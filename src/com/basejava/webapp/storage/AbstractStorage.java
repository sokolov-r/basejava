package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size() >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            insertElementByIndex(resume, index);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResumeByIndex(index);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            setResumeByIndex(resume, index);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteElementByIndex(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public abstract void clear();

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract int getIndex(String uuid);

    protected abstract void insertElementByIndex(Resume resume, int index);

    protected abstract Resume getResumeByIndex(int index);

    protected abstract void setResumeByIndex(Resume resume, int index);

    protected abstract void deleteElementByIndex(int index);
}
