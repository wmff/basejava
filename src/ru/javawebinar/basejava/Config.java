package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES_FILE = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();

    private Properties properties = new Properties();
    private File storageDir;

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(inputStream);
            storageDir = new File(properties.getProperty("storage.dir"));

            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPERTIES_FILE.getAbsolutePath());
        }


    }

    public File getStorageDir() {
        return storageDir;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
