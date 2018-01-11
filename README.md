<a href="http://predixdev.github.io/predix-microservice-cf-spring/javadocs/index.html" target="_blank" >
	<img height="50px" width="100px" src="images/javadoc.png" alt="view javadoc"></a>
&nbsp;
<a href="https://github.com/rahulchauhanraj/SampleSpringBootAppWithUAA" target="_blank">
	<img height="50px" width="100px" src="images/pages.jpg" alt="view github pages">
</a>

Sample Spring Boot Predix Microservice with UAA
================================================

Sample Spring Boot project shares the following characteristics
* Test cases and test case framework 
* Externalized Properties files
* REST implementation and framework
* Cloud ready with a Manifest file
* Sample Spring boot application with UAA security enabled
* Continuous Integration capable

## SampleSpringBootAppWithUAA

This project is a cloud-ready microservice.

1. Prepare your environment and follow the steps below to get up and running on Cloud Foundry.   

1. Download the project  
  ```
  $ git clone https://github.com/rahulchauhanraj/SampleSpringBootAppWithUAA.git
  
  $ cd SampleSpringBootAppWithUAA
  
  $ mvn clean package  
  
    note: mvn clean install may run integration tests against services you may not have set up yet
  ```
1. Push to cloud  

    Take a look at the [SampleSpringBootAppWithUAA manifest.yml](manifest.yml) which provides properties and instructions for pushing to cloud foundry.
  ```
  $ cf push  
  

