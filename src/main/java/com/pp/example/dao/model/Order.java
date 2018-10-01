package com.pp.example.dao.model;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Order implements BaseEntity<Long> {

    @NonNull
    private final Long id;

    //EAGER
    @NonNull
    private final Customer customer;

    //LAZY
    @Setter
    private List<Item> items;

    @Override
    public Long getId() {
        return id;
    }
}
