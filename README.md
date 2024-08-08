# Binary File Wizard

A simple command line Java program that can read most binary files, or turn a file into a `.dat` file.

## Table of Contents

- [Title](#binary-file-wizard)
- [Installation](#installation)
- [Usage](#usage)

## Installation & Setup

Make sure you have the latest version of the Java Development Kit installed. You can download it [<u>here</u>](https://www.oracle.com/java/technologies/downloads/)

### 1. Clone the repository
```bash
git clone https://github.com/prebish/binary-file-wizard.git
```

### 2. Compile the program
```bash
cd ./binary-file-wizard
javac ./src/*.java
```

## Usage

This is the baseline of what you'll use to run the program. Try running it yourself.  
Note that the usage will print out when there are insufficient arguments.

```bash
java -cp build BinFileWiz
```

### Reading

#### The output will be displayed in the console
```bash
java -cp build BinFileWiz -r binary.dat
```

#### Optionally, you can include a name of a file to place the output
```bash
java -cp build BinFileWiz -r binary.dat  binary_to_text.txt
```

### Writing

#### The output will be placed in a file named `output.dat` by default
```bash
java -cp build BinFileWiz -w file.txt
```

#### Optionally, a custom output file name can be included
```bash
java -cp build BinFileWiz -w file.txt text_to_binary.dat
```


