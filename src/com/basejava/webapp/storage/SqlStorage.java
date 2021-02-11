package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.*;
import com.basejava.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContacts(conn, resume);
            insertSections(conn, resume);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            String uuid = resume.getUuid();
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
            }
            deleteContacts(conn, uuid);
            insertContacts(conn, resume);
            deleteSections(conn, uuid);
            insertSections(conn, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "    SELECT * FROM resume" +
                    "     WHERE uuid = ? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }

            try (PreparedStatement ps = conn.prepareStatement("" +
                    "    SELECT * FROM contact" +
                    "     WHERE resume_uuid = ? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SqlStorage.this.addContact(rs, resume);
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("" +
                    "    SELECT * FROM section" +
                    "     WHERE resume_uuid = ? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SqlStorage.this.addSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {

        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "    SELECT * FROM resume" +
                    "  ORDER BY full_name, uuid ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid").trim();
                    Resume resume = resumeMap.get(uuid);
                    if (resume == null) {
                        resume = new Resume(uuid, rs.getString("full_name"));
                        resumeMap.put(uuid, resume);
                    }
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SqlStorage.this.addContact(rs, resumeMap.get(rs.getString("resume_uuid").trim()));
                }
            }

            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SqlStorage.this.addSection(rs, resumeMap.get(rs.getString("resume_uuid").trim()));
                }
            }
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) AS size FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("size");
        });
    }

    private void insertContacts(Connection conn, Resume resume) throws SQLException {
        Map<ContactType, String> contacts = resume.getContacts();
        if (contacts.size() > 0) {
            try (PreparedStatement ps = conn.prepareStatement("" +
                    " INSERT INTO contact (resume_uuid, type, value) " +
                    " VALUES (?,?,?)")) {
                String uuid = resume.getUuid();
                for (Map.Entry<ContactType, String> e : contacts.entrySet()) {
                    ps.setString(1, uuid);
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void insertSections(Connection conn, Resume resume) throws SQLException {
        Map<SectionType, Section> sections = resume.getSections();
        if (sections.size() > 0) {
            try (PreparedStatement ps = conn.prepareStatement("" +
                    " INSERT INTO section (resume_uuid, type, value) " +
                    " VALUES (?,?,?)")) {
                String uuid = resume.getUuid();
                for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                    ps.setString(1, uuid);
                    SectionType sectionType = entry.getKey();
                    ps.setString(2, sectionType.name());
                    String text = null;
                    switch (sectionType) {
                        case PERSONAL:
                        case OBJECTIVE:
                            text = ((TextSection) entry.getValue()).getText();
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATION:
                            List<String> textList = ((ListSection) entry.getValue()).getTextList();
                            StringBuilder sb = new StringBuilder();
                            for (String s : textList) {
                                sb.append(s);
                                sb.append("\n");
                            }
                            text = sb.toString();
                            break;
                    }
                    ps.setString(3, text);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    private void deleteContacts(Connection conn, String uuid) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, uuid);
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    private void deleteSections(Connection conn, String uuid) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
            ps.setString(1, uuid);
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            SectionType sectionType = SectionType.valueOf(rs.getString("type"));
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    resume.addSection(sectionType, new TextSection(value));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATION:
                    List<String> textList = new ArrayList<>(Arrays.asList(value.split("\n")));
                    resume.addSection(sectionType, new ListSection(textList));
                    break;
            }
        }
    }
}
