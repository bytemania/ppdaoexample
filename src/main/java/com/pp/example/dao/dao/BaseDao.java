package com.pp.example.dao.dao;

import com.pp.example.dao.dao.exception.PersistException;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<PK, E> {
    E create(E e) throws PersistException, SQLException;

    Optional<E> read(@NotNull PK id) throws IllegalArgumentException, SQLException;

    List<E> read() throws IllegalArgumentException, SQLException;

    void update(E e) throws SQLException;

    void delete(PK id) throws SQLException;
}
