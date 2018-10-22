package com.pp.example.dao.dao;

import com.pp.example.dao.dao.exception.PersistException;
import com.pp.example.dao.dao.exception.ReadException;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<PK, E> {
    E create(E e) throws PersistException;

    Optional<E> read(@NotNull PK id) throws IllegalArgumentException, ReadException;

    List<E> read() throws IllegalArgumentException, SQLException;

    void update(E e) throws SQLException;

    void delete(PK id) throws SQLException;
}
