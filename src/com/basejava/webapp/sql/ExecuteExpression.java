package com.basejava.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface ExecuteExpression<T> {
    T executeExp(PreparedStatement ps) throws SQLException;
}
