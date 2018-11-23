package ru.javawebinar.basejava.exception;

public class StorageException extends RuntimeException {
    private static final long serialVersionUID = -8211432998976510922L;
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}