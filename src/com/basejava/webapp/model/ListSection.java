package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection implements Section {
    private List<String> listText;

    public ListSection(List<String> listText) {
        this.listText = listText;
    }

    public List<String> getListText() {
        return new ArrayList<>(listText);
    }
}
