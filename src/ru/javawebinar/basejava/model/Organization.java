package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link name;
    private List<Position> positions;

    public Organization() {
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link link, List<Position> positions) {
        name = link;
        this.positions = positions;
    }

    public Link getName() {
        return name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name=" + name +
                ", positions=" + positions +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateBegin;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateEnd;
        private String title;
        private String description;

        public Position() {
        }

        public Position(int beginYear, Month beginMonth, String title, String description) {
            this(of(beginYear, beginMonth), NOW, title, description);
        }

        public Position(int beginYear, Month beginMonth, int endYear, Month endMonth, String title, String description) {
            this(of(beginYear, beginMonth), of(endYear, endMonth), title, description);
        }

        public Position(LocalDate dateBegin, LocalDate dateEnd, String title, String description) {
            Objects.requireNonNull(dateBegin, "dateBegin required not null");
            Objects.requireNonNull(dateEnd, "dateEnd required not null");
            Objects.requireNonNull(title, "title required not null");
            this.dateBegin = dateBegin;
            this.dateEnd = dateEnd;
            this.title = title;
            this.description = description == null ? "" : description;
        }

        public LocalDate getDateBegin() {
            return dateBegin;
        }

        public LocalDate getDateEnd() {
            return dateEnd;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(dateBegin, position.dateBegin) &&
                    Objects.equals(dateEnd, position.dateEnd) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateBegin, dateEnd, title, description);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "dateBegin=" + dateBegin +
                    ", dateEnd=" + dateEnd +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
