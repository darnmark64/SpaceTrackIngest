# SpaceTrackIngest
This is a simple Spring Boot JAVA application that pulls data from a REST endpoint and posts the data to another REST endpoint.

This application queries space-track.org for objects of type *PAYLOAD* and uses the JSON to create Java POJOs, which are translated 
into CODEX space vehicle POJOS. The CODEX objects are translated into JSON and posted to a local CODEX REST endpoint to insert the
data into the CODEX database. 

## Running
### Requirements
1. Download code to your local workstation.
2. Install CODEX locally.
3. Obtain an account with space-track.org.
4. Install Maven (http://maven.apache.org)

### Space-Track.org Account
In order to run this application you need an account on space-track.org. When you have an account, edit the application.properties
to set *space_tracker.username* and *space_tracker.password* based on your account. You also need to encrypt your password using 
JASYPT:

```
[prompt]$ java -cp ~/.m2/repository/org/jasypt/jasypt/1.9.2/jasypt-1.9.2.jar  org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="yourspacetrackpassword" password=jasyptencryptpassword algorithm=PBEWithMD5AndDES
```

Put the results of this command in the *space_tracker.password* field inside the ENC\(...\).

Put the *jasyptencryptpassword* in an environment variable called **JASYPT_ENCRYPTOR_PASSWORD**.

### CODEX Account
You need to encrypt your local CODEX admin password as well using the same *jasyptencryptpassword* as above. Edit the application.properties
and put the results of the command in the *codex.password* field inside the ENC\( ... \).

### Build Application and Run
Change to the directory where you downloaded the code and issue the following commmand:

```
mvn clean install spring-boot:run
```
