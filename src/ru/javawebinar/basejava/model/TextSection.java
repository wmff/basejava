package ru.javawebinar.basejava.model;

public class TextSection extends Section {
    private final String content;

    public TextSection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
