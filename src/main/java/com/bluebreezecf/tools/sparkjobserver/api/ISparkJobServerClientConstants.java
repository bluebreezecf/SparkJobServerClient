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

/**
 * Defines the constants used in public for
 * the spark job server client.
 * 
 * @author bluebreezecf
 * @since 2014-09-11
 *
 */
public interface ISparkJobServerClientConstants {

	/**
	 * Parameter used to create job context, indicates the executor
	 * instances for current spark job context.
	 * <p>
	 * It's value is integer.
	 * <P>
	 * It is equivalent to <code>PARAM_SPARK_CORES_MAX</code>.
	 */
	String PARAM_NUM_EXECUTORS = "spark.executor.instances";

	/**
	 * Parameter used to create job context, indicates the number of cpu cores
	 * for current spark job context.
	 * <p>
	 * It's value is integer.
	 * <P>
	 * It is equivalent to <code>PARAM_SPARK_CORES_MAX</code>.
	 */
	String PARAM_NUM_CPU_CORES = "num-cpu-cores";
	
	/**
	 * Parameter used to create job context, indicates the number of cpu cores
	 * for current spark job context.
	 * <p>
	 * It's value is integer.
	 * <p>
	 * It is equivalent to <code>PARAM_NUM_CPU_CORES</code>.
	 */
	String PARAM_SPARK_CORES_MAX = "spark.cores.max";
	
	/**
	 * Parameter used to create job context, indicates the number of memeory
	 * for each spark job executor.
	 * <p>
	 * It's value is 'xxxm' such as 512m, where xxx is a integer, and m means MB.	 
	 * <p>
	 * It is equivalent to <code>PARAM_SPARK_EXECUTOR_MEMORY</code>.
	 */
	String PARAM_MEM_PER_NODE = "mem-per-node";
	
	/**
	 * Parameter used to create job context, indicates the number of memeory
	 * for each spark job executor.
	 * <p>
	 * It's value is 'xxxm' such as 512m, where xxx is a integer, and m means MB.	 
	 * <p>
	 * It is equivalent to <code>PARAM_MEM_PER_NODE</code>.
	 */
	String PARAM_SPARK_EXECUTOR_MEMORY = "spark.executor.memory";
	
	/**
	 * It's a necessary parameter used to assign a application name when 
	 * trying to start a new job.
	 */
	String PARAM_APP_NAME = "appName";
	
	/**
	 * It's a necessary parameter which is a full qualified class name such as 
	 * <code>'spark.jobserver.WorkCountExample'</code>, used to assign a spark 
	 * job main class when trying to start a new job.
	 */
	String PARAM_CLASS_PATH = "classPath";
	
	/**
	 * It's an optional parameter which is an existing context in the calling of 
	 * <code>GET /contexts</code>, used to assign a context in which the new 
	 * created job will run.
	 * 
	 * <p>
	 * When it wants the new started job to run synchronize and gets the result,
	 * It becomes a necessary one.
	 */
	String PARAM_CONTEXT = "context";

	/**
	 * It's an optional parameter which is used to create contexts using context
	 * factory.
	 */
	String PARAM_CONTEXT_TYPE = "context-factory";
	
	/**
	 * It's an optional parameter which could be "true" or "false", 
	 * which is used to identify the client will wait for created 
	 * job 's finish and get the results.
	 * 
	 * <p>
	 * When it wants the new started job to run synchronize and gets the result,
	 * It becomes a necessary one.
	 */
	String PARAM_SYNC = "sync";

	/**
	 * It's an optional parameter which is the number of seconds the,
	 * should wait before timeing out. Increasing this value may be
	 * required when using sync on long jobs.
	 * <p>
	 * It's value is integer.
	 * </p>
	 */
	String PARAM_TIMEOUT = "timeout";
}
