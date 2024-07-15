Currency WebApi Project

Features

Retrieve Exchange Rates: Fetch real-time exchange rates for various currencies.
Convert Currency: Convert amounts between azerbaijan money to different money based on current exchange rates.
Historical Rates: Access historical exchange rates for specific dates.


Technologies Used

Java: Core programming language.
Spring Boot: Framework for building Java applications.
Hibernate: ORM (Object-Relational Mapping) for database interaction.
PostgreSQL: Database for storing currency data.
RestTemplate: HTTP client for making requests to external APIs.
Swagger: API documentation tool.

Setup Instructions

To run this project locally, follow these steps:

Clone the repository:
git clone https://github.com/nihademinov/Currency-WebApi-Project.git
cd Currency-WebApi-Project

Configure the database:

Install PostgreSQL and create a database named "currencyDb"
Update application.properties with your database credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/currency_webapi
spring.datasource.username=your_username
spring.datasource.password=your_password

API Endpoints
GET /currency: Retrieve all exchange rates on the date in the databases.
GET /currency/{dd.mm.yyyy}/write AZN value/convert valute code(example:USD):Retrieve an amount from one currency to another
POST /currency/{dd.mm.yyyy}:  Retrieve historical exchange rates for a specific date.
