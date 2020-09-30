package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    protected HashMap<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Resume resumeInStorage = storage.get(uuid);
        if (resumeInStorage != null) {
            throw new ExistStorageException(uuid);
        } else {
            storage.put(uuid, resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume resumeInStorage = storage.get(uuid);
        if (resumeInStorage == null) {
            throw new NotExistStorageException(uuid);
        }
        return resumeInStorage;
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Resume resumeInStorage = storage.get(uuid);
        if (resumeInStorage != null) {
            storage.put(uuid, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        Resume resumeInStorage = storage.get(uuid);
        if (resumeInStorage != null) {
            storage.remove(uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected void saveResume(Resume resume, int index) {
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected void updateResume(Resume resume, int index) {
    }

    @Override
    protected void deleteResume(int index) {
    }
}
