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

mvn jetty:run -Djetty.http.port=1234

src/main/webapp --> folder for static resources like html / css and JS

JDBC --> integration library for java to connect to RDBMS
JDBC provides only interfaces, implementation classes are provided by database vendors

Steps to connect to database:
1) load drivers 
2) establish a database connection
3) Send SQL for DML
3.1) Statement interface if SQL is fixed and same for all request
3.2) PreparedStatement if SQL takes IN parameters from User
4) ResultSet is a cursor for fetched records from database if SELECT statement is used
5) close the connection


Recap:
Maven based java project.
pom.xml --> dependency, compiler, packaging [default is jar], plugins for war, jetty.

mvn clean
mvn compile
mvn package
mvn jetty:run

MVC Architecture pattern
M --> Model --> Business data and logic
V --> View --> JSP Java Server Pages [ Presentation logic]
C --> Controller [Servlet's are good for Controller] --> application logic

Don't put presentation logic in Servlet

JSP can be used for Server Side Rendering of pages. In JSP we can have static + dynamic content

=======================

Spring Framework : Current version 6.x
Spring Boot : Current version 3.x

Spring is a light weight container for building enterprise applications.

Spring core module container provides life cycle management of beans and wiring depenencies.

Servlet engine also manages lifecycle of Servlet's and wires request and response objects. Can't manage or wire used specific classes like DAO, service, ...

Term bean: any object which is managed within the spring container is termed as a bean.

What is Dependency Injection?
All real world objects works on DI

Why DI?
* Loose coupling
* Switching between strategies is easy
* Testing in isolation


========

Spring needs metadata for managing beans - XML or annotation

```
    public interface EmployeeDao {
        void addemployee(Employee e);
    }

    public class EmployeeDaoJdbcImpl implements EmployeeDao {
         public void addemployee(Employee e) {...}
    }

     public class EmployeeDaoMongoImpl implements EmployeeDao {
         public void addemployee(Employee e) {...}
    }

    public class AppService {
        private EmployeeDao empDao;

        private void setEmpDao(EmployeeDao edao) {
            empDao = edao;
        }

        public void insert(Employee e) {
            empDao.addemployee(e);
        }
    }

beans.xml
<beans>
    <bean id="jdbc" class="pkg.EmployeeDaoJdbcImpl" />
    <bean id="mongo" class="pkg.EmployeeDaoMongoImpl" />
    <bean id="service" class="pkg.AppService">
            <property name="empDao" ref = "jdbc" />
    </bean>
</beans>

Statement to create Spring container:
ApplicationContext ctx = new ClassPathApplicationContext("beans.xml");

AppService service = ctx.getBean("service");

```

Annotation as metadata:
Spring instantiates classes which contain one of these annotations at class level:
1) Component
2) Repository
3) Service
4) Controller
5) RestController
6) Configuration
7) ControllerAdvice

```
    @Repository
    public class EmployeeDaoJdbcImpl implements EmployeeDao {
         public void addemployee(Employee e) {...}
    }

    @Service
     public class AppService {
        @Autowired
        private EmployeeDao empDao;

        public void insert(Employee e) {
            empDao.addemployee(e);
        }
    }

    Default behaviour of  @Autowired is to check if the container has any object of the type and wire

    ApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.scan("com.cisco.prj");
    ctx.refresh();
    AppService service = ctx.getBean("appService");
```

Why Spring Boot?
Highly opiniated framework on top of spring framework, lots of configuration comes out of the box.
Examples:
1) Unit testing framework - JUnit is bundled by default, for mocking mockito
2) Database applications --> Connection pool is configured by default
3) If we are building web applications ==> Tomcat is configured as default servlet engine
4) Easy to dockerize 
5) Writing standalone and web applications are similar [main is the entry point]

Option 1 of creating Spring boot applicaiton:
File --> New --> Project --> Spring Initializer

Option 2:
https://start.spring.io/


@SpringBootApplication is 3 in one:
1) @Configuration
2) @ComponentScan
3) @EnableAutoConfiguration --> default opiniated config like DB connection pool

SpringApplication.run(DemoApplication.class, args); creates Spring container

Description:

Field employeeDao in com.example.demo.service.AppService required a single bean, but 2 were found:
	- employeeDaoDbImpl: 
	- employeeDaoMongoImpl:

Solution 1:
using @Primary
```
@Repository
@Primary
public class EmployeeDaoMongoImpl implements EmployeeDao{

@Repository
public class EmployeeDaoDbImpl implements EmployeeDao{

```

Solution 2:
using @Qualifier
```
@Repository
public class EmployeeDaoMongoImpl implements EmployeeDao{

@Repository
public class EmployeeDaoDbImpl implements EmployeeDao{

@Service
public class AppService {
    @Autowired
    @Qualifier("employeeDaoDbImpl")
    private EmployeeDao employeeDao; // wiring is done by spring container

```

@Profile
@ConditionalOnMissingBean
JPA with Hibernate

Recap:
1) JDBC: Integration library to connect to database. interfaces are provided by java  and implemenation classes are provided by database vendors [oracle / MS/ Postgres /..]
Connection, DriverManager, Statement, PreparedStatement [ IN parameters]

2) Servlet API for building web based applications.
3) Servlet engine: Jetty / Tomcat / Netty ...
4) HttpServlet, HttpServletRequest and HttpServletResponse

Spring Framework and Spring container --> dependency Injection
Spring Boot
XML vs Annotation as metadata.

Spring Initializer https://start.spring.io/ for creating a scaffolding code.

If more than one bean is eligible for wiring we get

Field employeeDao in com.example.demo.service.AppService required a single bean, but 2 were found:
	- employeeDaoDbImpl: 
	- employeeDaoMongoImpl:

Resolve:
1) @Primary
2) @Qualifier

================

Bean: Sun MS gave the definition as a reusable software component. Meaning the object can be used in any enviroment like web, standalone, mobile,...

Bean: from Spring perspective
any object which is managed by spring container is termed as a bean.
For example:
Employee e = new Employee(); // not a bean from Spring perspective


Day 2:
Resolve:
1) @Primary
2) @Qualifier
3) @ConditionalOnMissingBean
```
@ConditionalOnMissingBean(EmployeeDaoDbImpl.class)
public class EmployeeDaoMongoImpl implements EmployeeDao{
```
4) @Profile
```
@Repository
@Profile("prod")
public class EmployeeDaoDbImpl implements EmployeeDao{

@Repository
@Profile("dev")
public class EmployeeDaoMongoImpl implements EmployeeDao{

resources/application.properties
spring.profiles.active=dev

OR use Command Line arguments
java com.cisco.proj.DemoApplication  --spring.profiles.active=dev

```

Scope of beans:
1) Singleton [ default ]
2) Prototype
3) request [ only for web ]
4) session [ only for web ]
5) application [ only for web ]

Singleton: one bean of a type within the container
```
@Scope("singleton")
public class EmployeeDaoDbImpl implements EmployeeDao {

@Service
public AService {
    @Autowired
    EmployeeDao empDao;
}

@Service
public BService {
    @Autowired
    EmployeeDao empDao;
}
```

Prototype: each place we wire, we get a different instance
```
@Scope("prototype")
public class EmployeeDaoDbImpl implements EmployeeDao {

@Service
public AService {
    @Autowired
    EmployeeDao empDao;
}

@Service
public BService {
    @Autowired
    EmployeeDao empDao;
}
```

Factory Methods: object returned from a factory method is managed by Spring container [ bean]
 @Bean on top the method 

Why factory method in Spring?
* 3rd party APIs provided classes can't have any spring specific annotation
* Object instantiation is complex

DataSource : pool of database connections, prefer this instead of DriverManager.getConnection() is a single connection


===============================

ORM: Object Relational Mapping
ORM, or object-relational mapping, is a programming method used to bridge the gap between object-oriented programming languages (like Python, Java, Ruby, C++, JavaScript, C#, and many more) and relational databases (like PostgreSQL, MySQL, or SQLite). Simply put, ORM allows developers to manipulate data using Java or Python instead of SQL statements.

ORM simplifies CRUD operations for RDBMS.

Mapping
class <--> table
instance variables <---> columns of table

@Id --> is to mark a Primary Key

ORM generates SQLs

ORM frameworks available for Java Developers:
1) Hibernate --> JBoss --> RedHat
2) TopLink --> Oracle
3) KODO --> BEA --> Oracle
4) JDO --> Sun Microsystems --> Oracle
5) EclipseLink --> Eclipse
6) OpenJPA --> Apache

JPA --> Java Persistence API a specification for ORMs

Application --> JPA --> ORM --> JDBC --> RDBMS

===========

Spring Framework:

```

@Configuration
public class AppConfig {

    @Bean
    public DataSource getDataSource()  {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
            cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/AD_JAVA" );
            cpds.setUser("root");
            cpds.setPassword("Welcome123");
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            return  cpds;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  null;
    }

    @Bean
    public EntityManagerFactory getEmf() {
        LocalContainerEntityManagerFactory emf = new LocalContainerEntityManagerFactory();
        emf.setDataSource(getDataSource());
        emf.setJpaVendor(new HibernateJpaVendor()); // which ORM you want
        ...
        return emf;
    }
}


@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao{
    @PersistenceContext
    EntityManager em;
      @Override
    public void addEmployee(Employee e) {
        em.perist(e);
    }

    public List<Product> getProducts() {
        return em.getResultList()...
    }
```

Spring Data JPA, makes it easy to easily implement JPA-based (Java Persistence API) repositories.

New Spring project
dependencies:
1) data-jpa
2) mysql
3) lombok



Spring boot uses HikariCP as Connection pool like C3p0 ComboPooledDataSource.
It creates database connection pool based on entries present in application.properties


spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
This is to inform ORM to generate SQL for MySQL database

spring.jpa.hibernate.ddl-auto=update
DDL --> create / alter / drop
update --> 
a) create tables based on mapping if not exist 
b) if table exists map to existing one
c) if required alter table [ like add column, change length, ...]


spring.jpa.hibernate.ddl-auto=create
create ->
drop tables on application termination and create new ones for every start of application

spring.jpa.hibernate.ddl-auto=validate
validate ->
map to existing tables, don't allow create or alter tables

Note:
any package you create has to be a subpackage of package where main() is present

Example main is in package com.cisco.orderapp;
valid packages are 
com.cisco.orderapp.entity;
com.cisco.orderapp.repo;
com.cisco.orderapp.service;
com.cisco.orderapp.a.b.c;

invalid are:
com.example.repo;
com.cisco.repo;

====================

With Spring Data JPA we just create interfaces extends JpaRepository,
Implementation classes i.e @Repository classes are created by Spring Data Jpa
```
public interface ProductRepo extends JpaRepository<Product, Integer> {
}

CRUD operations like save, delete, findAll(), findByID() , ...

```
