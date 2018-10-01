package com.pp.example.dao.dao.impl;

import java.util.Map;

@FunctionalInterface
public interface Unmapper<E> {
    Map<String, Object> unmapper(E e);
}
