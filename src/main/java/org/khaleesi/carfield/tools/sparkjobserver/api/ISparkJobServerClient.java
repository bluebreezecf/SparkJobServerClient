package org.khaleesi.carfield.tools.sparkjobserver.api;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * A client implements all the Rest APIs described by the
 * Spark Job Server (https://github.com/ooyala/spark-jobserver).
 * 
 * @author bluebreezecf
 * @since 2014-09-07
 */
public interface ISparkJobServerClient {
	/**
	 * Lists all the information of jars for potential jobs to be running
	 * in the Spark Cluster behind the Spark Job Server.
	 * 
	 * <p>
	 * This method implements the Rest API <code>'GET /jars' </code>of the 
	 * Spark Job Server.
	 * 
	 * @return a list containing information of Spark Job jars
	 */
	List<SparkJobJarInfo> getJars();
	
	/**
	 * Uploads a jar containing spark job to the Spark Job Server under
	 * the given application name.
	 * 
	 * <p>
	 * This method implements the Rest API <code>'POST /jars/&lt;appName&gt;' </code>
	 * of the Spark Job Server.
	 * 
	 * @param jarData the instance of <code>InputStream</code> contains the
	 *     contents of the target jar file to be uploaded
	 * @param appName the application name under which the related Spark Job
	 *     is about to run
	 * @return true if the operation of uploading is successful, false otherwise
	 * @throws SparkJobServerClientException if the given parameter jarData or
	 *     appName is null, or error occurs when uploading the related spark job 
	 *     jar
	 */
	boolean uploadSparkJobJar(InputStream jarData, String appName) 
        throws SparkJobServerClientException;
	
	/**
	 * Lists all the contexts available in the Spark Job Server.
	 * 
	 * <p>
	 * This method implements the Rest API <code>'GET /contexts '</code>
	 * of the Spark Job Server.
	 * 
	 * @return a list containing names of current contexts
	 */
	List<String> getContexts();
	
	/**
	 * Creates a new context in the Spark Job Server with the given context name.
	 * 
	 * <p>
	 * This method implements the Rest API <code>'POST /contexts/&lt;name&gt;' </code>
	 * of the Spark Job Server.
	 * 
	 * @param contextName the name of the new context to be created
	 * @param params a map containing the key-value pairs appended to appoint the context 
	 * settings if there is a need to configure the new created context, or null indicates
	 * the new context with the default configuration
	 * @return true if the operation of creating is successful, false otherwise
	 */
	boolean createContext(String contextName, Map<String, String> params);
	
	/**
	 * Delete a context with the given name in the Spark Job Server.
	 * All the jobs running in it will be stopped consequently.
	 * 
	 * <p>
	 * This method implements the Rest API <code>'DELETE /contexts/&lt;name&gt;' </code>
	 * of the Spark Job Server.
	 * 
	 * @param contextName the name of the target context to be deleted
	 * @return true if the operation of the deleting is successful, false otherwise
	 */
	boolean deleteContext(String contextName);
	
	/**
	 * Lists the last N jobs in the Spark Job Server.
	 * 
	 * <p>
	 * This method implements the Rest API <code>'GET /jobs' </code> of the Spark
	 * Job Server.
	 * 
	 * @return a list containing information of the jobs
	 */
	List<SparkJobInfo> getJobs();
	
	//TODO figure out the POST /jobs rest api
	//boolean startJob(xxxxParam);
	
	/**
	 * Gets the result or status of a specific job in the Spark Job Server.
	 * 
	 * <p>
	 * This method implements the Rest API <code>'GET /jobs/&lt;jobId&gt;' </code>
	 * of the Spark Job Server.
	 * 
	 * @param jobId the id of the target job
	 * @return the corresponding <code>SparkJobInfo</code> instance if the job
	 * with the given jobId exists, or null if there is no corresponding job in
	 * the spark job server.
	 */
	SparkJobInfo getJob(String jobId);
	
	/**
	 * Gets the job configuration of a specific job.
	 * 
	 * <p>
	 * This method implements the Rest API <code>'GET /jobs/&lt;jobId&gt;/config' </code>
	 * of the Spark Job Server.
	 * 
	 * @param jobId the id of the target job
	 * @return the corresponding <code>SparkJobConfig</code> instance if the job
	 * with the given jobId exists, or null if there is no corresponding job in 
	 * the spark job server.
	 */
	SparkJobConfig getConfig(String jobId);
}
