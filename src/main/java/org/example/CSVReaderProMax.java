package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReaderProMax {
    public String[] urls;
    public File file;

    public CSVReaderProMax() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the file path to your CSV file: ");
        String filepath = scanner.nextLine();

    }

    public void gatherData(String filepath) throws FileNotFoundException {
        file = new File(filepath);

        String temp = "";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            temp += scanner.nextLine();
        }

        urls = temp.split(",\n");
    }

    public void printUrls() {
        for (String url : urls) {
            System.out.println(url);
        }
    }

    public String[] getUrls() {
        return urls;
    }
}
