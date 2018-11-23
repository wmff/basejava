package ru.javawebinar.basejava.exception;

public class ExistStorageException extends StorageException {
    private static final long serialVersionUID = -8152275316536136728L;

    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }
}