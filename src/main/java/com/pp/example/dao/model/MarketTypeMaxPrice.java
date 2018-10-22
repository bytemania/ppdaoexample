package com.pp.example.dao.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class MarketTypeMaxPrice {

    public enum MarketType {PRE_MATCH, BIR}

    @NonNull
    private final Long numerator;

    @NonNull
    private final Long denominator;

    @NonNull
    abstract MarketType getMarketType();

}
