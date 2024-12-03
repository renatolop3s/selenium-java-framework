# Selenium Java Framework

This is a Maven project for a Selenium Java framework designed for automated web testing. 
The framework offers a set of dependencies and plugins that simplify the creation and execution of web tests with Selenium and TestNG.

**_Please note that this is not the final version and is still a work in progress._**

## Features

- **Selenium WebDriver**: for web automation
- **TestNG**: for test organization and execution
- **WebDriverManager**: for managing WebDriver binaries
- **AssertJ**: for fluent assertions
- **Lombok**: for reducing boilerplate code
- **Owner**: for configuration management
- **Log4j**: for logging
- **Java Faker**: for generating test data
- **Allure** and **ExtentReports**: for generating test reports

## Prerequisites

- Java 21
- Maven 3.9.9 (Optional)

## Getting Started

1. Clone the repository:

```
git clone https://github.com/renatolop3s/selenium-java-framework.git
```

2. Change directory to the project folder:

```
cd selenium-java-framework
```

3. Run the tests using Maven:

```
mvn clean test
```

This will execute the tests as per the TestNG suite configuration specified in `src/test/resources/suites/testng.xml`.

4. To run a different TestNG suite, use the `-Dsuite=<new_suite>` command-line argument:

```
mvn clean test -Dsuite=<new_suite>
```

Replace `<new_suite>` with the name of the desired suite located in `src/test/resources/suites/`. There's no need to specify .xml extension.

## Using Maven Wrapper

If you don't have Maven installed on your system, you can use the Maven Wrapper included in the project. The Maven Wrapper is a shell script and batch file that automatically downloads and runs the correct version of Maven.

### For Linux and macOS:

Use the `mvnw` shell script to run the Maven commands. For example:

```
./mvnw clean test
```

### For Windows:

Use the `mvnw.cmd` batch file to run the Maven commands. For example:

```
mvnw.cmd clean test
```

These commands will work the same way as if you had Maven installed, and they will automatically download and use the appropriate Maven version for this project.

## Configuration Management

WIP