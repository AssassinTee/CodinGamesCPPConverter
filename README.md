# CGConvert (CGC)
Converts a CPlusPlus project into one file
## GettingStarted

These instructions will help you to set up the project and convert your own CPP-Project

### Prerequisites

What you need to install and run CGC:

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
### Running
After installing and building the package you can run CGC.
You can see the options like this:
```
java -jar target/cgconvert-YOURVERSION-jar-with-dependencies.jar
```
Options (in german locale):
```
 -c (--clipboard)   : copy to clipboard (Vorgabe: false)
 -i (--input) WERT  : input directory
 -m (--mode) WERT   : converter mode: 'default', 'debug' or 'shortest'
                      (Vorgabe: default)
 -o (--output) WERT : output directory
```

Modes:
* **Default**: 	Converts Code, allows empty lines and **doesn't add** debug **comments** to the converted code. **May** remove **some** comments.
* **Debug**: 	Converts Code, allows empty lines and **adds** debug information to the converted code. **May** remove **some** comments.
* **Shortest**:	Converts Code, skips empty lines and **doesn't add** any comments. It also removes any whitespaces and tabs that aren't necessary! Removes **all** single line comments

This project includes some **examples**.
Example for project example1:
```
java -jar target/cgconvert-YOURVERSION-jar-with-dependencies.jar -i examples/example1/ -m debug
```


## Running the tests

Maven automatically runs some unit tests (with junit).
CGC won't give you any warranty, that your project is converted right and your code will be runnable, **BUT** it will give you a good starting point.

You can run tests by hand with
```
java -jar target/cgconvert-YOURVERSION-jar-with-dependencies.jar -i examples/example1/
```
You can find more examples under the **examples** directory.

## Built with
* [Maven](https://maven.apache.org/)
* [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/)
* [Args4j](https://args4j.kohsuke.org/)

### Versioning
Yes I should do that

### License
This project runs under GPL v3 - see the LICENSE file for details

### Acknowledgments
Thanks to `Redstrike` for inspiring me to do this project and testing this software.
