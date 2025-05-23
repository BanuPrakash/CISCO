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

CommandLineRunner is a simple Spring Boot interface with a run method. Spring Boot will automatically call the run method of all beans implementing this interface after the application context [spring container] has been loaded.

Issue with Lombok:
Settings -> Compiler -> Annotation Processors
Enable select
Obtain processors from classpath select
```

update products set qty = 100 where 1 = 1;

Mapping associations and Transactions

===============================

JPA Projections and Custom Queries

SQL is based on table and columns
JP-QL is based on class name and it's fields [ Case-sensitive ]

By default built-in methods for INSERT and DELETE are Transactional.
Custom query or custom methods of performing INSERT, DELETE or UPDATE needs to be marked as @Transactional generally done in Service Tier code.

Update using DIRTY CHECKING
Dirty checking is a mechanism in ORM that automatically detects and synchronizes changes made to the entities within Transactional boundaries without requiring explicit update queries. 

https://www.database-answers.com/data_models/

https://martinfowler.com/bliki/DomainDrivenDesign.html
https://martinfowler.com/bliki/BoundedContext.html

orders and lineitems

Identify Root aggregate and entity mappings for : Uber / Ola / BlueDrive

 @JoinColumn used with ManyToOne introduces FK in owning table / parent
 @JoinColumn used with OneToMany introduces FK in child table

========

Without Cascade:
```
Order {
     @OneToMany
    @JoinColumn(name="order_fk")
    private List<LineItem> items = new ArrayList<>();
}
```

assume one order has 4 items.
Inserting:
```
orderDao.save(order);
itemDao.save(i1);
itemDao.save(i2);
itemDao.save(i3);
itemDao.save(i4);
```

Delete:
```
orderDao.delete(order);
itemDao.delete(i1);
itemDao.delete(i2);
itemDao.delete(i3);
itemDao.delete(i4);
```

======

With Cascade:
```
    Order {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_fk")
    private List<LineItem> items = new ArrayList<>();
    }

```


assume one order has 4 items.
Inserting: order saves its line items
orderDao.save(order);


Delete: deleting order deletes its line items also.
orderDao.delete(order);

=========================

EAGER and LAZY FETCHING.
By default one to many is LAZY LOADING/FETCHING
By default ManyToOne is EAGER fetching

orderDao.findById(1); // select * from orders where oid = 1;
items are not fetched.
Customer is fetched.

EAGER
```
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="order_fk")
    private List<LineItem> items = new ArrayList<>();


orderDao.findById(1); // select * from orders where oid = 1;
items of the orders are also fetched 
```

@Transactional --> Atomic operation
All operatins in methods commits or rollback
* Save Order
* save items
* update product qty

==========================

Building RESTful WS for orderapp
AOP
Validation
ExceptionHandling

//    @Query(nativeQuery = true,
//            value = "select c.fname, c.email, o.order_date, o.total  " +
//                    " from orders o inner join customers c " +
//                    " on c.email = o.customer_fk")
//    List<Object[]> getReport();



=============================

Recap:
Spring Boot application with JPA.
Spring Data JPA module when configured in Spring Boot Framework provides following things out of box:
1) DataSource --> Pool of Database connections HikariCP
2) Hibernate --> as the default ORM provider
3) Configures EnitityManagerFactory --> LocalContainerEntityManagerFactory
4) JpaRepository interfaces, we just need to create interfaces extending this, implementation classes are generated by Spring Data JPA [No need for @Repository classes]
5) JPA Projections are based on methods startingwith findByXXXXX (XXXXX has to be a field of entity along with And / Or/ between operations can be used)
6) @Query to write custom queries using SQL or JP-QL
7) @Modifying for INSERT, dELETE and UPDATE queries
8) Dirty Checking: within @Transactional boundary if entity undergoes a change in state, automatically UPDATE SQL is sent to database implicilty
9) Mappings:
@ManyToOne
@OneToMany
@JoinColumn --> for FK
10) Cascade
11) EAGER and LAZY loading

====================

Day 3

Building RESTful WS

Spring Web MVC Module provides
* DispatcherServlet: intercepts all http requests from client [ url-pattern = * ]
* HandlerMapping : mapping a URL to @Controller or @RestController 
* Jackson library for Java <---> JSON
* Tomcat is configured as default servlet engine

method.invoke();

JAXB / XML libraries should be explicitly configured for Java <--> XML

Alternates to Jackson: GSON, Jettison, moxy

```
    @RestController
    @RequestMapping("api/products")
    public class ProductController {
        private final OrderService orderService;
        @GetMapping() 
        public List<Product> getProducts() {
            return orderService.getProducts();
        }

        @PostMapping()
        public Product addProduct(@RequestBody Product p) {
            return orderService.addProduct(p);
        }
    }

```

REST --> REpresentational State Transfer -- architectural style for distributed hypermedia system.

Resource: any info on server which can be named like document, printer, database...

Representation: state of the resource at given time

We can serve the representation in various formats like JSON / XML

Advantage of using RESTful over traditional web application is we can have different clients consuming the data like tv / mobile / web / desktop

Guiding Principles of REST:
1) Uniform Interface: uniquely identify each resource and used for interaction between client and server
2) Client-server: seperation of concerns, helps client and server to evolve independently.
3) Stateless: There should not be a conversational state like session tracking
4) Cacheable : Client side / Middleware / Serverside cache
5) Layered system


POSTMAN
https://www.postman.com/downloads/


Controller are for Traditional web application which returns Pages like HTML / PDF
RestController is for RESTful WS to return various formats of representation like JSON / XML

Plural Nouns to identify a resource [ products, orders, customers, ...]
@GetMapping() @PostMapping() @DeleteMapping() @PutMapping() , @PatchMapping() 

Put is for major update, Patch is for partial updates

Validation:

```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
 </dependency>

Settings --> Compiler --> Project --> Update Annotation Processor

public  Product addProduct(@RequestBody @Valid  Product p) {
        return  orderService.saveProduct(p);
    }

Product.java
    @NotBlank(message = "Name is required!!!")
    private  String name;

    @Min(value=10, message = "Price ${validatedValue} should be more than {value}")
    private  double price;

    
    @Column(name="qty")
    @Min(value=0, message = "Quantity ${validatedValue} should be more than {value}")
    private int quantity;
```

.w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public com.cisco.orderapp.entity.Product com.cisco.orderapp.api.ProductController.addProduct(com.cisco.orderapp.entity.Product) with 2 errors: [Field error in object 'product' on field 'name': rejected value [null]; codes [NotBlank.product.name,NotBlank.name,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [product.name,name]; arguments []; 
default message [name]]; default message [Name is required!!!]] 

[Field error in object 'product' on field 'price': rejected value [0.0]; codes [Min.product.price,Min.price,Min.double,Min]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [product.price,price]; arguments []; default message [price],10]; 
default message [Price 0.0 should be more than 10]] ]

==============


AOP: Aspect Oriented Programming.
AOP is needed to eliminate Code tangling and code scattering

```
    public Account createAccount(Account fromAcc) {
        if(getRole().equals("ADMIN")) { // SECURITY
            log.info("trying to create an account");
            // actual code to create account
            log.info("account created!!!");
            return fromAcc;
        }
    }

    public void tranferFunds(Account fromAcc, Account toAcc, double amt) {
        log.info("begin transferFunds..");
       Transaction tx = con.beginTransaction();
        try {
            double bal = fromAcc.getBalance();
            if(amt > bal) {
                log.info("insufficient balance!!!";)
            }
            fromAcc.withdraw(...)
            ...

            tx.commit();
        } catch(Exception ex) {
            tx.rollback();
        }


       

    }

```

Aspect: a bit of code which is not a main logic but can be used along with main logic:
Logging, Security, Transaction, Profile, ...

JoinPoint: where aspect can be weaved, eligibility --> Spring method and exception are JoinPoint
PointCut: Where an aspect is weaved
Advice: how to weave it. Before, After, Around, AfterReturning, AfterThrowing

throws EntityNotFoundException

PointCut:
m1, m4 and EntityNotFoundException


public void log(args) {
    log.info(...)
}

Try to simulate @Transactional

===
Aspects are not web aware. No access to HttpServletRequest and HttpServletResponse and hence they can't write data back to client

Spring Web MVC provides @ControllerAdvice a special advice to catch exceptions propageted from @Controller and @RestController and write custom message to client
@ControllerAdvice is a specialized annotation  that enables global exception handling across your entire Spring MVC application.


BindingResult is an interface in Spring that holds the result of a validation process. It contains any errors that may have occurred during the validation and binding of request parameters to an object. It is typically used in conjunction with the @Valid annotation to validate request data.

==============

Spring APIs for Consuming RESTful WS:
1) RestTemplate
2) WebClient
3) RestClient

Problem Statement:
```
Vehicle
REG_NO | type | cost_per_day

DRIVER
ID | NAME | PHONE

CUSTOMER
EMAIL | FNAME | LNAME


RENTAL
ID | RENT_FROM_DATE | RATE_TO_DATE | CUSTOMER_FK | VECHICLE_FK | DRIVER_FK

1) Add Vehicle
2) Add Driver
3) Add Customer
4) RENT a vehicle
ID | RENT_FROM_DATE | RATE_TO_DATE | CUSTOMER_FK        | VECHICLE_FK | DRIVER_FK
124  2025-4-20          NULL         raj@cisco.com          KA12EF141    789

5) RETURN A vehcile --> 124

ID | RENT_FROM_DATE | RATE_TO_DATE | CUSTOMER_FK        | VECHICLE_FK | DRIVER_FK
124  2025-4-20          2025-4-23         raj@cisco.com          KA12EF141    789

```

Day 4:

Recap:
* Different RESTful API mapping
@GetMapping(), @PostMapping(), @PutMapping(), @PatchMapping(), @DeleteMapping()
@RequestBody --> Payload to Java Object based on Content-type
@ResponseBody --> Java to Payload based on Accept [ optional]
@RequestParam --> to read query parameters ?
@PathVariable --> path parameter /data
ResponseEntity --> ResponseBody + additional info like explict headers

* AOP
Aspect, JoinPoint , PointCut, Advice
@ControllerAdvice --> Spring MVC provides this advice which works like AfterThrowing advice to catch any exceptions propagated from @Controller or @RestController

* Validation module 
@Valid, NotBlank, Min, Max, Email, Pattern, ...
MethodArgumentNotValidException
https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary

==============

Unit Testing:
```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

```

Unit testing has to be done by a developer.

When there is a dependency, dependency has to be mocked

Controller --> Service --> Repository --> database

We will test Controller by mocking Service tier code.

Spring Boot provides:
1) JUnit -Jupiter as default Unit testing Framework [TestNG alternate]
2) Mockito for mocking dependencies [ EasyMock / JMock ...]
3) Hamcrest [ assertions which can be used along with default assertions comming from JUnit] good for collections. https://hamcrest.org/JavaHamcrest/tutorial
4) JsonPath https://jsonpath.com/
 
 @WebMvcTest : Annotation that can be used for a Spring MVC test that focuses only on Spring MVC components.
 This annotation doesn't create a complete Spring container but loads only helpers for MVC like HandlerMapping, Jackson, MockMvc.
 Here Service / Repository / Database are not loaded


MockMvc is used to make API calls for testing -- GET / POST / ...

https://medium.com/@truongbui95/jacoco-code-coverage-with-spring-boot-835af8debc68

======================

Caching:
* client side caching
 --> Cache-Control header
 --> ETag
 An ETag (Entity Tag) is a unique identifier assigned by a web server to a specific version of a resource.
 Etag is generally generated as hashCode() or we can use @Version of JPA
    @Version
    private int ver;
    initially ver column will be 0;
    every update triggers an increment in ver column 

    @Version can also be used in avoiding data corruption in multi-user/multi-threaded enviroment

Without Version:
```
    id  name    price   qty
    12   A      1004    100

    User 1:
        get product 12
        buys 3
        qty = 97
    User 2:
         get product 12
         buys 1
         qty = 99
    Based on last commit data will be 97 or 99 [ corrupted ]
```


With Version:
```
    id  name    price   qty   version
    12   A      1004    100     0

    User 1:
        get product 12
        buys 3
        qty = 97

        update products set qty = 97, version = version  + 1 where id = 12 and version = 0

    User 2:
         get product 12
         buys 1
         qty = 99
        update products set qty = 99, version = version  + 1 where id = 12 and version = 0

    First Commit wins; Other users --> StaleStateException

```
server side caching:

Including dependency we get a default ConcurrentHashMapCache Manager.
In case if we include explict cache managers like Redis, this will be disabled

```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
</dependency>

@Configuration
@EnableCaching
public class AppConfig {


 @Cacheable ==> add entry in Cache
 @CachePut --> update the cache
 @CacheEvict --> remove from cache

https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions

```
SPeL ==> Spring Expression Language

Redis as Cache Manager:
Redis on Docker.
```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
 </dependency>

application.properties
spring.data.redis.host=localhost
spring.data.redis.port=6379

```
Level 3 : RESTful WS
HATEOAS (Hypertext As The Engine Of Application State)
https://martinfowler.com/articles/richardsonMaturityModel.html

```
GET /doctors/mjones/slots?date=20100104&status=open

<openSlotList>
  <slot id = "1234" doctor = "mjones" start = "1400" end = "1450">
     <link rel = "/linkrels/slot/book" 
           uri = "/slots/1234"/>
  </slot>
  <slot id = "5678" doctor = "mjones" start = "1600" end = "1650">
     <link rel = "/linkrels/slot/book" 
           uri = "/slots/5678"/>
  </slot>
</openSlotList>

```

Spring Boot HATEOAS:
```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-hateoas</artifactId>
 </dependency>
 EntityModel --> Resource/Entity + Links EntityModel<Product>
CollectionModel --> List<Entity> + Links CollectionModel<List<Product>>
WebMvcLinkBuilder --> programetically add links

HAL [ links ] and HAL_FORMS [affordance -- template]
```

Spring Data REST is part of the umbrella Spring Data project [data-jpa data-mongo] and makes it easy to build hypermedia-driven REST web services on top of Spring Data repositories [JpaRepositry, MongoRepository].

Using Spring Data Rest we don't need to write @RestController
Spring Data Rest doesn't support @RestController instead use BasePathAwareController for adding extra endpoints or over write existing endpoints 


Good for basic Inventory type applications without any business logic [Simple CRUD]

New Spring boot application
* lombok
* mysql
* web
* data-jpa
* data-rest [ Rest respositories]

http://localhost:8080/products/search/findByPriceBetween?low=10&high=50000
http://localhost:8080/products/search/findByQuantity?quantity=100


==================================

Spring Application Events and Async operations.


// Synchrounous Code --> Sequential Execution
```
POST http://localhost:8080/api/discharge
Content-type: application/json

{
    "patientId": "1234",
    "patientName": " George"
}


// tight coupling
public String dischargePatient(String patientId, String patientName) {
    billingService.processBill(patientId); // blocking
    medicalRecordsService.updatePatientHistory(patientId); // blocking
    houseKeepingService.notifyPatientDischarge(); // blocking
    notificationService.notifyPatient(); // blocking
    return "..";
}
```
dischargePatient can generate an ApplicationEvent. BillingService, NotificationService, ... can be ApplicationEventListener

@EnableAsync ==> allows application code to execute in multiple threads [ not Tomcat Threads]
Allows use to use our own Thread pool

Threads Priority ==> 1, 10
By default Thread priorty is 5

Thread t1 = new Thread(Runnable object);
t1.setPriority(8);

Declarative Definition: With @HttpExchange, you define your HTTP client as an interface, annotating methods with HTTP methods and paths.

=================================

Day 4 Recap:
```
Caching: @EnableCache @Cacheable, @CachPut, @CacheEvict. CacheManager --> ConcurrentMapCache, Redis as Cache Manager.

Level 3 RESTful WS --> HATEOAS --> WebMvcLinkBuilder
Spring Data REST Module which works on any Data Repositories like JpaRepository, MonogRepository ...
creates endpoints based on methods of repository interface. No need for explicilty writing RestController. @BasePathAwareController

Async operations in Spring Boot Container.
@EnableAsync --> Spring container by default provides a thread pool. We can expliclty create thread pools using Executors, ExecutorService ...
@Async on top the method is used to specify that the method has to execute using the mentioned Thread pool

@Async("flights-pool")
public CompletableFuture<List<Flight>> getFlights() {...}

RestTemplate, @HttpExchange, @GetExchange, @PostExchange, RestClient [Spring boot 3.0]

```

Spring Security provides APIs for Authentication and Authorization.

```
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
 </dependency>
```
* By including above dependency all resources will be protected.
* creates one user with username="user" and generated password
Using generated security password: 57edc9a5-3f96-461c-9a43-52290bc4e9ea
* creates login and logout pages
http://localhost:8080/logout

https://bcrypt-generator.com/

==========

RESTful WS --> Stateless
How is the server going to identity the client. CLient needs to pass his/her information everytime they make a request
Client request will have -> principle, authorites --> secured Token

JWT : JSON Web Token 

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30

Headers:
{
  "alg": "HS256",
  "typ": "JWT"
}

Payload:
{
  "sub": "roger@cisco.com",
  "iat": 1516239022,
  "exp": 1600023032,
  "iss": "https://auth.cisco.com",
  "authorities": "ADMIN", "MANAGER", ...
}

Signature:
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
    "topsecretsaltvalueforsecurity-abracadabra"
  )

Signature we can use secret key.
We can also use Private Keys and Public Keys
private key --> used by AuthServer --> to generate token
public keys --> used by ResourceServer --> to validate the token

```

JPA for Authentication and authorization

User <--- Many To Many --> Role

entity: User and Role
repo: UserDao
service: UserDetailsServiceImpl, JwtService [validate and generate tokens]

DTOs: SignUpRequest,  SignInRequest

====

Steps of registration:

1) 
```

POST http://localhost:8080/auth/register
Content-Type: application/json
Accept: application/json

{
  "email": "anna@adobe.com",
  "password": "secret",
  "username": "Anna",
  "roles": [ {
    "name": "ROLE_USER",
    "description": "Has basic rights"
  },
    {
      "name": "ROLE_ADMIN",
      "description": "Admin rights"
    }]
}

@RequestMapping("/auth")
public class AuthController {
 private  final AuthenticationService service;

    @PostMapping("/register")
    public String register(@RequestBody SignUpRequest request) {
        System.out.println("Entered!!!");
        return  service.signUp(request);
    }
}
```

2) AuthenticationService:
```
 // register
    public  String signUp(SignUpRequest request) {
          userDao.save(user);
```

3) ```
public class JwtService {
     public String generateToken(UserDetails userDetails) {

```

Login:

```

POST http://localhost:8080/auth/login
Content-Type: application/json
Accept: application/json

{
  "email": "anna@adobe.com",
  "password": "secret"
}

```

1) @RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping("/login")

2) AuthenticationService
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

3) SecurityConfig configured to use JwtAuthenticationFilter

===
Accessing protected Resource:
```
GET http://localhost:8080/api/products
Accept: application/json

Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwZXRlckBhZG9iZS5jb20iLCJpYXQiOjE3NDU1Njg5NTUsImV4cCI6MTc0NTY1NTM1NSwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHBzOi8vYXV0aC5jaXNjby5jb20ifQ.cPL39ELD_enKXGIqvXcO1TeB2GsPbQQUMgqthPQSMHU

```

