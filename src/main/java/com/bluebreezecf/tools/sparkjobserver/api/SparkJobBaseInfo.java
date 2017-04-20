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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * It acts as an base class and holds the mutual attributes of <code>SparkJobInfo</code>
 * and <code>SparkJobResult</code>.
 * 
 * @author bluebreezecf
 * @since 2014-09-15
 *
 */
class SparkJobBaseInfo {
	static final String INFO_EMPTY_VALUE = "empty value";
	
	/**
	 * Status value in a global job information (a <code>SparkJobInfo</code>
	 * instance) or a job result/status information 
	 * (a <code>SparkJobResult</code> instance)
	 */
	static final String INFO_STATUS_ERROR = "ERROR";
	
	/**
	 * Status value in a global job information (a <code>SparkJobInfo</code>
	 * instance)
	 */
	static final String INFO_STATUS_FINISHED = "FINISHED";
	
	/**
	 * Status value in a job status/result information (a <code>SparkJobResult</code> 
	 * instance)
	 */
	static final String INFO_STATUS_OK = "OK";
	
	/**
	 * Status value in a job status/result information (a <code>SparkJobResult</code> instance)
	 */
	static final String INFO_STATUS_STARTED = "STARTED";

	/**
	 * Status value in a job status/result information (a <code>SparkJobResult</code> instance)
	 */
	static final String INFO_STATUS_RUNNING = "RUNNING";

	/**
	 * Key of status information in the Spark Job Server's json response.
	 */
	static final String INFO_KEY_STATUS = "status";
	
	/**
	 * Key of result information in the Spark Job Server's json response.
	 */
	static final String INFO_KEY_RESULT = "result";
	
	/**
	 * Key of message information of a result item in the Spark Job Server's json response.
	 * <p>
	 * It presents the global description of the error
	 */
	static final String INFO_KEY_RESULT_MESSAGE = "message";
	
	/**
	 * Key of error class information of a result item in the Spark Job Server's json response.
	 * <p>
	 * It indicates the error class of current error message
	 */
	static final String INFO_KEY_RESULT_ERROR_CLASS = "errorClass";
	
	/**
	 * Key of stack class information of a result item in the Spark Job Server's json response.
	 * <p>
	 * It shows the information of java/scala exception stack
	 */
	static final String INFO_KEY_RESULT_STACK = "stack";

	/**
	 * Key of context information of a result item in the Spark Job Server's json response.
	 * <p>
	 * It's the context name.
	 */
	static final String INFO_KEY_CONTEXT = "context";
	
	/**
	 * Key of context information of a result item in the Spark Job Server's json response.
	 * <p>
	 * It shows the job id of the target spark job
	 */
	static final String INFO_KEY_JOB_ID = "jobId";

	static final Set<String> ASYNC_STATUS = new HashSet<String>(Arrays.asList(new String[]{INFO_STATUS_STARTED, INFO_STATUS_RUNNING}));
	static final Set<String> COMPLETED = new HashSet<String>(Arrays.asList(new String[]{INFO_STATUS_FINISHED, INFO_STATUS_OK}));

	private String status;
	private String message;
	private String errorClass;
	private String[] stack;
	private String context;
	private String jobId;
	protected String contents;
	
	public String getStatus() {
		return status;
	}
	void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	void setMessage(String message) {
		this.message = message;
	}
	public String getErrorClass() {
		return errorClass;
	}
	void setErrorClass(String errorClass) {
		this.errorClass = errorClass;
	}
	public String[] getStack() {
		return stack;
	}
	void setStack(String[] stack) {
		this.stack = stack;
	}
	
	public String getContext() {
		return context;
	}
	void setContext(String context) {
		this.context = context;
	}

	public String getJobId() {
		return jobId;
	}
	void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
