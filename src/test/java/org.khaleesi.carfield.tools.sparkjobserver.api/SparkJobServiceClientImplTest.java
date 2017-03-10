package org.khaleesi.carfield.tools.sparkjobserver.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

/**
 * a test class for SparkJobServerClientImpl
 * @author yatshash
 * @since 2017-03-08
 */
public class SparkJobServiceClientImplTest {
    private static final String defaultSparkJobHost = "127.0.0.1";
    private static final String defaultSparkJobPort = "8090";
    private static String endpoint = String.format("http://%s:%s/", defaultSparkJobHost, defaultSparkJobPort);
    private ISparkJobServerClient client;

    @Before
    public void setUp() throws Exception {
        client = SparkJobServerClientFactory
                .getInstance()
                .createSparkJobServerClient(endpoint);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testUploadJar() throws Exception {
        InputStream jarFileStream = ClassLoader.getSystemResourceAsStream("./job-server-tests-2.11-0.8.0-SNAPSHOT.jar");

        String appName = "test8";
        boolean isUploaded = client.uploadSparkJobJar(jarFileStream, appName);

        assertThat(isUploaded, is(true));

        Map<String, String> params = new HashMap<String, String>();
        params.put(ISparkJobServerClientConstants.PARAM_APP_NAME, appName);
        params.put(ISparkJobServerClientConstants.PARAM_CLASS_PATH, "spark.jobserver.WordCountExample");

        SparkJobResult result = client.startJob("input.string= fdsafd dfsf a b c a a a ", params);
        assertThat(result.getStatus(), anyOf(is("STARTED"), is("FINISHED")));
    }

}