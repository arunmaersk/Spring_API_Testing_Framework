# Telikos-E2E-API-Test[e2e-integration-tests]
This repository host automated rest assured based test cases for all the microservices developed for Telikos

**Tech Stack**
1. Serenity Rest Assured
2. Junit
3. Spring boot
4. Serenity Reporting
5. Maven surefire plugin for execution/re-execution of failed test cases
6. Spring Boot


**Test Configurations**: These controlled by properties file and by default spring provides application.properties . 
These are customised to suffice env specifications and application-env.properties highlights respective env configurations

**Execution**: 
    **Maven Cli:** mvn clean verify -Dspring.profiles.active=env
     Specify the spring profile(which we refer to as env) for diff configurations i.e. dev OR qa OR prd

**Framework Structure:**
It is a maven project wherein:
--src/main: It incorporates all Services, generic methods, utils, POJO(s) to be used across the framework. 
--src/test: It incorporates all test classes, test runners, test data etc. In brief, whatever is test specific goes here.
In this project we are using BDD(Cucumber > Gherkin) style of writing test scripts wherein feature files, step definitions,test runners and property files reside here.
-application-**env**.properties: Configure these properties file for any env level configurations
-Reporting: Navigate to target/site/serenity/index.html for consolidated reports post execution

**Prerequisite:**
Clone Parent POM ie "telikos-api-test-parent" first before cloning this repo.
**Branching Strategy:**
Here is the strategy that we propose to follow:
--**main**: This branch only includes the parent framework and sample test case and not specific to any microservice.
Checkout from this branch only if you have to include some framework or generic changes that can be considered for enhancement or pace up test development like Design patterns,Reusable Code, Exception handling,reporting etc.
--**develop**: This is the parent branch that will be used to execute the pipeline.Rebase your branch with develop before raising a merge request
--Any modification/addition w.r.t to test scripts for a particular microservice has to be merged to develop branch. Rebase your branch with develop before raising a merge request
--**Steps to rebase:**
a.Checkout **develop** branch and make sure have the latest code base(git rebase develop)
b. Checkout to the feature branch
c. execute command : git rebase develop
**PS:** Resolve conflicts if any until rebase completes and then raise merge request with develop branch

--**naming convention:**
Checkout branch as feature/ticket-number which helps in traceability of the feature/test scripts. This branch gets deleted from github as and when it gets merged to develop