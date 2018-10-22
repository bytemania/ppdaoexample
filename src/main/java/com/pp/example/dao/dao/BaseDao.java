package com.pp.example.dao.dao;

import com.pp.example.dao.dao.exception.PersistException;
import com.pp.example.dao.dao.exception.ReadException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BaseDao<PK, E> {
    E create(@NotNull E e) throws PersistException;

    Optional<E> read(@NotNull PK id) throws IllegalArgumentException, ReadException;

    List<E> read() throws IllegalArgumentException, ReadException;

    E update(@NotNull E e) throws PersistException;

    void delete(@NotNull PK id) throws PersistException;
}
