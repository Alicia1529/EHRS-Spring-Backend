



## Spring-Backend

#### [Live demo](billyzou.com)

#### [API documentation](https://galactic-water-5893.postman.co/collections/8887048-39efb6fd-7f84-4231-a5e1-5c0719fa7886?version=latest&workspace=64c4b77c-2340-4b0e-835e-ee33fe7e5ae2)

#### Technical stack
*Web Framework*: Spring Boot

*Security Framework*: Apache Shiro

*Persistence*: MyBatis

*Dependency Management*: Maven

#### Necessary softwares and libraries 
1. Please download the Enterprise Version of IDE Eclipse
2. Install `Maven` library in case your IDE does not have it
3. Set up the  `Tomcat` server to `Apache Tomcat v9.0`
4. Load the `spring` folder to Eclipse

#### Configuration File
1. The default port is `8080` which means you can access the web service through `localhost:8080`
2. To change the service host, add `spring.port=xxxx` in the file `application.properties`

#### Database Setup
1. Get a MySQL database 
2. Import the sql file `createTable.sql` to create all the schemas
3. Setup the databse connection in `resources/application-dev.properties`

#### Email Service Setup
1. Get an enterprise version of google email account
2. Configure the email connection in `resources/application.properties`
3. Enable the `Allow less secure applications` toggle in your google account and allow STMP in your gmail setting 

#### Start the program
1. Run `maven clean` on the whole folder to remove any previous compiling
2. Run `maven build` on the whole folder with `Tomcat` to compile the program after changes
3. Run file `Application.java` to start the web service

#### Notes
1. No need to install any dependencies as `maven` will automatically install for you 
2. You may also run `maven install` to replace `maven clean` and `maven build`. `maven install` will also run unittest automatically

