package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void insert(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insert(resume, index);
            size++;
        }
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }
}
