package com.pp.example.dao.model;

import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
