package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {
    private final List<String> sections = new ArrayList<>();

    public void addContent(String content) {
        sections.add(content);
    }

    public List<String> getContent() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return sections != null ? sections.equals(that.sections) : that.sections == null;
    }

    @Override
    public int hashCode() {
        return sections != null ? sections.hashCode() : 0;
    }

    @Override
    public String toString() {
        return sections.toString();
    }
}
