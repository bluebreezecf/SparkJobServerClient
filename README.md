##Spark-Job-Server-Client

###Backgroud
People always use curl or HUE to upload jar and run spark job in Spark Job Server.
But the Spark Job Server official only presents the rest apis to upload job jar and 
run a job, doesn't give client lib with any language implementation.

Now there is another option to communicate with spark job server in Java, that is Spark-Job-Server-Client, the Java Client of the Spark Job Server implementing the arranged Rest APIs.

Spark-Job-Server-Client is a open-source program of **org.khaleesi.carfield** under Apache License v2. It aims to make the java applications use the spark more easily.

###How to compile
If you want to generate the whole version, which is a single jar of spark-job-server-client
containing all the dependent jars. You can execute the following commands:
```shell
git clone https://github.com/bluebreezecf/SparkJobServerClient.git
cd SparkJobServerClient
mvn clean package
```
Then you can find`spark-job-server-client-1.0.0.jar`in SparkJobServerClient/target, it is the main jar of spark-job-server-client. Besides, `spark-job-server-client-1.0.0-sources.jar`is the java source jar, and `spark-job-server-client-1.0.0-javadoc.jar` is the java doc api jar.

If you just want a standalone version, which is a single jar of spark-job-server-client
without any the dependent jars. You should backup current `pom.xml` and replace its contents with
the contents in `pom.xml_standalone`
```shell
cd SparkJobServerClient
mv pom.xml pom.xml_bak
mv pom.xml_standalone pom.xml
mvn clean package
```

###How to set dependency
There are two kind of spark-job-servier-client, accordingly there are two approaches to set the dependency:

- Use the whole version of spark-job-servier-client
 
 1. Add spark-job-server-client-1.0.0.jar to src/main/resources/lib folder of your application
 2. Add the following contents to the pom.xml 
```xml
<dependency>
    <groupId>org.khaleesi.carfield</groupId>
    <artifactId>spark-job-server-client</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/src/main/resources/lib/spark-job-server-client-1.0.0.jar</systemPath>
</dependency>
```
- Use the standalone version of spark-job-servier-client
 1. Install spark-job-servier-client to your local maven repository with `mvn clean install`
 2. Add the following contents to the pom.xml 
```xml
<dependency>
    <groupId>org.khaleesi.carfield</groupId>
    <artifactId>spark-job-server-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

###How to use
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

/**
 * A sample shows how to use spark-job-server-client.
 * 
 * @author bluebreezecf
 * @since 2014-09-16
 *
 */
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
###How to contribute
Anyone interested in this program can do the following things:
 1. `Fork` it to your own git repository.
 2. Create a new branch for your feature via `git checkout -b your-new-feature`.
 3. Add or modify new codes.
 4. Commit the modifications through `git commit -am 'add your new feature'`.
 5. Push the new branch by `git push origin your-new-feature`.
 6. Create a new `pull request`.

Any questions and discussions can be added in [SparkJobServerClient/issues] (https://github.com/bluebreezecf/SparkJobServerClient/issues)
