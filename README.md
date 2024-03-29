Books_WebApplication



Problem Statement: You are tasked with developing a basic CRUD (Create, Read, Update, Delete) application for managing a simple database of books. The application should be built using Spring MVC framework, JSP for user interface, and SQL for database operations. The application should allow users to perform the following operations:
1.	Create Operation: Users should be able to add new books to the database by providing information such as title, author, genre, publication year, and ISBN number.
2.	Read Operation: Users should be able to view a list of all books in the database, displaying details such as title, author, genre, publication year, and ISBN number. Additionally, users should be able to search for books by title or author.
3.	Update Operation: Users should be able to edit the information of existing books in the database, including fields such as title, author, genre, publication year, and ISBN number.
4.	Delete Operation: Users should be ,able to remove books from the database.


Steps :
1)	Spring MVC, Hibernate, MYSql workbench
2)	Configured Tomcat server
3)	Created maven project as maven archetype-webApp
4)	Added required dependencies in POM.xml file (spring-mvc,spring-orm,hibernate,mysql-connector) 
5)	Configured dispatcher-servlet in web.xml file (which handle http req and responses for web app)
6)	Created spring-servlet.xml file (for spring configuration) 
- declared schemas in <beans>
- component scan for scanning base package
- configured view-resolver declared prefix and suffix
7)	Configured Database bean declared driver,url,username,pass
8)	Configured localSession Factory Bean
9)	Configured hibernate Template and configured Hibernate Transaction Manager and enable it using <tx:annotation-driven/>
10)	Created DAO class 
- @component : it will helps to create bean which will handled by spring IOC (Inversion of Control)  
- @Autowired HibernateTemplate : it will inject hibernateTemp object
- added CRUD methods using hibernateTemp
- @Transactional : managed and perform multiple operations betn DB 
11)	Created Model class for Books and annotated it as @Entity and in spring-servlet.xml added its pkg for config
12)	WorkBench : created Schema in WB “bookcrud”.
13)	Created controllers and created jsp pages accordingly 
-RedirectView (add/delete) will redirect user to specified URL
