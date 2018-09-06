package com.github.juanmougan.zip;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ZipperIntegrationTest {

    private static final String ZIP_FILE = "twoFiles.zip";
    private Zipper zipper;
    private URL zipUrl;

    @BeforeEach
    void setUp() {
        this.zipper = new Zipper();
        this.zipUrl = this.getClass().getResource("/" + ZIP_FILE);
    }

    @AfterEach
    void tearDown() {
        this.zipper = null;
        this.zipUrl = null;
    }

    @Test
    void printEntries() {
        this.zipper.printEntries(System.out, zipUrl);
    }

    @Test
    void unzipFilesOnly() throws URISyntaxException {
        zipUrl = this.getClass().getResource("/" + ZIP_FILE);
        System.out.println(Paths.get(zipUrl.toURI()).getParent().toAbsolutePath());
        this.zipper.unzipFilesOnly(System.out, Paths.get(zipUrl.toURI()));
        System.out.println("Finished");
    }
}
