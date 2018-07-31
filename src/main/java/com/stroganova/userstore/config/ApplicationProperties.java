package com.stroganova.userstore.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ApplicationProperties {

    private final static String APPLICATION_PROPERTIES_PATH = "src/main/resources/application.properties";

    public Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(APPLICATION_PROPERTIES_PATH))
        ) {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("error while loading properties file", e);
        }
    }


}
