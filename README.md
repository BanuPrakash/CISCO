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

Filter: are used as interceptor pattern

* SecurityFilter
* EncryptionFilter
* Encoding/Decoding
...

=========

Day 2

Recap:
JDBC --> Java Database connectivity - Integration Library to connect Java <--> RDBMS
Web Application Development
Servlet Engine / Servlet Container / Web Container
Servlet, deployment descriptor [ web.xml or Annotation]
JSP --> internally its a servlet but allows us to write static + dynamic content
Filter --> Interceptor Pattern [ concern which is not part of main logic but can be used along with main logic like Security, Logging, Encryption, Profile]
HttpSession: to track Conversational State of client

SOLID design Principles
S --> Single Responsibility

MVC Architectural Pattern
M --> Model [ business data + business logic ] --> Entity, service , DAO
V --> View [ presenation] --> JSP pages
C --> Controller --> Flow of your application --> Servlet



Spring Framework:
Lightweight Container which supports dependency Injection using Inversion Of Control to build enterprise applications. 

SOLID design Principles
D --> Dependency Injection

Metadata
XML / Annotation

Example of Depenedency Injection using XML as metadata:
```
    interface EmployeeDao {
        void addEmployee();
    }

    public class EmployeeDaoJdbcImpl implements EmployeeDao {
        public void addEmployee() {
            // logic to insert into RDBMS
        }
    }

      public class EmployeeDaoMongoDbImpl implements EmployeeDao {
        public void addEmployee() {
            // logic to insert into MongoDB --> NoSQL
        }
    }

    Setter DI
    public class AppService {
        private EmployeeDao empDao;

        public void setEmpDao(EmployeeDao dao) {
            this.empDao = dao;
        }

        public void insert() {
            this.empDao.addEmployee();
        }
    }
```

Metadata --> XML --> beans.xml
```
    <beans>
            <bean id="jdbc" class="pkg.EmployeeDaoJdbcImpl" />
            <bean id="mongo" class="pkg.EmployeeDaoMongoDbImpl" />
            <bean id="service" class="pkg.AppService">
                <property name="empDao" ref="mongo" />
            </bean>
    </beans>
```
To create Spring Container:

ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

AppService ser = ctx.getBean("service", AppService.class);
ser.insert();

Constructor DI:
```
 public class AppService {
        private EmployeeDao empDao;
        public AppService(EmployeeDao dao) {
            this.empDao = dao;
        }
        public void insert() {
            this.empDao.addEmployee();
        }
    }

  <beans>
            <bean id="jdbc" class="pkg.EmployeeDaoJdbcImpl" />
            <bean id="mongo" class="pkg.EmployeeDaoMongoDbImpl" />
            <bean id="service" class="pkg.AppService">
                <constructor index="0" ref="mongo" />
            </bean>
    </beans>

```

Annotations as Metadata:
Spring instantiates if it finds any of the below annotations at class level:
1) @Component
2) @Repository
3) @Service
4) @Configuration
5) @Controller
6) @RestController
7) @ControllerAdvice


Example:


```
 interface EmployeeDao {
        void addEmployee();
 }

 @Repository
 @Scope("prototype")
 public class EmployeeDaoJdbcImpl implements EmployeeDao {
        public void addEmployee() {
            // logic to insert into RDBMS
        }
}

@Service
 public class AppService {
        @Autowired
        private EmployeeDao empDao;
       
        public void insert() {
            this.empDao.addEmployee();
        }
    }


ApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.scan("com.cisco.prj");
ctx.refresh();

AppService ser = ctx.getBean("appService", AppService.class);
ser.insert();
```

Spring Boot depends on libraries like ByteBuddy / JavaAssist and CGLib for wiring and creating proxies

Byte Buddy is a code generation and manipulation library for creating and modifying Java classes.

Scope of Bean:
1) Singleton <<default>>
2) Prototype





@Service
 public class AppService {
        @Autowired
        private EmployeeDao empDao;
       
        public void insert() {
            this.empDao.addEmployee();
        }
    }

@Service
 public class AdminService {
        @Autowired
        private EmployeeDao empDao;
       
        public void insert() {
            this.empDao.addEmployee();
        }
    }

=================

Advantage of using @Repository instead of @Component for DAO layer code.
https://github.com/spring-projects/spring-framework/blob/main/spring-jdbc/src/main/resources/org/springframework/jdbc/support/sql-error-codes.xml


Spring boot:
Spring Boot is a framework on top of Spring Framework.

Spring Boot 2.x is built on top of Spring Framework 5.x
Spring Boot 3.x uses Spring Framework 6.x

Spring Boot is highly opiniated framework, lots of configurations are done out of the box.
For Example if we are building web based application, Embedded Tomcat Servlet container / web Server is configured out of box.

If we are using JDBC, database Connection pool is created out of the box

 DriverManager.getConnection(URL, USER, PASSWORD); --> opens a single connection

 latency involved in opening and closing connection is solved by using connection pool.

 many more ...

 ==========================


@SpringBootApplication is 3 in 1:
1) @Configuration
2) @ComponentScan ==> by default adds package where this class resides and sub-packages to scan
2) @EnableAutoConfiguration ==> used to create opinated beans based on Context [ Connection pool if DB is used, Tomcat Embedded Server if web application is built]

SpringApplication.run(DemoApplication.class, args); is same as "new AnnotationConfigApplicationContext();"

Problem:

Field employeeDao in com.cisco.demo.service.AppService required a single bean, but 2 were found:
	- employeeDaoJdbcImpl
	- employeeDaoMongoImpl


Solution 1:
using @Qualifier

```

@Service
public class AppService {
    @Autowired
    @Qualifier("employeeDaoJdbcImpl")
    private EmployeeDao employeeDao; // dependency , loosely coupled

```

Solution 2:
using @Primary

```
@Repository
@Primary
public class EmployeeDaoMongoImpl implements EmployeeDao{

@Repository
public class EmployeeDaoJdbcImpl implements  EmployeeDao{


@Service
public class AppService {
    @Autowired
    private EmployeeDao employeeDao;

```

Solution 3:
using @Profile

```
@Repository
@Profile("prod")
public class EmployeeDaoMongoImpl implements EmployeeDao{

@Repository
@Profile("dev")
public class EmployeeDaoJdbcImpl implements  EmployeeDao{

resources>
application.properties
spring.profiles.active=dev

OR

Main class -> Right click --> More Run/Debug --> Modify Configurations ==> Active Profile [ dev or prod]

profile resolving:
1) Command Line Argument / Configuration 
2) Environment Variable [ System Env]
3) application.properties

```

Solution 4:
@ConditionalOnMissingBean

```
@Repository
public class EmployeeDaoJdbcImpl implements  EmployeeDao{

@Repository
@ConditionalOnMissingBean(EmployeeDaoJdbcImpl.class)
public class EmployeeDaoMongoImpl implements EmployeeDao{

```

Factory Method: are methods which create  and return an object.
Why? 
* when we use 3rd party classes in project which doesn't have any of the above mentioned 7 annotations

https://www.mchange.com/projects/c3p0/

DriverManager.getConnection() --> create a single Connection
DataSource --> Pool of database connection
https://www.mchange.com/projects/c3p0/

```
<dependency>
			<groupId>com.mchange</groupId>
			<artifactId>c3p0</artifactId>
			<version>0.10.1</version>
</dependency>
```

ORM Framework

Object Relational Mapping 

Object [ Java / Python / C# / C++ ] <--> Relational database
CRUD Operations are generated by the ORM framework

Example:
```
    @Entity
    @Table(name="customers")
    public class Customer {
        @Id
        private String email;

        @Column(name="FNAM", length=100)
        private String firstName;
    }

    @Override
    public void addProduct(Product p) throws PersistenceException{
        em.persist(p);
    }

``

ORM Frameworks:
1) Hibernate --> JBOSS --> redHat
2) TopLink --> Oracle
3) KODO --> BEA --> Oracle
4) JDO --> Sun MS --> Oracle
5) OpenJPA --> Apache
6) EclipseLink --> Eclipse
.....

ApplicationContext: environment where beans are managed. [ above mentioned 7 annotations]

JPA Specification interface for ORM

PersistenceContext: environment where entities are managed [@Entity]
EntityManager is an interface which is used to manage PersistenceContext
DataSource: pool of database connection
EntityManagerFactory is a factory class to create PersistenceContext

JpaVendor
[Hibernate / KODO / OpenJPA/ TopLink]

Spring Boot provides Spring Data JPA.

Spring Data JPA: simplifies using JPA.
out of the box "Spring Data JPA" creates DataSource, EntityManagerFactory, PersitenceContext...

=====

New Spring Boot project with following dependencies:
1) lombok
2) MySQL
3) Spring Data JPA

https://docs.spring.io/spring-boot/appendix/application-properties/index.html

spring.jpa.hibernate.ddl-auto=update
Data Defintion Language : create , drop , alter table

update--> if for a class mapped to database table exists --> use it
if not exist --> create table
if required alter the table

create --> drop tables on termination of application, create tables when application starts [ used only in test environment]

validate --> map classes to existing tables. If matches use it, if doesn't match throw error. no changes to existing tables

validate ==> Bottom to Top Appraoch [write classes to match tables]
update ==> Top to Bottom Approach and hybrid

================

1) 
@JoinColumn with @ManyToOne will introduce FK in owning table
  @ManyToOne
  @JoinColumn(name="customer_fk") // FK
  private Customer customer; // order is by a given Customer


2)
@JoinColumn with @OnetoMany will introduce FK in child table
 @OneToMany
 @JoinColumn(name="order_fk")
 private List<LineItem> items = new ArrayList<>();


=============

Day 3

Recap:

```
ORM Framework , JPA Specification for ORM
* @Entity and @Id are compulsory
* Optinally @Column can be used to map Java property to a database table column, if not done name of the java property will be taken for column name in table
* Association Mapping
1) one-to-many @OneToMany
2) many-to-one @ManyToOne
3) one-to-one @OneToOne
4) many-tomany @ManyToMany


Order ---> LineItem [ one-to-many]
Order --> Customer [ many-to-one]
LineItem --> Product [many-to-one]

@JoinColumn is used to introduce a FK in table, if used along with @OneToMany FK is introduced in child,
Whereas if @JoinColumn is used with @ManyToOne FK is introduced in Owning table.

JpaRepository ==> we just write interface, implementation classes are generated by the Spring Data JPA

``
Assume 1 order has 4 LineItems
1) Save
orderDao.save(order);
itemDao.save(item1);
itemDao.save(item2);
itemDao.save(item3);
itemDao.save(item4);

2) delete
orderDao.delete(order);
itemDao.delete(item1);
itemDao.delete(item2);
itemDao.delete(item3);
itemDao.delete(item4);

Cascade
 @OneToMany(cascade = CascadeType.ALL)
 @JoinColumn(name="order_fk")
 private List<LineItem> items = new ArrayList<>();

Assume 1 order has 4 LineItems
1) Save
orderDao.save(order); ==> takes care of saving line items also
2) delete
orderDao.delete(order); --> takes care of deleting line items also

====
By default OneToMany association are LAZY fetched
Fetching Orders
orderDao.findById(1, Order.class);
select * from orders where oid = 1;
orderDao.findAll();
select * from orders;

With EAGER Fetching
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name="order_fk")
private List<LineItem> items = new ArrayList<>();

1) orderDao.findById(1, Order.class);
select * from orders where oid = 1; also 
select * from line_items where order_fk = 1;

2) orderDao.findAll();
select * from orders;
select * from line_items where order_fk = 1;
select * from line_items where order_fk = 2;
select * from line_items where order_fk = 3;
...

With the above 2 options we don't need ItemDao

Service classes acts as a facade over DAO operations, decides what needs to be exposed to role based users.
Service classes are used for combining many fine-grained operations into one course grained operation

AccountService.java
```
// below code is ATOMIC
// one call from client
void transferFunds(Account fromAcc, Account toAcc, double amt) {
    // select for getting balance
    select balance from fromeAcc;
    if balnce is less that amt --> throw InfufficeintBalanceException
    else
    update fromAcc
    update toAcc
    insert into transaction_table ...
    send SMS
    send Email
}
```
JP-QL:

  @Query("select new com.cisco.orderapp.dto.ReportDTO(c.email, c.firstName, c.lastName, o.orderDate, o.total) 
        from Order o inner join o.customer c")
    List<ReportDTO> getReport();

========================

Client Side Rendering
Advantages: Heterogenous clients like Web , Mobile, Tv, Desktop, Hardware....

Building RESTful WS

REpresentational State Transfer [REST]

* Resource is present on server: database / file / printer/ images
Anything present on server and can be named is a resource

* Representation: state of the resource at a given point of time

* ContentNegotiation: representation of resource is served to the client in various formats like XML / JSON / CSV ...
How client asks for content is using HTTP Header [MIME]
Accept: text/xml
Accept: application/json

Resources are identified using a URL [ uniform URL]
Resources are generally named as plural nouns
http://localhost:8080/api/products
http://localhost:8080/api/customers
http://localhost:8080/api/orders

HTTP methods --> verbs --> Action
GET, POST, PUT, DELETE

CREATE --> POST
READ --> GET
UPDATE --> PUT / PATCH
DELETE --> DELETE


Examples of Usage:
1) GET http://localhost:8080/api/products

get all products

2) PathParameter is used to fetch by primary key
GET http://localhost:8080/api/products/3

get a product whose id is 3

3 --> path parameter / path variable

3) Query Parameter is used to get subset / filtered data

Pagination:
GET http://localhost:8080/api/products?page=1&size=20
Products by range:
GET http://localhost:8080/api/products?low=3000&high=10000

? --> Query Parameter / Request Param
& --> delimiter between each parameter

4) POST http://localhost:8080/api/products
payload contains a new product data to be added to products resource

5) DELETE http://localhost:8080/api/products/4
delete a product whose id is 4

6) PUT http://localhost:8080/api/products/2
payload contains a new product infomartion to update a products whose id is 2

Http Headers:
1) Accept --> to ask server to send the data in a particular format
Accept: application/json

2) content-type: what type of payload client is sending to the server
Content-type: text/xml
client is sending xml to server

=========
```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

By adding above depenency we get:
1) Servlet api
2) Embedded Tomcat Web Server
3) Spring MVC module
4) Jackson library
to convert Java <--> JSON
alternate libraries available: GSON / Jettison / Moxy ...


DispatcherServlet

Controller/RestController

Controller --> SSR
RestController --> CSR

```

@RestController
@RequestMapping("api/products")
public class ProductController {
    
    @GetMapping
    public List<Product> getProducts() {
        ...
    }

    @PostMapping
    public Product addProduct(@RequestBody Product p) {
        ..
    }
}

```

ContentNegotiationHandlers
Jackson for JSON
JAXB .. for XML
ResponseBody

Content-type: application/json
{
    name: 'AProduct',
    price: 792.44,
    quantity: 100
}

Status Code:
200 --> OK
201 --> CREATED
400 ---> BAD REQUEST
401 --> not Authorized
404 --> RESOURCE NOT FOUND
500 --> INTERNAL SERVER ERROR
300 series is redirection

To test GET request we can use Browser
To test POST / PUT / DELETE we need clients like POSTMAN or INTELLIJ IDE or any any other client

http://localhost:8080/api/products?low=100&high=5000



Handling:
GET http://localhost:8080/api/products/5
```
OrderService.java
public Product getProductById(int id) {

ProductController.java
@GetMapping("/{id}")
public Product getById(@PathVariable("id") int id) {

```

PUT http://localhost:8080/api/products/5
Payload should contain new data
```
{
    "price" : 4211.11
}

OrderService.java
  @Transactional
    public void updateProduct(int id, Product p) {

ProductController.java
  @PutMapping("/{id}")
    public  Product updateProduct(@PathVariable("id") int id, @RequestBody Product p) {
   
```

Handling Exception:
GlobalExceptionHandler.java

Using @ControllerAdvice Classes
A controller advice allows you to handle exception thrown from @Controller or @RestController. 
You can think of them as an annotation driven interceptor.

=======

Adding Validation:
https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @Min(value = 10, message = "Price entered ${validatedValue} should be more than {value}")
    private double price;

    @Min(value = 1, message = "Quantity entered ${validatedValue} should be more than {value}")
    @Column(name="qty")
    private int quantity; // column will be added in table
}

@PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Product addProduct(@RequestBody @Valid Product p) {
        return service.saveProduct(p);
    }
```


MethodArgumentNotValidException: 

 [Field error default message [Price entered -210000.0 should be more than 10]] 
 
 [Field error default message [Name is required]] 
 
 [Field error default message [Quanti    should be more than 1]] 

 =============

Rental Application:

customers

email | fname | lname


vehicles

reg_no | fuel_type | cost_per_day


rentals

id | customer_fk | vehicle_fk | rent_from | rent_to

start.spring.io
depdenencies
lombok, web, jpa, mysql, validation

========================


Cannot deserialize value of type `java.util.Date` from String "2024-4-21": not a valid representation (error: Failed to parse Date value '2024-4-21': Cannot parse date "2024-4-21": while it seems to fit format 'yyyy-MM-dd', parsing fails (leniency? null))]

https://databases.biz/data-models/

* Assign employee to Project
Employee 
Project

EmployeeProject ==> Similar to Rental

id | employee_fk | project_fk | role        | start_date   | end_date 
1   sam@cisco       PRJ1        SR.ENG          3-10-2023    null



* Bug Tracker
Employee raises a ticket
Another employee will resolve a ticket

================
```
package com.example.shopapp.cfg;


import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration("productCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
                .withCacheConfiguration("customerCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
    }
}

```

Testing
Caching
Async request handling
...

Day 4

Recap of Day 3:
```
    Building RESTful WS.
    @RestController, @RequestMapping, @GetMapping, @PostMapping, @PutMapping
    @DeleteMapping, @Patchmapping

    @RequestBody ==> to convert payload to entity based on Content-type
    @PathVariable to read Path Parameter /
    @RequestParam to read Query parameters ?

    @ControllerAdvice and @ExceptionHandler
    ResponseEntity ==> data + headers + status code

    @Transactional --> method level to specify that code in method is atomic in nature [ commit or rollback]
    Dirty Checking --> within a Tranctional boundary if an entity changes [dirty] JPA will flush the new state of entity to database by issuing UPDATE SQL
```



PATCH vs PUT for Updating

PUT to update entity --> use this if entity has less attributes and major update is happening
{
  "biscuits": [
    { "name": "Digestive" },
    { "name": "Choco Leibniz" }
  ]
}

to Update:
payload:
{
  "biscuits": [
    { "name": "Digestive" },
    {"name" , "Ginger Nut"},
    { "name": "Choco Leibniz" }
  ]
}
PATCH is a technique for updating the resources when the client transmits partial data that will be updated without changing the whole data.

```
<!-- https://mvnrepository.com/artifact/com.github.java-json-tools/json-patch -->
<dependency>
    <groupId>com.github.java-json-tools</groupId>
    <artifactId>json-patch</artifactId>
    <version>1.13</version>
</dependency>

```
https://jsonpatch.com/

{ "op": "add", "path": "/biscuits/1", "value": { "name": "Ginger Nut" } }

Operations: add, remove, replace, copy, move, test

mapper.writeValueAsString(employee) ==> Employee to JSON

```
{
    "id":123,
"title":"Sr.Programmer",
"personal":{"firstName":"Smitha","lastName":"Patil","phone":"1234567890"},
"programmingSkills":["Java","Python"]
}

``