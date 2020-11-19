package com.basejava.webapp.storage.serializer;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializeStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                if (entry.getKey() == SectionType.PERSONAL || entry.getKey() == SectionType.OBJECTIVE) {
                    dos.writeUTF(((TextSection) entry.getValue()).getText());
                }
                if (entry.getKey() == SectionType.ACHIEVEMENT || entry.getKey() == SectionType.QUALIFICATION) {
                    List<String> list = ((ListSection) entry.getValue()).getTextList();
                    dos.writeInt(list.size());
                    for (String s : list) {
                        dos.writeUTF(s);
                    }
                }
                if (entry.getKey() == SectionType.EXPERIENCE || entry.getKey() == SectionType.EDUCATION) {
                    List<Organization> organizationList = ((OrganizationSection) entry.getValue()).getOrganizationList();
                    dos.writeInt(organizationList.size());
                    for (Organization organization : organizationList) {
                        dos.writeUTF(organization.getName());
                        String link = organization.getLink();
                        if (link == null) {
                            dos.writeBoolean(false);
                        } else {
                            dos.writeBoolean(true);
                            dos.writeUTF(organization.getLink());
                        }
                        List<Organization.Position> positionList = organization.getPositionList();
                        dos.writeInt(positionList.size());
                        for (Organization.Position position : positionList) {
                            dos.writeLong(position.getStartDate().toEpochDay());
                            dos.writeLong(position.getFinishDate().toEpochDay());
                            dos.writeUTF(position.getJobTitle());
                            dos.writeUTF(position.getJobDescription());
                        }
                    }
                }
            }

        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                    resume.addSection(sectionType, new TextSection(dis.readUTF()));
                }
                if (sectionType == SectionType.ACHIEVEMENT || sectionType == SectionType.QUALIFICATION) {
                    List<String> list = new ArrayList<>();
                    int sizeList = dis.readInt();
                    for (int j = 0; j < sizeList; j++) {
                        list.add(dis.readUTF());
                    }
                    resume.addSection(sectionType, new ListSection(list));
                }
                if (sectionType == SectionType.EXPERIENCE || sectionType == SectionType.EDUCATION) {
                    List<Organization> organizationList = new ArrayList<>();
                    int sizeOrganizations = dis.readInt();
                    for (int j = 0; j < sizeOrganizations; j++) {
                        List<Organization.Position> positionList = new ArrayList<>();
                        String name = dis.readUTF();
                        boolean existLink = dis.readBoolean();
                        String link = null;
                        if (existLink) {
                            link = dis.readUTF();
                        }
                        Organization organization = new Organization(name, link, positionList);
                        int sizePositions = dis.readInt();
                        for (int k = 0; k < sizePositions; k++) {
                            positionList.add(new Organization.Position(LocalDate.ofEpochDay(dis.readLong()),
                                    LocalDate.ofEpochDay(dis.readLong()), dis.readUTF(), dis.readUTF()));
                        }
                        organizationList.add(organization);
                    }
                    resume.addSection(sectionType, new OrganizationSection(organizationList));
                }
            }
            return resume;
        }
    }
}
