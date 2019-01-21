# URL Shortener

A simple API that shortens URLs

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

 - JDK 8.
 - An IDE.
 - Lombok.
 - MySQL DB.

### Installing

Steps to show how to get a development environment running

Checkout the project

```
git clone git@github.com:AbdullahAsendar/url-shortener.git
```

Import to eclipse as maven project

```
On eclipse go to file> import> maven> exisitng maven project
```

Set you DB params inside

```
resources> config> application-mysql.properties
```

Run the project using main class

```
org.asendar.url.shortener.main.Launcher
```

Access the app throw swagger on a web browser by visiting
```
http://localhost:8080
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Author

* **Abdullah Asendar** - *Java Developer*
