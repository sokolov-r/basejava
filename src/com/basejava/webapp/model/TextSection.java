package com.basejava.webapp.model;

public class TextSection implements Section {
    private String text;

    public TextSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
