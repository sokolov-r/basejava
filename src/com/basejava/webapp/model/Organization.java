package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final String name;
    private final String link;
    private final List<Position> positionList;

    public Organization(String name, String link, List<Position> positionList) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(positionList, "positionList must not be null");
        this.name = name;
        this.link = link;
        this.positionList = positionList;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", positionList=" + positionList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return name.equals(that.name) &&
                Objects.equals(link, that.link) &&
                positionList.equals(that.positionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link, positionList);
    }
}
