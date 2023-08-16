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
```

Once compiled, just run the application:

```bash
mvn spring-boot:run
```

## Usage example
Use any tool to manage Requests like Postman, or use any cURL based library to make proper requests to the API.

```http
   POST http://localhost:8080/send
   Content-Type: application/json
   Body:
   {
	    "category": "SMS",
	    "message": "This is a demo message"
	}

	Response:
	{
    	"response": "Message of type SMS was successfully sent"
	}
```

All the messages sent will be logged in the 'messages_log.txt' file in the root of the project

This is an example of the log file:

```bash
[2023.08.16.15.52.53] - SMS to be sent: Message
[2023.08.16.15.53.14] - Push to be sent: This is a Push message
```