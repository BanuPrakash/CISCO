# Advance Java
```
Banuprakash C
Full Stack Architect, Corporate Trainer
Co-founder & CTO: Lucida Technologies Pvt Ltd.,
Email: banuprakashc@yahoo.co.in; banuprakash.cr@gmail.com; banu@lucidatechnologies.com
https://www.linkedin.com/in/banu-prakash-50416019/
https://github.com/BanuPrakash/CISCO

===================================

Softwares Required:
1) openJDK 21
https://jdk.java.net/java-se-ri/21

 For Mac machine USE SDKMAN to manage java

curl -s "https://get.sdkman.io" | bash

sdk install java 21.0.6-tem

sdk default java 21.0.6-tem 

https://mydeveloperplanet.com/2022/04/05/how-to-manage-your-jdks-with-sdkman/#:~:text=Some%20time%20ago%2C%20a%20colleague%20of%20mine,maintain%20different%20versions%20of%20JDKs%2C%20Maven%2C%20etc.


2) IntelliJ Ultimate edition https://www.jetbrains.com/idea/download/?section=mac

3) MySQL  [ Prefer on Docker]

Install Docker Desktop

Docker steps:

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

Day  1:
Web application and database

Day 2, 3, 4: Spring Boot and JPA [Hibernate], Building RESTful WS

Day 5: Security and Introduction to MicroServices

==================================

Part 1:

Web application development and JDBC

JSE: Java Std Edition
JEE: Java Enterprise Edition provides Servlet API for building web application.

Apache Tomcat / Jetty  are popular open-source Java servlet container and HTTP web server, primarily used to host web applications and APIs built with Java.

1) Server Side Rendering
    Presentation pages are sent to client.
    Pros:
    * Thin clients
    * Faster 
    Cons:
    * Can't have heterogenous clients like Desktop / Mobile / Tv ...
    * Heavy payload
2) Client Side Rendering
    Pros:
        * Lightweight payload
        * can be heterogenous clients like Desktop / Mobile / Tv
    Cons:
        * Heavy clients
        * If not handled properly, this can be slow

Rendering ==> data to presentation logic

--> SSR

Servlet technology uses Java for SSR.
* Servlet
* Filter
* Listener
* JSP pages

Servlet's are special java objects which execute within the servlet engine/ servlet container / web container

HTTP REquests: URL and MEthod of request

GET --> making request from Address bar and hyperlink by default is GET
POST --> generally FORM submission [ send data to server as payload] which needs to be stored in persist store
Create a new resource on server
PUT/PATCH --> Modify existing resource
DELETE --> remove a resource on server

CRUD --> POST GET PUT/PATCH DELETE

Mapping between URL and Servlet 
http://server.com/registration
http://server.com/products
http://server.com/orders

```
    public class UserServlet extends HttpServlet {
        public void doGet(HttpServletRequest req, HttpServletResponse res)  {

        }
         public void doPost(HttpServletRequest req, HttpServletResponse res)  {
            
        }
         public void doPut(HttpServletRequest req, HttpServletResponse res)  {
            
        }
         public void doDelete(HttpServletRequest req, HttpServletResponse res)  {
            
        }
    }

HttpServlet has default implementations for all the doXXX() methods ==> returns 400 BAD REQUEST
```

IDEMPOTENT: multiple identical requests to an endpoint have the same effect as a single request.

GET is IDEMPOTENT
PUT / POST : not IDEMPOTENT

GET and DELETE are safe methods

Mapping an URL to Servlet:

1) Deployment descriptor --> web.xml
```
    <servlet>
        <servlet-name>First</servlet-name>
        <servlet-class>pkg.UserServlet</servlet-class>
    </servlet>

    <servlet-mapping>
         <servlet-name>First</servlet-name>
         <url-pattern> /register, /login </url-pattern>
    </serlvet-mapping>
      <servlet>
        <servlet-name>Sec</servlet-name>
        <servlet-class>pkg.ProductServlet</servlet-class>
    </servlet>

    <servlet-mapping>
         <servlet-name>Sec</servlet-name>
         <url-pattern> /products </url-pattern>
    </serlvet-mapping>
```
2) Annotation 

@WebServlet("/products")
public class ProductServlet extends HttpServlet {}

GET 
http://localhost:8080/products


HttpServletRequest and HttpServletResponse objects are created by the engine and injected to the servlet's method
HttpServletRequest encapsulates data from client [ client data, browser, OS]
HttpServletResponse is used to write data back to client
===========================================

MySQL:
```
Add mysql/bin to path

mysql -u root -p

mysql> create database AD_JAVA;
mysql> use AD_JAVA;
mysql> create table products (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), price double);
```
===================

Maven based project:
Maven is a build tool, manage dependencies for the project, running goals like compilation, testing, building, ...



mysql-connector-j --> MySQL JDBC drivers 
jakarta.servlet-api --> servlet api to build web application
lombok --> code generation tool, simplifies creating data classes

JAR --> Java Archive --> Library
WAR --> Web Archive --> required to deploy on servlet engine
EAR --> Enterprise Archive

mvn compile --> uses compiler plugin
mvn package --> sees package type as below and uses war-plugin, default is jar-plugin [built-in]

```
<packaging>war</packaging>
```

embedded JETTY server

mvn jetty:run --> compile, package, start jetty server and deploy war package on jetty --> default it runs on 8080 port

http://localhost:8080/hello