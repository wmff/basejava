package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
//    private static final File PROPERTIES_FILE = new File(getHomeDir() + "/config/resumes.properties");
    private static final String PROPERTIES_FILE = "/resumes.properties";
    private static final Config INSTANCE = new Config();

    private final File storageDir;
    private final Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream inputStream = Config.class.getResourceAsStream(PROPERTIES_FILE)) {
//        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            storageDir = new File(properties.getProperty("storage.dir"));

            Class.forName("org.postgresql.Driver");
            storage = new SqlStorage(properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );

        } catch (IOException | ClassNotFoundException e) {
//            throw new IllegalStateException("Invalid config file " + PROPERTIES_FILE.getAbsolutePath());
            throw new IllegalStateException("Invalid config file " + PROPERTIES_FILE);
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }

    private static File getHomeDir() {
        String prop = System.getProperty("homeDir");
        File homeDir = new File(prop == null ? "." : prop);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + " is not directory");
        }
        return homeDir;
    }
}
