package com.basejava.webapp.model;

import com.basejava.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String link;
    private List<Position> positionList;

    public Organization() {
    }

    public Organization(String name, String link, List<Position> positionList) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(positionList, "positionList must not be null");
        this.name = name;
        this.link = link;
        this.positionList = positionList;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public List<Position> getPositionList() {
        return positionList;
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finishDate;
        private String jobTitle;
        private String jobDescription;

        public Position() {
        }

        public Position(LocalDate startDate, LocalDate finishDate, String jobTitle, String jobDescription) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(finishDate, "finishDate must not be null");
            Objects.requireNonNull(jobTitle, "jobTitle must not be null");
            this.startDate = startDate;
            this.finishDate = finishDate;
            this.jobTitle = jobTitle;
            this.jobDescription = jobDescription;
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
}
