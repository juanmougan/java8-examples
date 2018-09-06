package com.github.juanmougan.zip;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ZipperIntegrationTest {

    private static final String ZIP_FILE = "twoFiles.zip";
    private Zipper zipper;

    @BeforeEach
    void setUp() {
        this.zipper = new Zipper();
    }

    @AfterEach
    void tearDown() {
        this.zipper = null;
    }

    @Test
    void printEntries() {
        System.out.println("Absolute: " + this.getClass().getResource("/" + ZIP_FILE));
        System.out.println("As stream: " + this.getClass().getResourceAsStream("/" + ZIP_FILE));
        this.zipper.printEntries(System.out, this.getClass().getResource("/" + ZIP_FILE));
    }
}
