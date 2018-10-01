package com.pp.example.dao.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Address implements BaseEntity<Long> {
    @NonNull
    private final Long id;
    @NonNull
    private final String Address;

    public Long getId() {
        return id;
    }
}
