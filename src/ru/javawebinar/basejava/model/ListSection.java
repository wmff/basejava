package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private List<String> sections;

    public ListSection(String... contents) {
        this(Arrays.asList(contents));
    }

    public ListSection(List<String> sections) {
        Objects.requireNonNull(sections, " sections required not null");
        this.sections = sections;
    }

    public List<String> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return sections.equals(that.sections);
    }

    @Override
    public int hashCode() {
        return sections.hashCode();
    }

    @Override
    public String toString() {
        return sections.toString();
    }
}
