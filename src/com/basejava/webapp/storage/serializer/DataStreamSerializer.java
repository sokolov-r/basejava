package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements SerializeStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeCollection(resume.getContacts().entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeCollection(resume.getSections().entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) entry.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        List<String> list = ((ListSection) entry.getValue()).getTextList();
                        writeCollection(list, dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = ((OrganizationSection) entry.getValue()).getOrganizationList();
                        writeCollection(organizationList, dos, organization -> {
                            dos.writeUTF(organization.getName());
                            String link = organization.getLink();
                            dos.writeUTF(link == null ? "" : link);
                            List<Organization.Position> positionList = organization.getPositionList();
                            writeCollection(positionList, dos, position -> {
                                dos.writeLong(position.getStartDate().toEpochDay());
                                dos.writeLong(position.getFinishDate().toEpochDay());
                                dos.writeUTF(position.getJobTitle());
                                String jobPosition = position.getJobDescription();
                                dos.writeUTF(jobPosition == null ? "" : jobPosition);
                            });

                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            readCollection(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readCollection(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        List<String> list = new ArrayList<>();
                        readCollection(dis, () -> list.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizationList = new ArrayList<>();
                        readCollection(dis, () -> {
                            List<Organization.Position> positionList = new ArrayList<>();
                            String name = dis.readUTF();
                            String link = dis.readUTF();
                            Organization organization = new Organization(name, link.equals("") ? null : link, positionList);
                            readCollection(dis, () -> {
                                LocalDate startDay = LocalDate.ofEpochDay(dis.readLong());
                                LocalDate finishDay = LocalDate.ofEpochDay(dis.readLong());
                                String jobTitle = dis.readUTF();
                                String jobDescription = dis.readUTF();
                                positionList.add(new Organization.Position(startDay,
                                        finishDay, jobTitle, jobDescription.equals("") ? null : jobDescription));
                            });
                            organizationList.add(organization);
                        });
                        resume.addSection(sectionType, new OrganizationSection(organizationList));
                        break;
                }
            });
            return resume;
        }
    }

    @FunctionalInterface
    private interface WriterElements<T> {
        void write(T t) throws IOException;
    }

    @FunctionalInterface
    private interface ReaderElements {
        void read() throws IOException;
    }

    private <T> void writeCollection(Collection<T> collection, DataOutputStream dos, WriterElements<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    private void readCollection(DataInputStream dis, ReaderElements reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}
