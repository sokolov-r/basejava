package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private SerializeStrategy serializeStrategy;

    protected PathStorage(String dir, SerializeStrategy serializeStrategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(serializeStrategy, "serializeStrategy must not be null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writable");
        }
        this.serializeStrategy = serializeStrategy;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Path size read error", null);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.toString(), uuid);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            String str = path.toString();
            throw new StorageException("Couldn't create file on path " + str, null, e);
        }
        doUpdate(resume, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializeStrategy.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File read error on path", null, e);
        }
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            serializeStrategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("File write error on path", resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error on path", null, e);
        }
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> resumes = new ArrayList<>();
        try {
            Files.list(directory).forEach(path -> resumes.add(doGet(path)));
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
        return resumes;
    }
}
