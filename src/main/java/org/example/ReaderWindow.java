package org.example;

import javax.swing.*;
import java.awt.*;

public class ReaderWindow {
    private CSVReaderProMax reader;
    private JWindow readerWindow;

    public ReaderWindow(CSVReaderProMax reader) {
        this.reader = reader;

        JFrame myFrame = new JFrame("CSVReaderProMax");
    }

    public static void main(String[] args) {
        CSVReaderProMax myReader = new CSVReaderProMax();
        ReaderWindow myWindow = new ReaderWindow(myReader);
    }
}
