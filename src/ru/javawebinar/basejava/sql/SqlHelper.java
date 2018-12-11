package ru.javawebinar.basejava.sql;

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
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SqlExec<T> sqlExec) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            return sqlExec.execute(preparedStatement);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw ExceptionUtil.convertException(e);
        }
    }

    public <T> void transactionalExecute(SqlTransaction<T> executor) {
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                connection.setAutoCommit(false);
//                T response = executor.execute(connection);
                executor.execute(connection);
                connection.commit();
//                return response;
            } catch (SQLException e) {
                connection.rollback();
                throw ExceptionUtil.convertException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StorageException(e);
        }
    }

    public interface SqlExec<T> {
        T execute(PreparedStatement preparedStatement) throws SQLException;
    }

    public interface SqlTransaction<T> {
        void execute(Connection connection) throws SQLException;
    }

}
