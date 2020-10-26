package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final String jobTitle;
    private final String jobDescription;

    public Position(LocalDate startDate, LocalDate finishDate, String jobTitle, String jobDescription) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(finishDate, "finishDate must not be null");
        Objects.requireNonNull(jobTitle, "jobTitle must not be null");
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }

    @Override
    public String toString() {
        return "Position{" +
                "startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return startDate.equals(position.startDate) &&
                finishDate.equals(position.finishDate) &&
                jobTitle.equals(position.jobTitle) &&
                Objects.equals(jobDescription, position.jobDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, finishDate, jobTitle, jobDescription);
    }
}
