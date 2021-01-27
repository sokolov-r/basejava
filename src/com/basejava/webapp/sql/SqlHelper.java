package com.basejava.webapp.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T transactionExecute(String sql, ExecuteExpression<T> executeExpression) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executeExpression.executeExp(ps);
        } catch (SQLException e) {
            throw ExeptionUtil.convertException(e);
        }
    }
}
