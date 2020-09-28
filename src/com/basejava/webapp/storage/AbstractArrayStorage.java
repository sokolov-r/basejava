package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

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
    protected void insertElementByIndex(Resume resume, int index) {
        insertElement(resume, index);
        size++;
    }

    @Override
    protected Resume getResumeByIndex(int index) {
        return storage[index];
    }

    @Override
    protected void setResumeByIndex(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void deleteElementByIndex(int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}
