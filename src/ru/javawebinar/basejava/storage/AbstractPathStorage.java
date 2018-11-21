package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory required not null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
//        return Files.ge
        return null;
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
            saveInStorage(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException(path.getFileName() + "IO error", null, e);
        }

    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return loadFromStorage(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException(path.getFileName() + "read error", null, e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException(path.getFileName() + " delete error", null, e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            saveInStorage(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException(path.getFileName() + "IO error", null, e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list = new ArrayList<>(size());
        try {
            Stream<Path> stream = Files.list(directory);
            for (Path path : stream.collect(Collectors.toList())) {
                list.add(doGet(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract void saveInStorage(Resume resume, OutputStream outputStream) throws IOException;

    abstract Resume loadFromStorage(InputStream inputStream) throws IOException;
}
