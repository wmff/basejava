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
        this("", "");
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid required not null");
        Objects.requireNonNull(fullName, "fullName required not null");
        this.uuid = uuid;
        this.fullName = fullName;
        initSections();
    }

    private void initSections() {
        for (SectionType sectionType : SectionType.values()) {
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    this.addSection(sectionType, new TextSection(""));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    this.addSection(sectionType, new ListSection(""));
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    this.addSection(sectionType, new OrganizationSection(
                            new Organization("", "", new Organization.Position())
                    ));
                    break;
            }
        }
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getContact(ContactType type) {
        return contacts.get(type);
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
