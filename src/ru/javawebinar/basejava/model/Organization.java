package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;

public class Organization {
    private final Link name;
    private List<Position> positions;

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    private Organization(Link link, List<Position> positions) {
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
        if (!super.equals(o)) return false;

        Organization that = (Organization) o;

        if (!name.equals(that.name)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name=" + name +
                ", positions=" + positions +
                '}';
    }
}
