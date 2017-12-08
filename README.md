# highrock-api
72craft API written by Highrock
# INFO
This API is base on SpringBoot,it's developing on highrock's Local Area Network.<br>
Once it completed,It will run in AWS.<br>
Earthing need to help us to modify the configration in application.yml about the "dev-aurora"<br>
# API INFO
This API is base on the file <72craft Interface description><br>
Import this project into JAVA IDE like([IntelliJ IDEA](http://www.jetbrains.com/idea/) or [Eclipse](http://www.eclipse.org/))<br>
Run Application.java to start the API server.<br>
Following Two API is called by Earthling.  
### Basic Auth
We had add Basic Auth for the API.  
API 2.1 & API 2.2 both require “Basic Auth” to be set in the header of the request.  
For the stage environment, you can use:  
Username: highrock  
Password: Tianshi@18  

### API 2.1
URL: [localhost:8090/order/Order_Payed](http://localhost:8090/order/Order_Payed),method=POST.Parameter is json String ,like this {"order_no":"20170605144915001"}<br>
It'll respond:  
1. `{"result":"S","errormsg":""}` when the API run to completion successfully  
2. `{"result":"F","errormsg":"order is Missing"}` when the API can't find the order.  
3. `{"result":"F","errormsg":"Exception on BackEndAPI!"}` when the API called 72craft backend API and got an exception.  
4. `{"result":"F","errormsg":"Exception on API!"+ the exception message}` when the API got exception.   
### API 2.3  
URL:[localhost:8090/order/Order_Payed](http://localhost:8090/order/Order_Payed),method=POST.Parameter is json String ,like this {"order_no":"20170605144915001","user_address":"Los Angeles, CA 90012","comment","customer need change the address","user_comment","There were a few typos in the post address."}<br>
It'll respond:<br>
1. `{"result":"S","errormsg":""}` when the API run to completion successfully  
2. `{"result":"F","errormsg":"order is Missing"}` when the API can't find the order.  
3. `{"result":"F","errormsg":"Param is invalid"}` when the API can't convert the parameter.  
4. `{"result":"F","errormsg":"Update failed"}` when the API got exception. 
## the other APIs  
### API 2.2   
this API will call API 1.1 written by Earthling,with parameter like this{"cp_order":{"order_no":"20170605144915001","status":"200"}}  
Earthling need to supplements the API URL in properties file.   
Path:resources/source.properties EarthlingApi.root & EarthlingApi.orderStatus   

### API For Update Storage(Developing)  
This Api is used for update material's storage daily from 72craft BackEnd.   
Earthling don't need it.  
URL:[localhost:8090/storage/storage_update](http://localhost:8090/storage/storage_update),method=PUT.Parameter is json String ,like this [{"id":"1","material_code":"Birch 001","material_storage_num":"60"},{"id":"2","material_code":"Russet Orange 001","material_storage_num":"100"}]  

# DEPLOY
[JDK 1.8(64bit)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
For quickly deploy,we put jar file here.Earthing can also rebuild jar by command "mvn clean package" where "pom.xml" is.It's should be OK now.<br>
jar path: jar/highrock-api-1.0.0.jar.<br>
run command "java -jar highrock-api-1.0.0.0.jar > log.file 2>&1 &" to start the API<br>
We can access the RDS databasese now.And the connection properties base on [https://github.com/72craft/devops/blob/master/parameters.json](https://github.com/72craft/devops/blob/master/parameters.json)<br>

## application.yml
The following profiles are used to connect to different databases:
* `dev-highrock` -- the configuration used in developing on highrock's Local Area Network
* `local` -- the configuration used when developing on a database on your local computer, usually running in a Docker container
* `stage-aurora-tunnel` -- the configuration used to connect to the staging database on RDS Aurora from *outside* of the AWS VPC.  You must also have a tunnel to RDS open as described [here](https://github.com/72craft/devops#tunnel-rds-aurora).  Used for testing and troubleshooting.
* `stage-aurora` -- the configuration used to connect to the staging database on RDS Aurora from *inside* of the AWS VPC.  Used by the instance of the Highrock API deployed to the AWS Staging server.
* `prod-aurora` -- the configuration used to connect to the production database on RDS Aurora from *inside* of the AWS VPC.  Used by the instance of the Highrock API deployed to the AWS Production server.

To run with a particular profile, set the `spring.profiles.active` property on the command line:
```bash
java -Dspring.profiles.active=local -jar ./target/highrock-api-1.0.0.jar
```

