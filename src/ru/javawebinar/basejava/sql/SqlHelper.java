package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SqlHelper {
    private static final Logger LOGGER = Logger.getLogger(SqlHelper.class.getName());
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String sql, SqlExec<T> sqlExec) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            LOGGER.info(preparedStatement.toString());
            return sqlExec.execute(preparedStatement);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(e);
            } else {
                throw new StorageException(e);
            }
        }
    }

    public interface SqlExec<T> {
        T execute(PreparedStatement preparedStatement) throws SQLException;
    }
}
