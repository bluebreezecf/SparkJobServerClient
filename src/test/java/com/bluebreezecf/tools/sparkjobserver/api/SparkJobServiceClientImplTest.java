/*
 * Copyright 2014-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bluebreezecf.tools.sparkjobserver.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bluebreezecf.tools.sparkjobserver.api.ISparkJobServerClient;
import com.bluebreezecf.tools.sparkjobserver.api.ISparkJobServerClientConstants;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobResult;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobServerClientFactory;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

/**
 * a test class for SparkJobServerClientImpl
 * @author yatshash
 * @since 2017-03-08
 */
public class SparkJobServiceClientImplTest {
    private static final String defaultSparkJobHost = "54.178.178.219";
    private static final String defaultSparkJobPort = "8090";
    private static String endpoint = String.format("http://%s:%s/", defaultSparkJobHost, defaultSparkJobPort);
    private ISparkJobServerClient client;
    private static final long POOLING_TIME_SEC = 1;

    @Before
    public void setUp() throws Exception {
        client = SparkJobServerClientFactory
                .getInstance()
                .createSparkJobServerClient(endpoint);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * test runJob with File resource
     * Warning: This test require deleting jar after test.
     * @throws Exception
     */
    @Test
    public void testRunJobWithFile() throws Exception {
        InputStream jarFileStream = ClassLoader.getSystemResourceAsStream("./job-server-tests-2.11-0.8.0-SNAPSHOT.jar");
        File inputData = new File(ClassLoader.getSystemResource("input-SparkJobServiceClientImpTest.json").toURI());

        String appName = "runjob-with-file-test";
        boolean isUploaded = client.uploadSparkJobJar(jarFileStream, appName);

        assertThat(isUploaded, is(true));

        Map<String, String> params = new HashMap<String, String>();
        params.put(ISparkJobServerClientConstants.PARAM_APP_NAME, appName);
        params.put(ISparkJobServerClientConstants.PARAM_CLASS_PATH, "spark.jobserver.WordCountExample");


        SparkJobResult result = client.startJob(inputData, params);
        String status = result.getStatus();

        assertThat(status, anyOf(is("STARTED"), is("FINISHED")));

        String jobId;
        if (status.equals(SparkJobResult.INFO_STATUS_FINISHED))
        {
            jobId = result.getJobId();
        } else{
            jobId = (String) result.getExtendAttributes().get("jobId");
        }

        while (!result.getStatus().equals(SparkJobResult.INFO_STATUS_FINISHED)
                && !result.getStatus().equals(SparkJobResult.INFO_STATUS_ERROR)){
            TimeUnit.SECONDS.sleep(POOLING_TIME_SEC);
            result = client.getJobResult(jobId);
        }

        assertThat(result.getResult(), is("{\"fdsafd\":1,\"a\":4,\"b\":1,\"dfsf\":1,\"c\":1}"));
    }


    /**
     * Warning: This test require deleting jar after test.
     * @throws Exception
     */
    @Test
    public void testUploadJar() throws Exception {
        InputStream jarFileStream = ClassLoader.getSystemResourceAsStream("./job-server-tests-2.11-0.8.0-SNAPSHOT.jar");

        String appName = "upload-jar-test";
        boolean isUploaded = client.uploadSparkJobJar(jarFileStream, appName);

        assertThat(isUploaded, is(true));

        Map<String, String> params = new HashMap<String, String>();
        params.put(ISparkJobServerClientConstants.PARAM_APP_NAME, appName);
        params.put(ISparkJobServerClientConstants.PARAM_CLASS_PATH, "spark.jobserver.WordCountExample");

        SparkJobResult result = client.startJob("input.string= fdsafd dfsf a b c a a a ", params);
        assertThat(result.getStatus(), anyOf(is("STARTED"), is("FINISHED")));
    }

}