# Simple File Parser

Simple File Parser is console based Application it takes directory as command line argument and does the following:

* The application will then monitor the folder for new Files.
* For each file arrives it reads File contents and print statistics on the console

Note: Presently Application supports plain text files and print the number of words, number of dots and most used word on console as statistics.

## Prerequisites:
* Java 8
* Maven  

## Build Project

Download source to a directory. use below command build jar file

```bash
mvn clean install
```

## Usage

```bash
java -jar simplefileparser "directoryToWatch"
```

## How parser works.
When we run jar file. it invokes Starter class main method it takes directory path as argument and does the following:

* it uses Java WatchServcie API to watch directory.

```Java
parentPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
WatchKey key;
while(( key = watchService.take())!=null){
``` 

* Whenever new File arrives it invokes FileProcessor Process method.

* Using Strategy design pattern FileReaderSupport identifies FileReader object gives back to FileProcessor.
  
```Java
  FileReader fileReader = fileReaderSupport.getFileReader();
```

* using Chain of responsibility pattern FileProcessor collects statistics  and prints it to console from Extractors

```Java
  extractors.stream().map(extractor->extractor.extract(fileReader))
                .forEach(System.out::println);
```
* using FileHandler it moves processed file to processed directory.

## Class Diagram

![Image of sequence diagram](https://raw.githubusercontent.com/hemanthl7/simplefileparser/main/ClassDiagram.png)

## Sequence Diagram

![Image of sequence diagram](https://raw.githubusercontent.com/hemanthl7/simplefileparser/main/SequenceDiagram.png)


Please feel free to give comments.

## License
[MIT](https://choosealicense.com/licenses/mit/)
