package org.khaleesi.carfield.tools.sparkjobserver.api;

/**
 * Acts as an base class and holds the mutual attributes of <code>SparkJobInfo</code>
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
	
	static final String INFO_KEY_STATUS = "status";
	static final String INFO_KEY_RESULT = "result";
	static final String INFO_KEY_RESULT_MESSAGE = "message";
	static final String INFO_KEY_RESULT_ERROR_CLASS = "errorClass";
	static final String INFO_KEY_RESULT_STACK = "stack";

	static final String INFO_KEY_CONTEXT = "context";
	static final String INFO_KEY_JOB_ID = "jobId";
	
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
