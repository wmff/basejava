package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;

public class FileStorage extends AbstractFileStorage {

    protected FileStorage(File directory) {
        super(directory);
    }

    @Override
    void doWrite(Resume resume, File file) {

    }
}
