package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    private final StreamSerializable streamSerializable = new ObjectStreamSerializable();

    PathStorage(String dir) {
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
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
            streamSerializable.saveInStorage(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException(path.getFileName() + "IO error", null, e);
        }

    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamSerializable.loadFromStorage(new BufferedInputStream(Files.newInputStream(path)));
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
            streamSerializable.saveInStorage(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException(path.getFileName() + "IO error", null, e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list;
        try {
            list = Files.list(directory).map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("IO error", null, e);
        }
        return list;
    }

    @Override
    public int size() {
        int size = 0;
        try {
            size = (int) Files.list(directory).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }
}
