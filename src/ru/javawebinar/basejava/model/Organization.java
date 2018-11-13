package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization extends OrganizationSection {
    private final Link name;
    private final LocalDate dateBegin;
    private final LocalDate dateEnd;
    private final String title;
    private final String description;

    public Organization(String name, String url, LocalDate dateBegin, LocalDate dateEnd, String title, String description) {
        Objects.requireNonNull(dateBegin, "dateBegin required not null");
        Objects.requireNonNull(dateEnd, "dateEnd required not null");
        Objects.requireNonNull(title, "title required not null");
        this.name = new Link(name, url);
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Organization that = (Organization) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!dateBegin.equals(that.dateBegin)) return false;
        if (!dateEnd.equals(that.dateEnd)) return false;
        if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + dateBegin.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name +
                " " + dateBegin +
                " " + dateEnd +
                " " + title +
                " " + description;
    }
}
