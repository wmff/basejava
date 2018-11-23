package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException {

    private static final long serialVersionUID = -4728495119511215678L;

    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}