package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    protected ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElementByIndex(Resume resume, int index) {
        storage.add(index, resume);
    }

    @Override
    protected Resume getResumeByIndex(int index) {
        return storage.get(index);
    }

    @Override
    protected void setResumeByIndex(Resume resume, int index) {
        storage.set(index, resume);
    }

    @Override
    protected void deleteElementByIndex(int index) {
        storage.remove(index);
    }
}
