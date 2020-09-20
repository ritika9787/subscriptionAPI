# subscriptionAPI

PROJECT SETUP:
1. Install Java8 and set JAVA_HOME environment variable
https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
For MAC goto terminal and do following steps: vi ~/.bash_profile 
Insert : export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home (Choose path from your system)

2. Install Maven Latest Version from https://maven.apache.org/download.cgi
Set Maven Home in environment variables : 1. vi ~/.bash_profile 2. export M2_HOME=/Users/temp/apache-maven-3.6.3 (use your system path)

3. Install M2E – useful Maven plugin for Eclipse / or similar for your IDE
In Eclipse go to Help ⇒ Install new Software ⇒ Add and use the location: http://download.eclipse.org/technology/m2e/releases ￼
Restart Eclipse.

4. Download the Project from GIT location to your IDE

5. Install Postgresql and update your username and password in project's /src/main/resources/application.properties
(Database will be auto generated via Hibernate when application is being run)

6. Update Maven Project : Right click the project and select Maven ⇒ Update Project.
7. Run Maven Build : Right Click the project and select Run As : Maven Build and add to the goal : clean install
8. Run Project as Java Application
9. Execute APIs from Postman:

API ENDPOINTS:
Please refere to below URL for running API Collection
https://documenter.getpostman.com/view/12707931/TVKBZeEt

