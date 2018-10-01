package com.pp.example.dao.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<PK, E> {
    E create(E e) throws SQLException;

    Optional<E> read(PK id) throws IllegalArgumentException, SQLException;

    List<E> read() throws IllegalArgumentException, SQLException;

    void update(E e) throws SQLException;

    void delete(PK id) throws SQLException;
}
