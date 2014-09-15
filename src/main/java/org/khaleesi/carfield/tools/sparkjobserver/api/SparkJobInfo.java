package org.khaleesi.carfield.tools.sparkjobserver.api;


/**
 * Presents the information of spark job, when calling 
 * <code>GET /jobs</code> to a spark job server.
 * 
 * @author bluebreezecf
 * @since 2014-09-11
 *
 */
public class SparkJobInfo extends SparkJobBaseInfo {
	
	static final String INFO_KEY_DURATION = "duration";
	static final String INFO_KEY_CLASSPATH = "classPath";
	static final String INFO_KEY_START_TIME = "startTime";
	
	private String duration;
	private String classPath;
	private String startTime;

	
	public String getDuration() {
		return duration;
	}
	void setDuration(String duration) {
		this.duration = duration;
	}
	public String getClassPath() {
		return classPath;
	}
	void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public String getStartTime() {
		return startTime;
	}
	void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("SparkJobInfo");
		buff.append("{\n");
		buff.append(" ").append(INFO_KEY_DURATION).append(": ")
	    	.append(this.getDuration() != null ? this.getDuration() : INFO_EMPTY_VALUE).append("\n")
	    	.append(" ").append(INFO_KEY_CLASSPATH).append(": ")
	    	.append(this.getClassPath() != null ? this.getClassPath() : INFO_EMPTY_VALUE).append("\n") 
	    	.append(" ").append(INFO_KEY_START_TIME).append(": ")
    		.append(this.getStartTime() != null ? this.getStartTime() : INFO_EMPTY_VALUE).append("\n")
    		.append(" ").append(INFO_KEY_CONTEXT).append(": ")
			.append(this.getContext() != null ? this.getContext() : INFO_EMPTY_VALUE).append("\n")
			.append(" ").append(INFO_KEY_JOB_ID).append(": ")
			.append(this.getJobId() != null ? this.getJobId() : INFO_EMPTY_VALUE).append("\n")
			.append(" ").append(INFO_KEY_STATUS).append(": ")
			.append(this.getStatus() != null ? this.getStatus() : INFO_EMPTY_VALUE).append("\n");
		if (this.getMessage() != null) {
			buff.append(" ").append(INFO_KEY_RESULT).append(": {\n")
				.append("  ").append(INFO_KEY_RESULT_MESSAGE).append(": ").append(getMessage()).append("\n");
		}
		if (this.getErrorClass() != null) {
			buff.append("  ").append(INFO_KEY_RESULT_ERROR_CLASS).append(": ").append(getErrorClass()).append("\n");
		}
		if (this.getStack() != null) {
			buff.append("  ").append(INFO_KEY_RESULT_STACK).append(": [");
			for (String stackItem : getStack()) {
				buff.append(" ").append(stackItem).append(",\n");
			}
			buff.append("  ]");
		}
		
		buff.append("}");
		return buff.toString();
	}
}
