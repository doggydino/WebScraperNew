package org.example;
import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Indexer indexer = new Indexer("C:\\Users\\jvcer\\IdeaProjects\\WebScraperNew\\src\\main\\resources");
        indexer.indexURLSClean("https://en.wikipedia.org/wiki/Cult_of_the_Lamb");
        indexer.printURLS();
//        indexer.writeToCSV();
    }
}
