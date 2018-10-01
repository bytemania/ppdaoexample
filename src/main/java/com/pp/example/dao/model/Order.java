package com.pp.example.dao.model;

import lombok.*;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
