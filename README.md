# CGConvert (CGC)
Converts a CPlusPlus project into one file
## GettingStarted

These instructions will help you to set up the project and convert your own CPP-Project

### Prerequisites

What you need to install and run this CGC:

```
Java 8 JKD
Maven
```
Maven does the rest

### Installing

```
mvn clean install
mvn build package
```
Now you can find a jar-with-dependencies under the **target** directory.
Run the jar with
```
java -jar target/cgconvert-YOURVERSION-jar-with-dependencies.jar myprojectrootdirectory
```
This project includes some **examples**:
```
java -jar target/cgconvert-YOURVERSION-jar-with-dependencies.jar examples/example1/
```

## Running the tests

Maven automatically runs some unit tests (with junit).
CGC won't give you any warranty, that your project is converted right and your code will be runnable, **BUT** it will give you a good starting point.

You can run tests by hand with
```
java -jar target/cgconvert-YOURVERSION-jar-with-dependencies.jar examples/example1/
```
You can find more examples under the **examples** directory.

## Built with
* [Maven](https://maven.apache.org/)
* [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/)

### Versioning
Yes I should do that

### License
This project runs under GPL v3 - see the LICENSE file for details

### Acknowledgments
Thanks to `Redstrike` for inspiring me to do this project.
