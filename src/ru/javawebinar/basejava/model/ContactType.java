package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Тел."),
    SKYPE("Skype"),
    EMAIL("E-Mail"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    URL("Домашнаяя страница");

    private String title;

    ContactType(String value) {
        this.title = value;
    }

    public String getTitle() {
        return title;
    }
}
