package org.example;

import java.io.*;
import java.util.*;
import org.jsoup.*;
import com.opencsv.CSVWriter;
import org.jsoup.nodes.Document;

public class Indexer {
    public final File FILE;
    public final FileWriter FILEWRITER;
    public final ArrayList<String> URLS;

    public Indexer(String filePath) {

        // URLS Storage
        URLS = new ArrayList<>();

        // Creates a file object at the specified path
        Date currentDate = new Date();
        FILE = new File(filePath + "\\data" + currentDate + ".csv");
        System.out.println(FILE);


//        try {
//            FILE.createNewFile();
//            FILEWRITER = initializeFileWriter(FILE);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        FILEWRITER = null;
    }

    @Deprecated
    private CSVWriter initializeCSVWriter(File file) {
        try {
            return new CSVWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Tries to create a FileWriter object with a FILE object to write to and returns it
    private FileWriter initializeFileWriter(File file) {
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Failure 1
    private void indexURLS() throws FileNotFoundException {
        Scanner scanner = new Scanner(FILE);
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

    // Failure 2
    private void indexURLS(String url) throws IOException {
        // Implementing test
        Document websiteContents = Jsoup.connect(url).get();
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
                    URLS.add(temp);
                    currentLine = currentLine.substring(end);
                }
            }
            }
        }

        // Success
        public void indexURLSClean(String url) throws IOException {
            // Connects to web page and fetches the body
            Document websiteContents = Jsoup.connect(url).get();
            String temp = String.valueOf(websiteContents.body());

            // Limiter used to delimit the string being searched
            int limiter = 0;

            // Loops through HTML body and fetches all HTTPS links held within.
            while (temp.indexOf("href=\"https", limiter) > -1) {
                // Links in HTML start with the keyword "href=""
                // I added the HTTPS filter to make sure that it only returns HTTPS links
                int start = temp.indexOf("href=\"https", limiter);

                // Searches for the end of link denoted by a quote
                // Usually bad practice in HTML code to put quotes in web links so the margin of error is minimal
                int end = temp.indexOf("\"", start + 6);

                // Changes the frame of reference for the next loop iteration to make sure the
                limiter = end + 1;

                // Adds URL link to the collection
                URLS.add(temp.substring(start + 6, end));
            }
        }

        // Takes existing URLS ArrayList<String> and writes them to a CSV file format
        public void writeToCSV() {
            try {
                for (String url : URLS) {
                    FILEWRITER.write(url + ",\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        public void clearCSV()  {
//            try {
//                FILEWRITER.write("");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        // Prints the values from
        public void printURLS() {
            for (String url : URLS) {
                System.out.println(url);
            }
        }

    }