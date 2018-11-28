package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());

            Set<Map.Entry<ContactType, String>> contacts = resume.getContacts().entrySet();
            dataOutputStream.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts) {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            }

            Set<Map.Entry<SectionType, AbstractSection>> sections = resume.getSections().entrySet();

            for (Map.Entry<SectionType, AbstractSection> entry : sections) {
                SectionType sectionType = entry.getKey();
                dataOutputStream.writeUTF(sectionType.name());
                AbstractSection section = entry.getValue();
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dataOutputStream.writeUTF(((TextSection) section).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeListSection(dataOutputStream, (ListSection) section);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeOrganizationSection(dataOutputStream, (OrganizationSection) section);
                        break;
                }
            }
        }
    }

    private void writeListSection(DataOutputStream dataOutputStream, ListSection section) throws IOException {
        List<String> list = section.getItems();
        dataOutputStream.writeInt(list.size());
        for (String item : list) {
            dataOutputStream.writeUTF(item);
        }
    }

    private void writeOrganizationSection(DataOutputStream dataOutputStream, OrganizationSection section) throws IOException {
        List<Organization> organizations = section.getOrganizations();
        dataOutputStream.writeInt(organizations.size());
        for (Organization organization : organizations) {
            Link link = organization.getName();
            dataOutputStream.writeUTF(link.getName());
            dataOutputStream.writeUTF(link.getUrl());

            List<Organization.Position> positions = organization.getPositions();
            dataOutputStream.writeInt(positions.size());
            for (Organization.Position position : positions) {
                writeDate(dataOutputStream, position.getDateBegin());
                writeDate(dataOutputStream, position.getDateEnd());
                dataOutputStream.writeUTF(position.getTitle());
                dataOutputStream.writeUTF(position.getDescription());
            }
        }
    }

    private void writeDate(DataOutputStream dataOutputStream, LocalDate date) throws IOException {
        dataOutputStream.writeInt(date.getYear());
        dataOutputStream.writeInt(date.getMonthValue());
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            String uuid = dataInputStream.readUTF();
            String fullName = dataInputStream.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF());
            }

            while (dataInputStream.available() > 0) {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dataInputStream.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(sectionType, readListSection(dataInputStream));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.addSection(sectionType,
                                new OrganizationSection(readOrganizationSection(dataInputStream)));
                        break;
                }
            }
            return resume;
        }
    }

    private ListSection readListSection(DataInputStream dataInputStream) throws IOException {
        int size;
        size = dataInputStream.readInt();
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(dataInputStream.readUTF());
        }
        return new ListSection(list);
    }

    private List<Organization> readOrganizationSection(DataInputStream dataInputStream) throws IOException {
        int size = dataInputStream.readInt();
        List<Organization> organizations = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Link link = new Link(dataInputStream.readUTF(), dataInputStream.readUTF());

            int sizePositions = dataInputStream.readInt();
            List<Organization.Position> positions = new ArrayList<>(sizePositions);
            for (int j = 0; j < sizePositions; j++) {
                positions.add(new Organization.Position(readDate(dataInputStream), readDate(dataInputStream),
                        dataInputStream.readUTF(), dataInputStream.readUTF()));
            }
            organizations.add(new Organization(link, positions));
        }
        return organizations;
    }

    private LocalDate readDate(DataInputStream dataInputStream) throws IOException {
        return DateUtil.of(dataInputStream.readInt(), Month.of(dataInputStream.readInt()));
    }
}