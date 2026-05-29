package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        UrlIndexerProMax urlIndexerProMax = new UrlIndexerProMax("C:\\Users\\jvcer\\IdeaProjects\\WebScraperNew\\src\\main\\resources");
        urlIndexerProMax.indexURLSClean("https://en.wikipedia.org/wiki/Cult_of_the_Lamb");
        urlIndexerProMax.printURLS();
        urlIndexerProMax.writeToCSV();
    }
}
