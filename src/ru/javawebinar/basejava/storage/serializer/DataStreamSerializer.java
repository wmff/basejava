package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeUTF(resume.getUuid());
            dataOutputStream.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();

            writeCollection(dataOutputStream, contacts.entrySet(), entry -> {
                dataOutputStream.writeUTF(entry.getKey().name());
                dataOutputStream.writeUTF(entry.getValue());
            });

            writeCollection(dataOutputStream, resume.getSections().entrySet(), entry -> {
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
                        writeCollection(dataOutputStream, ((ListSection) section).getItems(), dataOutputStream::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dataOutputStream, ((OrganizationSection) section).getOrganizations(), organization -> {
                            Link link = organization.getName();
                            dataOutputStream.writeUTF(link.getName());
                            dataOutputStream.writeUTF(link.getUrl());

                            writeCollection(dataOutputStream, organization.getPositions(), position -> {
                                writeDate(dataOutputStream, position.getDateBegin());
                                writeDate(dataOutputStream, position.getDateEnd());
                                dataOutputStream.writeUTF(position.getTitle());
                                dataOutputStream.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    private <T> void writeCollection(DataOutputStream dataOutputStream, Collection<T> collection, Action<T> writer) throws IOException {
        dataOutputStream.writeInt(collection.size());
        for (T entry : collection) {
            writer.write(entry);
        }
    }

    private interface Action<T> {
        void write(T t) throws IOException;
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

            readItems(dataInputStream, () -> resume.addContact(ContactType.valueOf(dataInputStream.readUTF()), dataInputStream.readUTF()));

            readItems(dataInputStream, () -> {
                SectionType sectionType = SectionType.valueOf(dataInputStream.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dataInputStream.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(sectionType, new ListSection(
                                readListSection(dataInputStream, dataInputStream::readUTF))
                        );
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.addSection(sectionType,
                                new OrganizationSection(
                                        readListSection(dataInputStream, () -> new Organization(
                                                new Link(dataInputStream.readUTF(), dataInputStream.readUTF()),
                                                readListSection(dataInputStream, () -> new Organization.Position(
                                                        readDate(dataInputStream), readDate(dataInputStream),
                                                        dataInputStream.readUTF(), dataInputStream.readUTF()
                                                ))
                                        ))
                                ));
                        break;
                }
            });
            return resume;
        }
    }

    private void readItems(DataInputStream dataInputStream, Processor processor) throws IOException {
        int size = dataInputStream.readInt();
        for (int i = 0; i < size; i++) {
            processor.read();
        }
    }

    private interface Processor {
        void read() throws IOException;
    }

    private <T> List<T> readListSection(DataInputStream dataInputStream, ElementReader<T> reader) throws IOException {
        int size = dataInputStream.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private LocalDate readDate(DataInputStream dataInputStream) throws IOException {
        return DateUtil.of(dataInputStream.readInt(), Month.of(dataInputStream.readInt()));
    }

}