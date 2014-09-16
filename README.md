##Spark-Job-Server-Client

###Backgroud
People always use curl or HUE to upload jar and run spark job in Spark Job Server.
But the Spark Job Server official only presents the rest apis to upload job jar and 
run a job, doesn't give client lib with any language implementation.

Now there is another option to communicate with spark job server in Java, that is Spark-Job-Server-Client, the Java Client of the Spark Job Server implementing the arranged Rest APIs.

###How to use Spark-Job-Server-Client
The following sample codes shows how to use spark-job-server-client:

```java
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.khaleesi.carfield.tools.sparkjobserver.api.ISparkJobServerClient;
import org.khaleesi.carfield.tools.sparkjobserver.api.ISparkJobServerClientConstants;
import org.khaleesi.carfield.tools.sparkjobserver.api.SparkJobConfig;
import org.khaleesi.carfield.tools.sparkjobserver.api.SparkJobInfo;
import org.khaleesi.carfield.tools.sparkjobserver.api.SparkJobJarInfo;
import org.khaleesi.carfield.tools.sparkjobserver.api.SparkJobResult;
import org.khaleesi.carfield.tools.sparkjobserver.api.SparkJobServerClientException;
import org.khaleesi.carfield.tools.sparkjobserver.api.SparkJobServerClientFactory;

public class SparkJobServerClientTest {
	
	public static void main(String[] args) {
		ISparkJobServerClient client = null;
		try {
			client = SparkJobServerClientFactory.getInstance().createSparkJobServerClient("http://localhost:8090/");
			//GET /jars
			List<SparkJobJarInfo> jarInfos = client.getJars();
			//POST /jars/<appName>
			client.uploadSparkJobJar(new File("d:\\spark-examples_2.10-1.0.2.jar"), "spark-test");
			
			//GET /contexts
			List<String> contexts = client.getContexts();
			//POST /contexts/<name>--Create context with name ctxTest and null parameter
			client.createContext("ctxTest", null);
			//POST /contexts/<name>--Create context with parameters
			Map<String, String> params = new HashMap<String, String>();
			params.put(ISparkJobServerClientConstants.PARAM_MEM_PER_NODE, "10");
			params.put(ISparkJobServerClientConstants.PARAM_NUM_CPU_CORES, "512m");
			client.createContext("cxtTest2", params);
			
			//DELETE /contexts/<name>
			client.deleteContext("ctxTest");
			
			//GET /jobs
			List<SparkJobInfo> jobInfos = client.getJobs();

			//Post /jobs---Create a new job 
			Map<String, String> params2 = new HashMap<String, String>();
			params.put(ISparkJobServerClientConstants.PARAM_APP_NAME, "spark-test");
			params.put(ISparkJobServerClientConstants.PARAM_CLASS_PATH, "spark.jobserver.WordCountExample");
			//1.start a spark job asynchronously and just get the status information
			SparkJobResult result = client.startJob("input.string= fdsafd dfsf blullkfdsoflaw fsdfs", params);
			
			//2.start a spark job synchronously and wait until the result
			params.put(ISparkJobServerClientConstants.PARAM_CONTEXT, "cxtTest2");
			params.put(ISparkJobServerClientConstants.PARAM_SYNC, "true");
			result = client.startJob("input.string= fdsafd dfsf blullkfdsoflaw fsdffdsfsfs", params);

			//GET /jobs/<jobId>---Gets the result or status of a specific job
			result = client.getJobResult("fdsfsfdfwfef");
			//GET /jobs/<jobId>/config - Gets the job configuration
			SparkJobConfig jobConfig = client.getConfig("fdsfsfdfwfef");
		} catch (SparkJobServerClientException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.stop();
			}
		}
	}
}
```