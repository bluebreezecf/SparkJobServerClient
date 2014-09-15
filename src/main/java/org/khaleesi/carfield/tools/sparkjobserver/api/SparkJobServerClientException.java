package org.khaleesi.carfield.tools.sparkjobserver.api;

/**
 * The exception indicates errors occurs when using instance 
 * of <code>ISparkJobServerClient</code>.
 * 
 * @author bluebreezecf
 * @since 2014-09-07
 *
 */
public class SparkJobServerClientException extends Exception {

	private static final long serialVersionUID = -5065403696198358625L;

	/**
	 * Constructs a new <code>SparkJobServerClientException</code> instance
	 * with the specified detail message. The cause is not initialized, and 
	 * may subsequently be initialized by a call to initCause.
	 * 
	 * @param message the detail message. The detail message is saved for 
	 *        later retrieval by the getMessage() method.
	 */
	public SparkJobServerClientException(String message) {
		super(message);
	}
	
	/**
     * Constructs a new <code>SparkJobServerClientException</code> instance
     * with the specified detail message and cause.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
	public SparkJobServerClientException(String message, Throwable cause) {
		super(message, cause);
	}
}
