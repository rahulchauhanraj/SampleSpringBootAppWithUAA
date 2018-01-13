
Sample Predix Microservice Template using Spring Rest Template with UAA auth
==============

Welcome to Predix Microservice CF Spring, a Predix Backend Microservice Template.  

Each Microservice Template project shares the following characteristics

* Externalized Properties files
* REST implementation and framework
* Cloud ready with a Manifest file
* Applicatoion with uaa auth
* Continuous Integration capable


1. Download the project  
  ```
  $ git clone https://github.build.ge.com/212578960/SampleCFSpringBootWithUAA.git  
  
  $ cd SampleCFSpringBootWithUAA
  
  $ mvn clean package  
  
    note: mvn clean install may run integration tests against services you may not have set up yet
  ```
  ```
2. Push to cloud  

    Take a look at the [SampleCFSpringBootWithUAA manifest.yml](manifest.yml) which provides properties and instructions for [pushing cloud foundry]		 
    ```
    $ cf push 
 
