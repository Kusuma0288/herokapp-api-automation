# woolworths-app-apigee

The Project is for APIGEE automation 

# Installation

Use the maven commands to install the dependencies and

```sh 
mvn clean install
```

To execute the project need to pass the parameters -Denv=<test or uat> -P<profilename>
For ex:
test = It will run in the test environment (Check the URL under resources/config/test.properties)

```sh
mvn clean test -Denv=test -Psmoke
```

To check the test reports

```sh
mvn clean test verify -Denv=test -Psmoke
```

To check the Request and Response Structure

```sh
mvn clean test verify -Denv=test -Psmoke -DsaveRequestResponse=yes
```

For Regression
```sh
mvn clean test verify -Denv=test -Pregression -DsaveRequestResponse=yes
```