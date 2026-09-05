Sunrise Dental Clinic - Appointment and Patient Management System

A distributed Java desktop application for managing dental clinic appointments, patient records, and billing. Built as a university assignment for CIS6003 Advanced Programming.

Prerequisites:

- Java JDK 17+
- Apache Netbeans IDE 21+
- MySQL Server (WAMP)
- MySQL Connector (JDBC)

Note: the MySQL JDBC driver (mysql-connector-j.jar) is  included in the lib folder of this project, If NetBeans does not detect it automatically, please right-click the project -> Properties -> Libraries, and add the JAR from the lib folder.


Running the Application:
- Run the main file "SunriseDentalClinic.java"
This will automatically start the backend HTTP server on port 5000 and launch the login VIEW.


Test Credentials

- The system utilizes Role-based Access Control, please use the following hardcoded accounts to test the system

Admin access (for Full system and Staff Registration)
username: admin
password: [insert password]

NOTE: The admin account needs to be manually inputted to the database 
NOTE: An admin account already exists in the included sunrisedentalclinic_DB.sql file


Database configuration

- Open phpmyadmin
- create a new database named: sunrisedentalclinic_DB
- import the provided sunrisedentalclinic_DB.sql file to this new database. This will generate the required tables and populate the default test data





