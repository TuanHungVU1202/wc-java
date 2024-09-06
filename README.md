
# WordCount

WordCount is a Java application that mimics the functionality of the Unix `wc` command. It counts lines, words, bytes, and characters in files or input streams.

## Building the Software

To build the software, follow these steps:

1. Ensure you have Java Development Kit (JDK) 11 or later installed on your system.
2. Ensure you have Apache Maven installed on your system.
3. Clone this repository or download the source code.
4. Navigate to the project root directory in your terminal.
5. Build the project using Maven:
   ```
   mvn clean package
   ```
6. The compiled JAR file will be in the `target` directory.

## Using the Software

After building, you can run the WordCount application using the following syntax:

```
java -jar target/wordcount-1.0-SNAPSHOT.jar [options] [file]
```

### Options

- `-c`: Count bytes.
- `-l`: Count lines.
- `-w`: Count words.
- `-m`: Count characters.

### File Input

If a file is specified as the last argument, the application will count the specified type of tokens in that file.

### String Input

If no file is specified, the application will read from standard input.

### Examples

1. Count lines in a file:
   ```
   java -jar target/wordcount-1.0-SNAPSHOT.jar -l input.txt
   ```

2. Count words in a file:
   ```
   java -jar target/wordcount-1.0-SNAPSHOT.jar -w input.txt
   ```

3. Count bytes in a file:
   ```
   java -jar target/wordcount-1.0-SNAPSHOT.jar -c input.txt
   ```

4. Count characters in a file:
   ```
   java -jar target/wordcount-1.0-SNAPSHOT.jar -m input.txt
   ```

5. Count lines in a string:
   ```
   echo "Hello, World!" | java -jar target/wordcount-1.0-SNAPSHOT.jar -l
   ```

6. Count words in a string:
   ```
   echo "Hello, World!" | java -jar target/wordcount-1.0-SNAPSHOT.jar -w
   ```

7. Count bytes in a string:
   ```
   echo "Hello, World!" | java -jar target/wordcount-1.0-SNAPSHOT.jar -c
   ```

8. Count characters in a string:
   ```
   echo "Hello, World!" | java -jar target/wordcount-1.0-SNAPSHOT.jar -m
   ```

9. Count lines, words, bytes, and characters in a file:
   ```
   java -jar target/wordcount-1.0-SNAPSHOT.jar input.txt
   ```

10. Count lines, words, bytes, and characters in a string:
    ```
    echo "Hello, World!" | java -jar target/wordcount-1.0-SNAPSHOT.jar
    ```