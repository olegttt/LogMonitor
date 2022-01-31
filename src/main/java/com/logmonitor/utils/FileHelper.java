package com.logmonitor.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


public class FileHelper {
    public static void saveTextToFile(List<String> content, File file) throws FileNotFoundException {
        if (content != null) {
            PrintWriter writer;
            writer = new PrintWriter(file);
            content.forEach(writer::println);
            writer.close();
        }
    }
}
