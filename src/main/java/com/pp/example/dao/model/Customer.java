package com.pp.example.dao.model;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Customer implements BaseEntity<Long>  {
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

}
