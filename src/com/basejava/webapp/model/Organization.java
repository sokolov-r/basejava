package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final String name;
    private final String link;
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final String jobTitle;
    private final String jobDescription;

    public Organization(String name, String link, LocalDate startDate, LocalDate finishDate, String jobTitle,
                        String jobDescription) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(finishDate, "finishDate must not be null");
        Objects.requireNonNull(jobTitle, "jobTitle must not be null");
        this.name = name;
        this.link = link;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    @Override
    public String toString() {
        String result = "" ;
        result = result + name + "\n" + Format.dtf.format((startDate)) + " - " + Format.dtf.format((finishDate)) + "\n" +
                jobTitle + "\n" + jobDescription + "\n" ;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(link, that.link) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(finishDate, that.finishDate) &&
                Objects.equals(jobTitle, that.jobTitle) &&
                Objects.equals(jobDescription, that.jobDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link, startDate, finishDate, jobTitle, jobDescription);
    }
}
