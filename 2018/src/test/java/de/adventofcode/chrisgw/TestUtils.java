package de.adventofcode.chrisgw;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class TestUtils {


    public static List<String> readAllLinesOfClassPathResource(String classpathResource) {
        try {
            return Files.readAllLines(getResourcePath(classpathResource));
        } catch (Exception e) {
            throw new RuntimeException("Could not read all lines of classpath resource: " + classpathResource, e);
        }
    }


    public static Path getResourcePath(String classpathResource) throws URISyntaxException {
        URL classpathResourceURL = TestUtils.class.getResource(classpathResource);
        return Paths.get(classpathResourceURL.toURI());
    }


}
