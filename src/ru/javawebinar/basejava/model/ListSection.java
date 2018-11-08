package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section {
    private final List<String> contents;

    public ListSection(List<String> contents) {
        this.contents = contents;
    }

    public List<String> getContents() {
        return contents;
    }
}
