package org.example;
import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
        Indexer test = new Indexer(new Website("https://en.wikipedia.org/wiki/Cult_of_the_Lamb"), "C:\\Users\\jvcer\\IdeaProjects\\WebScraperNew\\src\\main\\resources");
        test.indexURLS("https://en.wikipedia.org/wiki/Cult_of_the_Lamb");

        test.printValues();
    }
}
