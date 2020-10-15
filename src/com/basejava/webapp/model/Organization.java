package com.basejava.webapp.model;

import java.time.LocalDate;

public class Organization {
    private String name;
    private String link;
    private LocalDate dateStart;
    private LocalDate dateFinish;
    private String post;
    private String text;

    public Organization(String name, String link, LocalDate dateStart, LocalDate dateFinish, String post, String text) {
        this.name = name;
        this.link = link;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.post = post;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public String getPost() {
        return post;
    }

    public String getText() {
        return text;
    }
}
