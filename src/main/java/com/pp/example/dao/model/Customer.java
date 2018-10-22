package com.pp.example.dao.model;

import lombok.*;

import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Customer implements BaseEntity<Long> {
    @NonNull
    private final Long id;
    @NonNull
    private final String name;

    //EAGER
    @NonNull
    private final Address address;

    @Setter
    private Long referrerId;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
