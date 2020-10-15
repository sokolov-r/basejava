package com.basejava.webapp.model;

import java.util.List;

public class OrganizationSection implements Section {
    private List<Organization> post;

    public OrganizationSection(List<Organization> post) {
        this.post = post;
    }

    public List<Organization> getPost() {
        return post;
    }
}
