package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object searchKey = getKey(uuid);
        if (searchKey != null) {
            throw new ExistStorageException(uuid);
        } else {
            saveResume(resume, searchKey);
        }
    }

    public Resume get(String uuid) {
        Object searchKey = getKey(uuid);
        if (searchKey == null) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(searchKey);
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object searchKey = getKey(uuid);
        if (searchKey != null) {
            updateResume(resume, searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void delete(String uuid) {
        Object searchKey = getKey(uuid);
        if (searchKey != null) {
            deleteResume(searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Object getKey(String uuid);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);
}
