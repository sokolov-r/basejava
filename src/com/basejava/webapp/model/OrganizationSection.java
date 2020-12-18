package com.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Organization> organizationList;

    public OrganizationSection() {
    }

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
