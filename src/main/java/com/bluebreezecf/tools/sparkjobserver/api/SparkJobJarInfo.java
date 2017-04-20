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
