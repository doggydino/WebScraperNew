package org.example;

import java.io.*;
import java.util.*;

import org.jsoup.*;

import com.opencsv.CSVWriter;
import org.jsoup.nodes.Document;

public class Indexer {
    final private Website ROOT;
    // final private String DATAPATH;
    private File file;
    private CSVWriter csvWriter;
    private ArrayList<String> urls;

    public Indexer(Website root, String filePath) {
        ROOT = root;
        urls = new ArrayList<>();
        // this.DATAPATH = filePath;
        file = new File(filePath + "\\data.csv");
        System.out.println(file.toString());

        // Checks for pre-existing data
        if (file.exists()) {
            initializeWriter(file);
        } else {
            try {
                file.createNewFile();
                csvWriter = initializeWriter(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private CSVWriter initializeWriter(File file) {
        try {
            return new CSVWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void indexURLS() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String temp = "";

        while (scanner.hasNextLine()) {
            temp += scanner.nextLine();
        }

        String[] lines = temp.split("\n");
        System.out.println(Arrays.toString(lines));

        for (int i = 0; i < lines.length; i++) {
            String currentLine = lines[i];
            int index = currentLine.indexOf("href=\"");

            if (index > -1) {
                // I'm not proud of this
                temp = currentLine.substring(index + 7, currentLine.substring(index + 8).indexOf("\" "));
                System.out.println(temp);
            }
        }
    }

    public void indexURLS(String url) throws IOException {
        // Implementing test
        Document websiteContents = Jsoup.connect(ROOT.getUrl()).get();
        String temp = String.valueOf(websiteContents.body());

        String[] lines = temp.split("\n");
        // System.out.println(Arrays.toString(lines));

        for (String line : lines) {
            String currentLine = line;
            for (int index = currentLine.indexOf("href=\"https"); currentLine.contains("href=\"https"); index = 0) {
                // I'm not proud of this
                if (index < 0) {
                    break;
                } else {
                    int end = currentLine.indexOf("\">", index + 1);
                    temp = currentLine.substring(index + 6, end - 2);
                    urls.add(temp);
                    currentLine = currentLine.substring(end);
                }
            }
            }
        }

        public void indexURLSClean(String url) {

        }

        public void writeToCSV(ArrayList<String> myData) {
            Iterable<String[]> dataIterator = null;

            csvWriter.writeAll(dataIterator);
        }

        public void printValues() {
            for (String url : urls) {
                System.out.println(url);
            }
        }

    }