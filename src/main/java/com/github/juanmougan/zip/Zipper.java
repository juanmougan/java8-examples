package com.github.juanmougan.zip;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.zip.ZipFile;

public class Zipper {
    public void printEntries(PrintStream stream, URL zip) {
        try (ZipFile zipFile = new ZipFile(zip.getFile())) {
            zipFile.stream()
                    .forEach(stream::println);
        } catch (IOException e) {
            throw new RuntimeException("Error opening zip file", e);
        }
    }
}
