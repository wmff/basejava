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

    public void execute(String sql) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
            LOGGER.info(preparedStatement.toString());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new StorageException(e);
        }
    }

    public void execute(String sql, SqlExec sqlExec) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            sqlExec.execute(preparedStatement);
            LOGGER.info(preparedStatement.toString());
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException(e);
            } else {
                throw new StorageException(e);
            }
        }
    }

    public interface SqlExec {
        void execute(PreparedStatement preparedStatement) throws SQLException;
    }
}
