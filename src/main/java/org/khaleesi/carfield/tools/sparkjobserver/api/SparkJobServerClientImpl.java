package org.khaleesi.carfield.tools.sparkjobserver.api;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * The default client implementation of <code>ISparkJobServerClient</code>.
 * With the specific rest api, it can provide abilities to submit and manage 
 * Apache Spark jobs, jars, and job contexts in the Spark Job Server.
 * 
 * @author bluebreezecf
 * @since 2014-09-07
 *
 */
class SparkJobServerClientImpl implements ISparkJobServerClient {
	private static Logger logger = Logger.getLogger(SparkJobServerClientImpl.class);
	private static final int BUFFER_SIZE = 512 * 1024;
	private String jobServerUrl;
	
	/**
	 * Constructs an instance of <code>SparkJobServerClientImpl</code>
	 * with the given spark job server url.
	 * 
	 * @param jobServerUrl a url pointing to a existing spark job server
	 */
	SparkJobServerClientImpl(String jobServerUrl) {
		if (!jobServerUrl.endsWith("/")) {
			jobServerUrl = jobServerUrl + "/";
		}
		this.jobServerUrl = jobServerUrl;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SparkJobJarInfo> getJars() {
		HttpClient client = new DefaultHttpClient();
		HttpGet getMethod = new HttpGet(jobServerUrl + "jars");
		List<SparkJobJarInfo> sparkJobJarInfos = new ArrayList<SparkJobJarInfo>();
		InputStream in = null;
		try {
			HttpResponse response = client.execute(getMethod);
			int statusCode = response.getStatusLine().getStatusCode();
			//TODO check out the status code and determine whether process later or not 
			
			in = response.getEntity().getContent();
			byte[] buf = new byte[BUFFER_SIZE];
			int len = -1;
			StringBuffer buffer = new StringBuffer();
			while ((len = in.read(buf)) > 0) {
				buffer.append(new String(buf, 0, len));
			}
			
			String content = buffer.toString();
			if (content.length() > 0) {
				JSONObject jsonObj = JSONObject.fromObject(content);
				//TODO Try to figure out the json format of the response and fill in sparkJobJarInfos
			}
		} catch (Exception e) {
			logger.error("Error occurs when trying to get information of jars:", e);
		} finally {
			if (null != in) {
				closeStream(in);
			}
		}
		return sparkJobJarInfos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean uploadSparkJobJar(InputStream jarData, String appName)  
	    throws SparkJobServerClientException {
		if (jarData == null || appName == null || appName.trim().length() == 0) {
			throw new SparkJobServerClientException("Invalid parameters.");
		}
		HttpClient client = new DefaultHttpClient();
		HttpPost postMethod = new HttpPost(jobServerUrl + "jars/" + appName);
		byte[] contents = new byte[BUFFER_SIZE];
		int len = -1;
		StringBuffer buff = new StringBuffer();
		try {
			while ((len = jarData.read(contents)) > 0) {
				buff.append(new String(contents, 0, len));
			}
			HttpEntity entity = new ByteArrayEntity(buff.toString().getBytes());
			postMethod.setEntity(entity);
			HttpResponse response = client.execute(postMethod);
			
			int statusCode = response.getStatusLine().getStatusCode();
			//TODO check out the right and wrong response
			return true;
		} catch (Exception e) {
			logger.error("Error occurs when uploading spark job jar:", e);
			return false;
		} finally {
			closeStream(jarData);
		}
		
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getContexts() {
		HttpClient client = new DefaultHttpClient();
		HttpGet getMethod = new HttpGet(jobServerUrl + "contexts");
		List<String> contexts = new ArrayList<String>();
		InputStream in = null;
		try {
			HttpResponse response = client.execute(getMethod);
			int statusCode = response.getStatusLine().getStatusCode();
			//TODO check out the status code and determine whether process later or not 
			
			in = response.getEntity().getContent();
			byte[] buf = new byte[BUFFER_SIZE];
			int len = -1;
			StringBuffer buffer = new StringBuffer();
			while ((len = in.read(buf)) > 0) {
				buffer.append(new String(buf, 0, len));
			}
			
			String content = buffer.toString();
			if (content.length() > 0) {
				JSONObject jsonObj = JSONObject.fromObject(content);
				//TODO Try to figure out the json format of the response and fill in contexts
			}
		} catch (Exception e) {
			logger.error("Error occurs when trying to get information of contexts:", e);
		} finally {
			if (null != in) {
				closeStream(in);
			}
		}
		return contexts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean createContext(String contextName, Map<String, String> params) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteContext(String contextName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SparkJobInfo> getJobs() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SparkJobInfo getJob(String jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SparkJobConfig getConfig(String jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Closes the given stream.
	 * 
	 * @param stream the input/output stream to be closed
	 */
	private void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException ioe) {
				logger.error("Error occurs when trying to close the stream:", ioe);
			}
		} else {
			logger.error("The given stream is null");
		}
	}
}
