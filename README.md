# Bug Tracer

## Table of Contents

* [Introduction](#introduction)
* [Features](#features)
* [Technologies](#technologies)
* [Getting Started](#getting-started)
    * [Prerequisite](#prerequisite)
    * [Installation](#installation)
    * [Running the Application](#running-the-application)
* [API Endpoint](#api-endpoints)
* [Future Work](#future-work)

## Introduction

BugTracer is a project management application designed to help teams track and manage bugs efficiently. The current
version of BugTracer includes the backend RESTful services, with the frontend still under development.

***

## Features

- Create, update, and delete projects
- Create, update, and delete tasks
- Assign tasks to users
- Comment on tasks

***

## Technologies

- Java 17
- Spring Boot 3.2.5
- Hibernate ORM 6.4.4
- MySQL 8.3.0
- Maven

***

## Getting Started

### Prerequisite

Make sure you have the following installed before beginning

* Java 17
* Maven
* MySQL

### Installation

1. Clone the repository

```
git clone https://github.com/duytranmanh/BugTracer.git
cd bugtracer
```

2. Create a MySQL database named `bugtracer` and update the `application.properties` file with your database credentials

```
spring.datasource.url=jdbc:mysql://localhost:3306/bugtracer
spring.datasource.username= *INSERT YOUR USERNAME*
spring.datasource.password= *INSERT YOUR PASSWORD*
```

3. Build the application

```
mvn clean install
```

### Running the Application

Run the application by this line

```
mvn spring-boot:run
```

The application will start on **http://localhost:8080**

***

## API Endpoints

Here are a couple of important RESTful endpoint in BugTracer!

### User Management

*POST /users* - Create a new user

*GET /users/{id}* - Get user details

*PUT /users/{id}* - Update user information

*DELETE /users/{id}* - Delete a user

### Project Management

*POST /projects* - Create a new project

*GET /projects/{id}* - Get project details

*PUT /projects/{id}* - Update project information

*DELETE /projects/{id}* - Delete a project

### Task Management

*POST projects/{projectId}/tasks* - Create a new task in a project

*GET projects/{projectId}/tasks* - Get all tasks in a project

*GET /tasks/{taskId}* - Get task details

*PUT projects/{projectId}/tasks/{taskId}* - Update task information

*DELETE tasks/{taskId}* - Delete a project

## Future Work

* Implement security feature
* Complete Frontend development

