package ru.javawebinar.basejava.exception;

public class StorageException extends RuntimeException {
    private static final long serialVersionUID = -8436957940089566597L;
    private final String uuid;

    public StorageException(String message) {
        this(message, null, null);
    }

    public StorageException(Exception e) {
        this(e.getMessage(), e);
    }

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, Exception e) {
        this(message, null, e);
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}