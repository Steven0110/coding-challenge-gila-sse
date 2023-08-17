# coding-challenge-gila-sse

![Badge](https://img.shields.io/badge/Enthusiasm-over_9000-dargreen)

This repository contains the files for the required Senior Software Engineer coding challenge.

## Instructions
It is required to create a notification system API, which is capable of receiving a message and depending
on the category of the message and the users subscribed to them, said users will be notified to the
medium that they themselves chose.
3 categories of messages will be handled:

- Sports
- Finance
- Films

And it is required to send 3 types of notifications:

- SMS
- E-mail
- Push Notification

It is not required that any message is actually sent or communicates with any external API, only the
sending of said notification will be recorded in a Logs file or in the database.
In the log it is required to save all the necessary information to identify that the correct notification
was made to the corresponding user. Save information such as the type of message, type of
notification, user data, time, etc.

User administration is not required, a Mock of users can be managed in the system, they must have the
following information:

- ID
- Name
- E-mail
- Phone
- Subscribed [ ] List of categories you are subscribed to
- Channels [ ] List of user notification types (SMS | Email | PushNotification)

The API will receive 2 parameters:

1. Category. Validate from the list of available categories
2. Message. Just validate that the message is not empty.

## Requeriments
- Java 19+
- Maven 3.9+

## Run
Open a terminal in the root of this repository and enter the following command.

```bash
mvn compile

[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< com.gerardo_steven:coding-challenge >-----------------
[INFO] Building coding-challenge 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ coding-challenge ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.11.0:compile (default-compile) @ coding-challenge ---
[INFO] Changes detected - recompiling the module! :source
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 3 source files with javac [debug target 1.8] to target\classes
[WARNING] bootstrap class path not set in conjunction with -source 8
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.850 s
[INFO] Finished at: 2023-08-17T08:43:55-06:00
[INFO] ------------------------------------------------------------------------

```

Once compiled, just run the application:

```bash
mvn spring-boot:run

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.2)

2023-08-17T08:44:11.893-06:00  INFO 16744 --- [           main] com.gerardo_steven.RestApiApp            : Starting RestApiApp using Java 19.0.2 with PID 16744 (D:\elife\dev\coding-challenge-gila-sse\target\classes started by cabel in D:\elife\dev\coding-challenge-gila-sse)
2023-08-17T08:44:11.897-06:00  INFO 16744 --- [           main] com.gerardo_steven.RestApiApp            : No active profile set, falling back to 1 default profile: "default"
2023-08-17T08:44:13.242-06:00  INFO 16744 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-08-17T08:44:13.257-06:00  INFO 16744 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-08-17T08:44:13.258-06:00  INFO 16744 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.11]
2023-08-17T08:44:13.420-06:00  INFO 16744 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-08-17T08:44:13.422-06:00  INFO 16744 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1442 ms
2023-08-17T08:44:13.923-06:00  INFO 16744 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-08-17T08:44:13.935-06:00  INFO 16744 --- [           main] com.gerardo_steven.RestApiApp            : Started RestApiApp in 2.595 seconds (process running for 3.088)
2023-08-17T08:44:17.919-06:00  INFO 16744 --- [nio-8080-exec-3] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-08-17T08:44:17.919-06:00  INFO 16744 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-08-17T08:44:17.920-06:00  INFO 16744 --- [nio-8080-exec-3] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
```

## Configuration
This API simulates the connection with a JSON-based DBMS to get user's information, this information is stored in the file users.json in the root folder. This is an example of its content:
```json
[
	{
		"id": "1",
		"name": "John Doe",
		"email": "john@challenge.com",
		"phone": "+525512341234",
		"deviceToken": "eX3wek3xL18:APA91bGc4zkrENir9Tb-o4tDWLVK_G-0O7XfDvcJ-6-EXAMPLE_TOKEN_Here-1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef",
		"subscribed": ["sports", "finance", "films"],
		"channels": ["sms", "email", "push"]
	},
	{
		"id": "2",
		"name": "Sarah Smith",
		"email": "sarah@challenge.com",
		"phone": "+525512341239",
		"deviceToken": "eX3wek3xL18:APA91bGc4zkrENir9Tb-o4tDWLVK_G-0O7XfDvcJ-6-EXAMPLE_TOKEN_Here-1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef",
		"subscribed": ["sports"],
		"channels": ["sms", "push"]
	},
	{
		"id": "3",
		"name": "Peter Park",
		"email": "peter@challenge.com",
		"phone": "+525512343421",
		"deviceToken": "eX3wek3xL18:APA91bGc4zkrENir9Tb-o4tDWLVK_G-0O7XfDvcJ-6-EXAMPLE_TOKEN_Here-1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef",
		"subscribed": ["sports", "films"],
		"channels": ["email", "push"]
	},
	{
		"id": "4",
		"name": "Andrew Gary",
		"email": "andrew@challenge.com",
		"phone": "+525512341235",
		"subscribed": ["finance", "films"],
		"channels": ["sms"]
	},
	{
		"id": "5",
		"name": "John Doe",
		"email": "demo@challenge.com",
		"phone": "+525512341238",
		"deviceToken": "eX3wek3xL18:APA91bGc4zkrENir9Tb-o4tDWLVK_G-0O7XfDvcJ-6-EXAMPLE_TOKEN_Here-1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef",
		"subscribed": ["sports", "finance", "films"],
		"channels": ["sms", "email", "push"]
	},
	{
		"id": "6",
		"name": "Matt Gibson",
		"email": "matt@challenge.com",
		"phone": "+525512341231",
		"subscribed": ["films"],
		"channels": ["email"]
	}
]
```

All the processes will be done by using this file, you can modify it as needed.

## Usage
Use any tool to manage Requests like Postman, or use any cURL based library to make proper requests to the API. These are a couple of sample requests made to the API:

```http
   POST http://localhost:8080/send
   Content-Type: application/json
   Body:
   {
	    "category": "sports",
	    "message": "This is a Sports message"
	}

	Response:
	{
    	"response": "Message sent successfully"
	}
```

```http
   POST http://localhost:8080/send
   Content-Type: application/json
   Body:
   {
	    "category": "games",
	    "message": "This is a Games message"
	}

	Response (400 Bad Request):
	{
   	"response": "Wrong request body. Invalid 'category' parameter, it should be either 'sports', 'finance' or 'films'"
	}
```

```http
   POST http://localhost:8080/send
   Content-Type: application/json
   Body:
   {
	    "category": "films",
	    "message": ""
	}

	Response (400 Bad Request):
	{
   	"response": "Wrong request body. 'message' parameter is required"
	}
```

All the messages sent will be logged in the 'messages_log.txt' file in the root of the project

This is an example of the log file:

```bash
[2023.08.17.08.44.18] - SMS message sent to +525512341234 => This is a Sports message
[2023.08.17.08.44.18] - Email message sent to john@challenge.com => This is a Sports message
[2023.08.17.08.44.18] - Push message sent to eX3wek3xL18:APA91bGc4zkrENir9Tb-o4tDWLVK_G-0O7XfDvcJ-6-EXAMPLE_TOKEN_Here-1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef => This is a Sports message
[2023.08.17.08.44.18] - SMS message sent to +525512341239 => This is a Sports message
```