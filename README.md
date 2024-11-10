# Event Management System

This project is an **Online Event Management System** built with Java, using JDBC for database connectivity and a MySQL database. The frontend is developed using HTML, CSS, and JavaScript. This system allows users to view events and manage event data.

## Features

- **View Events**: Display all events with details like name, description, date, location, and organizer.
- **Database Management**: Data stored in a MySQL database with tables for Events, Users, and Registrations.
- **Modular Code**: Backend and frontend code is separated, making it easier to extend and maintain.

## Project Structure

```
EventManagementSystem/
├── src/
│   ├── DatabaseConnection.java      # Manages MySQL database connection
│   ├── EventDAO.java                # Data Access Object for Event CRUD operations
│   └── EventServlet.java            # Servlet to handle requests and send event data as JSON
├── web/
│   ├── index.html                   # Frontend HTML to display events
│   ├── styles.css                   # CSS file for styling
│   └── script.js                    # JavaScript to fetch and display event data
├── db/
│   └── database.sql                 # SQL script for database setup
├── lib/
│   └── gson.jar                     # Gson library for JSON handling
└── README.md                        # Project documentation
```
Setup Instructions

Prerequisites

Java Development Kit (JDK)

Apache Tomcat or another web server

MySQL Database

Gson Library: Add gson.jar to the lib/ directory or configure it through Maven/Gradle.


Database Setup

1. Create a new database in MySQL:

CREATE DATABASE EventManagement;


2. Run the SQL commands from the db/database.sql file to set up the required tables:
```
USE EventManagement;

CREATE TABLE Events (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    location VARCHAR(100),
    organizer VARCHAR(100)
);

CREATE TABLE Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Registrations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (event_id) REFERENCES Events(id)
);
```

3. Update DatabaseConnection.java with your MySQL credentials.



Run the Application

1. Compile the Java files in src/ using an IDE or command line.


2. Deploy the project on a Tomcat server:

Place compiled classes in the server’s web application directory.

Add all .html, .css, .js files in web/.



3. Start the Tomcat server and go to http://localhost:8080/EventManagementSystem/index.html to view events.



Usage

1. View Events: Open the index.html file in a browser. The JavaScript file script.js fetches event data from the EventServlet and displays it.


2. API Endpoint:

GET /getEvents: Returns all events as JSON.




Technologies Used

Java: Backend development

JDBC: Database connectivity

MySQL: Database for storing event data

HTML, CSS, JavaScript: Frontend design and interactivity

Gson: Library for JSON parsing


Future Enhancements

Event Registration: Add functionality for users to register for events.

User Authentication: Implement login and user roles.

Event CRUD Operations: Enable admins to create, update, and delete events.


License

This project is open-source and available under the MIT License.
