package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid required not null");
        Objects.requireNonNull(fullName, "fullName required not null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void addContact(ContactType type, String value) {
        Objects.requireNonNull(value, "value required not null");
        contacts.put(type, value);
    }

    public void deleteContact(ContactType type) {
        contacts.remove(type);
    }

    public void updateContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<ContactType, String> getContactsHtml() {
        Map<ContactType, String> result = new EnumMap<>(ContactType.class);
        contacts.forEach((key, value) -> result.put(key, getContactHtml(key)));
        return result;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public String getContactHtml(ContactType type) {
        String contact = contacts.get(type);
        if (contact != null) {
            String typeLink = "";
            String iconLink;
            switch (type) {
                case PHONE:
                    typeLink = "tel:";
                    iconLink = "<img src='img/phone-32.png'>";
                    break;
                case SKYPE:
                    typeLink = "skype:";
                    iconLink = "<img src='img/social-skype-outline.png'>";
                    break;
                case EMAIL:
                    typeLink = "mailto:";
                    iconLink = "<img src='img/email-32.png'>";
                    break;
                case LINKEDIN:
                    iconLink = "<img src='img/social-linkedin-outline.png'>";
                    break;
                case GITHUB:
                    iconLink = "<img src='img/social-github-outline.png'>";
                    break;
                case STACKOVERFLOW:
                    iconLink = "<img src='img/Stackoverflow.png'>";
                    break;
                default:
                    iconLink = "<img src='img/37-browser-streamline-window.png'>";
            }
            return "<a href='" + typeLink + contact + "'>" + iconLink + contact + "</a>";
        }
        return "";
    }

    public void addSection(SectionType type, AbstractSection abstractSection) {
        sections.put(type, abstractSection);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void upddateSection(SectionType type, AbstractSection abstractSection) {
        sections.put(type, abstractSection);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return uuid + ' ' + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int compare = fullName.compareTo(o.fullName);
        return compare != 0 ? compare : uuid.compareTo(o.uuid);
    }
}
