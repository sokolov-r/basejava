package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection implements Section {
    private final List<String> textList;

    public ListSection(List<String> textList) {
        Objects.requireNonNull(textList, "textList must not be null");
        this.textList = textList;
    }

    public List<String> getTextList() {
        return textList;
    }

    @Override
    public String toString() {
        String result = "" ;
        for (String text : textList) {
            result = result + text + "\n\n" ;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(textList, that.textList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textList);
    }
}
