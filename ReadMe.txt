Ticket Management Thread Simulation

Overview:
This project implements a thread-based simulation of a ticket management system. 

Features:
Vendors release tickets to a shared ticket pool.
Each vendor is configured with: A unique vendor ID, Event details (e.g., event name, ticket price), Ticket release rate (time interval between releasing tickets), Total tickets to release.


Customers purchase tickets from the shared pool.
Each customer is configured with: A unique customer ID, Customer name, Vendor ID (identifying the vendor from which they purchase tickets), Number of tickets to buy, Retrieval rate (time interval between purchasing tickets).


Ticket Pool
A synchronized pool that holds available tickets.
Supports adding and removing tickets in a thread-safe manner using locks and conditions.
Enforces a maximum ticket capacity.
WebSocket Integration
Log messages are sent to a WebSocket controller for real-time monitoring.
Logging
Logging is implemented using Log4j to track the operations performed by vendors and customers.


Getting Started

Prerequisites:
Java 11 or higher: Ensure you have the required version of Java installed.
Spring Boot: The project uses Spring Boot for dependency injection and component management.
Log4j: Used for logging.

Installation:
Clone the repository:
git clone <repository_url>
Navigate to the project directory:

cd oop_cw

Build the project using your preferred IDE (e.g., IntelliJ IDEA or Eclipse), Visual Studio code or use Maven:

mvn clean spring-boot:run

Configuration:
Edit the configuration file (application.yml or application.properties) to set:

Maximum ticket capacity.

Customer retrieval rates.

Ticket release rates.

Running the Application

Start the Spring Boot application.

mvn spring-boot:run

Use the following endpoints to interact with the application:

API Endpoints

1. Create Vendors

URL: /simulation/vendors

Method: POST

Request Body:

[
  {
    "vendorId": 1,
    "eventName": "Concert A",
    "ticketsToRelease": 100,
    "ticketPrice": 50.0
  },
  {
    "vendorId": 2,
    "eventName": "Concert B",
    "ticketsToRelease": 50,
    "ticketPrice": 30.0
  }
]

Response:

Success: Vendors created successfully

Failure: Vendor requests cannot be null or empty

2. Create Customers

URL: /simulation/customers

Method: POST

Request Body:

[
  {
    "customerId": "C1",
    "customerName": "Alice",
    "vendorId": 1,
    "ticketsToBuy": 2
  },
  {
    "customerId": "C2",
    "customerName": "Bob",
    "vendorId": 2,
    "ticketsToBuy": 3
  }
]

Response:
Success: Customers created successfully


3. Start Simulation

URL: /simulation/start
Method: POST

Response:
Success: Simulation started successfully
Failure: Error while starting simulation: <error message>

4. Stop Simulation

URL: /simulation/stop

Method: POST

Response:
Success: Simulation stopped successfully

Design Principles:
Object-Oriented Programming (OOP)

Encapsulation:
Classes like Vendor, Customer, and TicketPool encapsulate their data and provide well-defined methods for interaction.

Inheritance:
Shared properties are reused and extended where needed.

Polymorphism:
Implementing Runnable for both vendors and customers to facilitate threading.

Thread Safety:
Used ReentrantLock and Condition to ensure thread-safe access to the ticket pool.

Synchronized lists to manage vendors and customers.

Dependency Injection

Managed by Spring Boot for components such as Simulation, TicketPool, and WebSocketController.

Logging

Logs are generated using Log4j.

Logs include:
Vendor ticket release details.
Customer ticket purchase details.

Errors and warnings.
Issues and Improvements
Ensure thread interruption is handled gracefully to prevent deadlocks.


Backend Setup

Clone the repository:

https://github.com/
cd oop_cw/backend

Build and run the backend:

mvn clean install
mvn spring-boot:run

The backend will be accessible at http://localhost:8080.

Frontend Setup

Navigate to the frontend directory:

cd oop_cw/frontend/ticket-frontend

Install dependencies:

npm install

Run the development server:

ng serve

The frontend will be accessible at http://localhost:4200.



