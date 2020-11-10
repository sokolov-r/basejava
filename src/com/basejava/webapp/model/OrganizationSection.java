package com.basejava.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OrganizationSection implements Section, Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Organization> organizationList;

    public OrganizationSection(List<Organization> organizationList) {
        Objects.requireNonNull(organizationList, "organizationList must not be null");
        this.organizationList = organizationList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organization organization : organizationList) {
            sb.append(organization.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organizationList.equals(that.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }
}
