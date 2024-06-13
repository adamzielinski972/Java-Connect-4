# Project Title

Connect Four Game

## Description

A Connect Four game with options to load from a file and the ability to save your game while you play

## Getting Started

### Dependencies

* import java.util.ArrayList;
* import java.util.Collections;
* import java.util.Scanner;
* import java.io.File;
* import java.io.IOException;
* import java.io.FileNotFoundException;
* import java.io.FileWriter;
* import org.junit.Assert;
* import org.junit.Before;
* import org.junit.Test;

### Executing program

* gradle build
* gradle run
* java -cp build/classes/java/main connectfour.ConnectFour

## Limitations

When loading from file you can overwrite the info from the file on the first turn
Ex. if column one has a yellow piece on the bottom, the program will overwrite it 
with a red if the player chooses that column

Did not fully test the exception throwing

## Development History

* 0.1
    * Initial Release




