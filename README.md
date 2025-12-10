# Description
Solutions for https://adventofcode.com/2025 written in Java with Maven.

# Build and run
Tests for specific day challenges are written as unit tests (JUnit). To run them use the following command:
```shell
mvn -B test --file pom.xml
```

# Generate placeholders for a day

```shell
mvn antrun:run@generate-class -DdayNumber=10 -DclassName=Factory
```