package com.github.juanmougan.zip;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Zip implementation using Java's APIs
 * Big thanks to: <ul>
 *          <li>http://blog.codeleak.pl/2014/06/listing-zip-file-content-java-8.html</li>
 *          <li>https://stackoverflow.com/a/36890475/3923525</li>
 *     </ul>
 *     for the inspiration :)
 */
public class Zipper {
    public void printEntries(PrintStream stream, URL zip) {
        try (ZipFile zipFile = new ZipFile(zip.getFile())) {
            zipFile.stream()
                    .forEach(stream::println);
        } catch (IOException e) {
            throw new RuntimeException("Error opening zip file", e);
        }
    }

    public void unzipFilesOnly(PrintStream stream, Path zipPath) {
        Predicate<ZipEntry> isFile = ze -> !ze.isDirectory();
        try (ZipFile zipFile = new ZipFile(zipPath.toFile())) {
            List<? extends ZipEntry> entries = zipFile.stream().filter(isFile).collect(Collectors.toList());
            // Nasty hack to keep names
            List<String> names = new ArrayList<>();
            List<InputStream> inputStreams = entries.stream().map(ze -> {
                try {
                    names.add(ze.getName());
                    return zipFile.getInputStream(ze);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            // I'm not using a Stream here, because I want an index --due to upper's another awful hack
            for (int i = 0; i < inputStreams.size(); i++) {
                // Copy each stream to a new file
                Files.copy(inputStreams.get(i), resolveAbsolutePath(zipPath, names.get(i) + new Date()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error opening zip file", e);
        }
    }

    private Path resolveAbsolutePath(Path zipPath, String fileName) {
        return zipPath.getParent().toAbsolutePath().resolve(fileName);
    }
}
