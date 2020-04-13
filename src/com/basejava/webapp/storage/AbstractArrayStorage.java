package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " is not in the storage");
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " is not in the storage");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            for (int j = index; j < size; j++) {
                if (j < STORAGE_LIMIT - 1) {
                    storage[j] = storage[j + 1];
                } else {
                    storage[j] = null;
                }
            }
            size--;
        } else {
            System.out.println("Resume " + uuid + " not in the storage");
        }
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
