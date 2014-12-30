package org.khaleesi.carfield.tools.sparkjobserver.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Presents the information of spark job configuration, when
 * calling <code>GET /jobs/&lt;jobId&gt;/config</code> to a 
 * spark job server.
 * 
 * <p>
 * The application used this <code>SparkJobConfig</code> instance
 * should use the <code>getConfigs()</code> and parse the values
 * itself.
 * 
 * @author bluebreezecf
 * @since 2014-09-11
 *
 */
public class SparkJobConfig {
	private Map<String, Object> configs = new HashMap<String, Object>();
	void putConfigItem(String key, Object value) {
		this.configs.put(key, value);
	}
	
	/**
	 * Gets all the configuration items.
	 *  
	 * @return a map holding the key-value pairs of configuration items 
	 */
	public Map<String, Object> getConfigs() {
		return new HashMap<String, Object>(this.configs);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("SparkJobConfig\n{\n");
		Set<Entry<String, Object>> items = configs.entrySet();
		for (Entry<String, Object> item : items) {
			buff.append(" ").append(item.getKey()).append(": ")
			    .append(item.getValue().toString()).append("\n");
		}
		buff.append("}");
		return buff.toString();
	}
}
