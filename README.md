# contact-list

### Overview
This is a simple Spring-Boot application leveraging `spring-data-jpa`, Hibernate, and HSQLDB.

It requires Java 11.

### Implementation notes
This solution treats the following as full-fledged JPA entities (package `org.foocorp.exercise.contactlist.model`):
- Contact
- Address
- Phone

In addition to the above:
- Contact names are stored as `@Embeddable` objects in the `contacts` table.
- Call list items (name + home phone) are handled by a seperate DAO, which
is implemented with a single `JDBCTemplate` call (SQL can be found in [application.yml](./src/main/resources/application.yml)).
  
Swagger/OpenApi 3.0 documentation is generated via the `springfox-boot-starter`
dependencies, and can be accessed at http://localhost:8888/swagger-ui/index.html

### Build / execution

To build:
```shell
mvn compile
```


To start the application, which defaults to port 8888, simply invoke the `maven-spring-boot-plugin`

```shell
mvn spring-boot:run
```

### Viewing the database
The default spring-boot profile will start an embedded (in-memory) HSQLDB instance.  To monitor
tables as the application processes API calls, you will need to start HSQLDB as a networked
process.  You can find the JAR at [Maven Central](https://repo1.maven.org/maven2/org/hsqldb/hsqldb/2.6.0/hsqldb-2.6.0.jar).

Simply choose a directory in which to store the data files, copy the HSQLDB JAR to this
directory and execute the following:
```shell
java -cp ..\hsqldb-2.6.0.jar org.hsqldb.server.Server --database.0 file:contacts --dbname.0 contacts
```

This will start HSQLDB in the local directory, creating database files, and
making the new database available at `jdbc:hsqldb:hsql://localhost/contacts`

You can then view the database in your favorite JDBC DB browser, or use the Swing-based
tool in the JAR file:
```shell
java -cp .\hsqldb-2.6.0.jar org.hsqldb.util.DatabaseManagerSwing
```

To make the application use this networked connection, I have added 2 Spring profiles:
1. `networked1`
2. `networked2`

The `networked1` profile contains the `spring-jpa.hibernate.ddl-auto: create` setting, which
will created the tables and sequences necessary on first run.  Execute this command to
select the `networked1` profile:

```shell
mvn spring-boot:run -D"spring-boot.run.arguments=--spring.profiles.active=networked1"
```

Please note that, if you exit the above Java process and try to run it again, _any data you
persisted during the first run will be lost_, as Hibernate will drop and recreate the
database objects.  Instead, on subsequent runs, employ the `networked2` profile:

```shell
mvn spring-boot:run -D"spring-boot.run.arguments=--spring.profiles.active=networked2"
```
