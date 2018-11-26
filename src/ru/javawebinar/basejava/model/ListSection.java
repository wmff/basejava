package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private List<String> sections;

    public ListSection() {
    }

    public ListSection(String... contents) {
        this(Arrays.asList(contents));
    }

    private ListSection(List<String> sections) {
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
        return Objects.equals(sections, that.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sections);
    }

    @Override
    public String toString() {
        return sections.toString();
    }
}
