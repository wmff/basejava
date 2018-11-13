package ru.javawebinar.basejava.model;

public class Organization extends OrganizationSection {
    private final String name;
    private final String url;
    private final String dateBegin;
    private final String dateEnd;
    private final String description;

    public Organization(String name, String url, String dateBegin, String dateEnd, String description) {
        this.name = name;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.description = description;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Organization that = (Organization) o;

        if (!name.equals(that.name)) return false;
        if (!url.equals(that.url)) return false;
        if (!dateBegin.equals(that.dateBegin)) return false;
        if (!dateEnd.equals(that.dateEnd)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + dateBegin.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name + ' ' + url + ' ' + dateBegin + ' ' +
                dateEnd + ' ' + description;
    }
}
