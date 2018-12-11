package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("TRUNCATE TABLE resume CASCADE ");
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                preparedStatement.setString(1, resume.getUuid());
                preparedStatement.setString(2, resume.getFullName());
                preparedStatement.execute();
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                    preparedStatement.setString(1, resume.getUuid());
                    preparedStatement.setString(2, entry.getKey().name());
                    preparedStatement.setString(3, entry.getValue());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        });
    }

    @Override
    public Resume get(String uuid) {
//        https://stackoverflow.com/questions/7886462/how-to-get-row-count-using-resultset-in-java
        return sqlHelper.execute("SELECT *, count(*) over() total_rows FROM resume r\n" +
                "  LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, resultSet.getString("full_name"));
            do {
                if (Integer.parseInt(resultSet.getString("total_rows")) > 1) {
                    if (!resultSet.getString("resume_uuid").isEmpty()) {
                        String value = resultSet.getString("value");
                        ContactType contactType = ContactType.valueOf(resultSet.getString("type"));
                        resume.addContact(contactType, value);
                    }
                }
            } while (resultSet.next());
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                preparedStatement.setString(1, resume.getFullName());
                preparedStatement.setString(2, resume.getUuid());
                if (preparedStatement.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact SET value = ? WHERE type = ? AND resume_uuid = ?")) {
                for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                    preparedStatement.setString(1, entry.getValue());
                    preparedStatement.setString(2, entry.getKey().name());
                    preparedStatement.setString(3, resume.getUuid());
                    if (preparedStatement.executeUpdate() != 0) {
                        preparedStatement.addBatch();
                    } else {
                        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                            ps.setString(1, resume.getUuid());
                            ps.setString(2, entry.getKey().name());
                            ps.setString(3, entry.getValue());
                        }
                    }
                }
                preparedStatement.executeBatch();
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name, uuid", preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Resume> list = new ArrayList<>(size());
            while (resultSet.next()) {
                Resume resume = get(resultSet.getString("uuid"));
                list.add(resume);
            }
            return list;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException("");
            }
            return resultSet.getInt(1);
        });
    }
}
