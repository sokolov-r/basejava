package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            int index = check(resume.getUuid());
            if (index < 0) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Resume " + resume.getUuid() + " is already in the storage");
            }
        } else {
            System.out.println("Storage is full");
        }
    }

    public void update(Resume resume) {
        int index = check(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " is not in the storage");
        }
    }

    public Resume get(String uuid) {
        int index = check(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Resume " + uuid + " is not in the storage");
        return null;
    }

    public void delete(String uuid) {
        int index = check(uuid);
        if (index >= 0) {
            for (int j = index; j < size; j++) {
                if (j < 9_999) {
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int check(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
