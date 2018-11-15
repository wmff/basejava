package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class Position {
    private final LocalDate dateBegin;
    private final LocalDate dateEnd;
    private final String title;
    private final String description;

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
        this.description = description;
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

        if (!dateBegin.equals(position.dateBegin)) return false;
        if (!dateEnd.equals(position.dateEnd)) return false;
        if (!title.equals(position.title)) return false;
        return description.equals(position.description);
    }

    @Override
    public int hashCode() {
        int result = dateBegin.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
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
