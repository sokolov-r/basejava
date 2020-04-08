package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < 10000) {
            int checkResult = check(resume.getUuid());
            if (checkResult < 0) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("This resume is already in the storage");
            }
        } else {
            System.out.println("Storage is full");
        }
    }

    private int check(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume resume) {
        int checkResult = check(resume.getUuid());
        if (checkResult >= 0) {
            storage[checkResult] = resume;
        } else {
            System.out.println("This resume is not in the storage");
        }
    }

    public Resume get(String uuid) {
        int checkResult = check(uuid);
        if (checkResult >= 0) {
            return storage[checkResult];
        } else {
            System.out.println("This resume is not in the storage");
            return null;
        }

    }

    public void delete(String uuid) {
        int checkResult = check(uuid);
        if (checkResult >= 0) {
            for (int j = checkResult; j < size; j++) {
                if (j < 9999) {
                    storage[j] = storage[j + 1];
                } else {
                    storage[j] = null;
                }
            }
            size--;
        } else {
            System.out.println("This resume is not in the storage");
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
}
