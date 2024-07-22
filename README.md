# Advance Java

Banuprakash C

Full Stack Architect, Corporate Trainer

Co-founder & CTO: Lucida Technologies Pvt Ltd., 

https://www.lucidatechnologies.com/

https://www.linkedin.com/in/banu-prakash-50416019/


Emails: 
banuprakashc@yahoo.co.in

banuprakash.cr@gmail.com

banu@lucidatechnologies.com

Repository for Training:
https://github.com/BanuPrakash/CISCO

===================================

Softwares Required:
1)  openJDK 17
https://jdk.java.net/java-se-ri/17

2) IntelliJ Ultimate edition 
https://www.jetbrains.com/idea/download/?section=mac

OR

Eclipse for JEE  
	https://www.eclipse.org/downloads/packages/release/2022-09/r/eclipse-ide-enterprise-java-and-web-developers

3) MySQL  [ Prefer on Docker]

Install Docker Desktop

Docker steps:

```
a) docker pull mysql

b) docker run --name local-mysql –p 3306:3306 -e MYSQL_ROOT_PASSWORD=Welcome123 -d mysql

container name given here is "local-mysql"

For Mac:
docker run -p 3306:3306 -d --name local-mysql -e MYSQL_ROOT_PASSWORD=Welcome123 mysql


c) CONNECT TO A MYSQL RUNNING CONTAINER:

$ docker exec -t -i local-mysql bash

d) Run MySQL client:

bash terminal> mysql -u "root" -p

mysql> exit

```

4. POSTMAN

=====================

Advance Java: TOC
```
1) Day 1:
Servlet Technologies to build web based application using MySQL as database
* Servlet
* JSP
* Filter
* Listener
* Session Tracking
* Servlet Context

2) Day 2, 3, 4 --> Spring and Spring Boot with JPA using Hibernate to build RESTful Web Services
3) Day 5 --> Security to your RESTful WS & Introduction to MicroServices

```

Day 1

Rendering ==> data to presentation

```
* Server Side Rendering (SSR)
    Advantages:
    1) Thin Client
    2) Good for SEO 
    Disadvantages:
    1) Can't have heterogeous clients
    2) Tight coupling to type of client
    3) Heavy Network traffic / payload
* Client Side Rendering (CSR)
    Advantages:
    1) heterogeous clients like Web / Mobile / Desktop / Tv /,...
    2) light weight payload
    3) decoupling of client and server technologies
    Disadvantage:
    1) not SEO 
    2) Heavy client [ renderers are required in client machine]
```

Servlet is a technology to build web applications using Java techonologies

Java techonologies --> we run bytecode on JVM [ part of JRE ]
bytecode can be generated using various programming languages like Java / Kotlin / Groovy

Servlet Technology uses Servlet engine / Web Container / Servlet Container to manage life cycle of objects, Dependency Injection

Request: encapsulates all the data comming from client: Form Data + Browser + OS

Response: is used to write data back to client [ contains client IP and PORT number ]


=========

Multi Threaded application:
Process --> Program in execution --> Unit of Work --> Thread
If an application is having many units of work concurrently executing --> Multi threaded

Word Application:
1 Thread for User interaction [ typing]
1 Thread for Grammer Check
1 Thread for Spell Check
1 Thread for Auto Saving

===============

HTTP protocol
Method of request can be GET / POST / PUT / PATCH / DELETE

GET: address bar and hyperlink are by default GET request

POST: generally FORM data, by default this is also GET request


Servlets are classes which are inherited from HttpServlet

```
GET / POST / PUT
http://localhost:8080/login

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {

    }

     public void doDelete(HttpServletRequest req, HttpServletResponse res) {

    }

     public void doPut(HttpServletRequest req, HttpServletResponse res) {

    }

}

new LoginServlet(); // not do this , rather Servlet engine creates this object 

```

Deployment Descriptor --> passing Metadata to Servlet engine
web.xml

```
<servlet>
    <servlet-name> First </servlet-name>
    <servlet-class> pkg.LoginServlet </servlet-class>
</servlet>
<servlet>
    <servlet-name> Second </servlet-name>
    <servlet-class> pkg.RegisterServlet </servlet-class>
</servlet>

<servlet-mapping>
     <servlet-name> First </servlet-name>
     <url-pattern>/login</url-pattern>
</servlet-mapping>

<servlet-mapping>
     <servlet-name> Second </servlet-name>
     <url-pattern>/register</url-pattern>
</servlet-mapping>
```

We can also use Annotations instead of XML [ we are going to use]
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

===========

Annotation: --> Metadata [ data about data]
* Who is going to use it?
1) Compiler
2) ClassLoader
3) Runtime
* Where can I use it?
1) Class level
2) method level
3) field level

Example:
```
public class Base {
    public void doTask() {}
}

public class Derived extends Base {
    @Override
    public void doTask() {}
}

@Override --> used by Compiler and  method level
When Compiler compiles Derived.java this annoation tells compiler to  check base class signature, if any mismatch don't compiler
Derived.java


@SettopBox(name="hathway")
public class ChannelList {

}

CustomClassLoader --> NetworkClassLoader
SettopBox --> annoation used by class loader
```

% docker exec -it local-mysql bash
# mysql -u root -p

mysql> create database CISCO_JAVA;

mysql> use CISCO_JAVA

mysql> create table products (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), price double);

mysql> insert into products values(0, 'iPhone 15', 89000.00);
Query OK, 1 row affected (0.01 sec)

mysql> insert into products values(0, 'Samsung OLED', 210000.00);
Query OK, 1 row affected (0.01 sec)

mysql> select * from products;

=====

JDBC Java Database Connectivity ==> Integration Library

Maven or Gradle are Java build tools
* manage dependecies
dependencies are in the form of JAR files residing in repositories
we can download one by one and configure in the application [40+ jar]
or
allow Maven / Gradle to do this 

* run goals like clean / Compile/ build / test

Local Repository:
/Users/<<username>>/.m2/repositories/....


Java provides JDBC ==> collection on interfaces
Implementation classes are provided by the database vendors [Oracle / MySQL / H2 / Derby / Postgres]

=============

Entity ==> representation of business data, long lived , can survive system crush ==> will have association with persistent store like relational database / file system / NoSQL

DAO [Data Access Object] ==> CRUD operations CREATE READ UPDATE AND DELETE

Client, Exception Handling

e.getMessage() and e.printStackTrace()

Connection, DriverManger.getConnection(), Statement, PreparedStatement [if SQL takes IN Parameter]
CallableStatement --> to invoke StoredProcedures / Functions of Database [INOUT parameter]

==========================

Web Application Development:
1) Servlet Containers: jetty / tomcat / netty ...
Eclipse Jetty is a Java web server and Java Servlet container.

2) we need servlet apis dependency

added into pom.xml
<packaging>war</packaging>

Web Archive similar to JAR but with a specific type of folder structure which Servlet engines can understand

maven-compiler-plugin ==> uses Maven to compile instead of IDE specific

mvn compile

War Plugin:
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.4.0</version>
        <configuration>
            <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
</plugin>

```
WAR format
    application
        |
        WEB-INF
            |
            classes
                |
                ProductServlet.class
                LoginServlet.class
                RegisterServlet.class
            web.xml

```

src/main/webapp folder ==> place where we add static resources like HTML / CSS / JS for the project

mvn jetty:run

mvn package

Servlet is good for Dynamic Content.

Use Templates for View rendering: JSP, Thymeleaf ,..

JSP ==> combination of Static + dynamic content


Http protocol is a stateless protocol. No conversational state of client

Session Tracking: ability to keep track of conversational state of client

JSESSIONID can be sent to client using Cookie <<default>> or by using URL-Rewriting

http://localhost:8080/lisp.jsp?JSESSIONID=2lwqb1

http://localhost:8080/login.jsp









