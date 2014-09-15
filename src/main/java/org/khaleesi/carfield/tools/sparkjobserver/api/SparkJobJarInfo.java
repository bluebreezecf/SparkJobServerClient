package org.khaleesi.carfield.tools.sparkjobserver.api;

/**
 * Presents the information of spark job jar files, when
 * calling <code>GET /jars</code> to a spark job server.
 * 
 * @author bluebreezecf
 * @since 2014-09-11
 *
 */
public class SparkJobJarInfo {
	private static final String INFO_EMPTY_VALUE = "empty value";
	static final String INFO_KEY_JAR_NAME = "jarName";
	static final String INFO_KEY_UPLOADED_TIME = "uploadedTime";
	
	private String jarName;
	private String uploadedTime;
	public String getJarName() {
		return jarName;
	}
	public void setJarName(String jarName) {
		this.jarName = jarName;
	}
	public String getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(String uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("SparkJobJarInfo{\n");
		buff.append(" ").append(INFO_KEY_JAR_NAME).append(": ")
		    .append(this.getJarName() != null ? this.getJarName() : INFO_EMPTY_VALUE).append(",\n");
		buff.append(" ").append(INFO_KEY_UPLOADED_TIME).append(": ")
	    	.append(this.getUploadedTime() != null ? this.getUploadedTime() : INFO_EMPTY_VALUE).append('\n');
		buff.append("}");
		return buff.toString();
	}
}
