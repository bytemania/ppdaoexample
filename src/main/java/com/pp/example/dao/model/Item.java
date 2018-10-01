package com.pp.example.dao.model;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Item implements BaseEntity<Long> {

    @NonNull
    private final Long id;
    @NonNull
    private final String name;
    @Setter
    private String description;

    @Override
    public Long getId() {
        return id;
    }
}
