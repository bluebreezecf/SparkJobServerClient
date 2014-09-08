package org.khaleesi.carfield.tools.sparkjobserver.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * The factory is responsible for creating instance of <code>ISparkJobServerClient</code>
 * to communicate with the Spark Job Server with the arranged rest apis.
 * 
 * @author bluebreezecf
 * @since 2014-09-07
 *
 */
public final class SparkJobServerClientFactory {
	private static final SparkJobServerClientFactory INSTANCE = new SparkJobServerClientFactory();
	
	private static Logger logger = Logger.getLogger(SparkJobServerClientFactory.class);
	
	private static Map<String, ISparkJobServerClient> jobServerClientCache 
	    = new ConcurrentHashMap<String, ISparkJobServerClient>();
	
	/**
	 * The default constructor of <code>SparkJobServerClientFactory</code>. 
	 */
	private SparkJobServerClientFactory() {
	}
	
	/**
	 * Gets the unique instance of <code>SparkJobServerClientFactory</code>.
	 * @return the instance of <code>SparkJobServerClientFactory</code>
	 */
	public static SparkJobServerClientFactory getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Creates an instance of <code>ISparkJobServerClient</code> with the given url.
	 * 
	 * @param url the url of the target Spark Job Server
	 * @return the corresponding <code>ISparkJobServerClient</code> instance
	 * @throws SparkJobServerClientException error occurs when trying to create the 
	 *     target spark job server client
	 */
	public ISparkJobServerClient createSparkJobServerClient(String url) 
		throws SparkJobServerClientException {
		if (!isValidUrl(url)) {
			throw new SparkJobServerClientException("");
		}
		String sparkJobServerUrl = url.trim();
		ISparkJobServerClient sparkJobServerClient = jobServerClientCache.get(sparkJobServerUrl);
		if (null == sparkJobServerClient) {
			sparkJobServerClient = new SparkJobServerClientImpl(url);
			jobServerClientCache.put(url, sparkJobServerClient);
		}
		return sparkJobServerClient;
	}
	
	/**
	 * Checks the given url is valid or not.
	 * 
	 * @param url the url to be checked
	 * @return true if it is valid, false otherwise
	 */
	private boolean isValidUrl(String url) {
		if (url == null || url.trim().length() <= 0) {
			logger.error("The given url is null or empty.");
			return false;
		}
		try {
			new URL(url);
		} catch (MalformedURLException me) {
			StringBuffer buff = new StringBuffer("The given url ");
			buff.append(url).append(" is invalid.");
			logger.error(buff.toString(), me);
		}
		return true;
	}
}
