package com.pp.example.dao.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class MarketTypeMaxPrice implements BaseEntity<Long> {

    public enum MarketType {PRE_MATCH, BIR}

    @NonNull
    private final Long id;

    @NonNull
    private final Long numerator;

    @NonNull
    private final Long denominator;

    @NonNull
    abstract MarketType getMarketType();

    public Long getId() {
        return id;
    }

}
