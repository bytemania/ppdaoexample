package com.pp.example.dao.model;

import lombok.NonNull;

public class BirMaxPrice extends MarketTypeMaxPrice {

    public BirMaxPrice(@NonNull Long numerator, @NonNull Long denominator) {
        super(numerator, denominator);
    }

    @Override
    @NonNull
    MarketType getMarketType() {
        return MarketType.BIR;
    }



}
