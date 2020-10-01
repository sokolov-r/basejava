package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insert(Resume resume, Object index) {
        int i = -((int) index) - 1;
        System.arraycopy(storage, i, storage, i + 1, size - i);
        storage[i] = resume;
    }

    @Override
    protected Object getKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void fillDeletedElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}
