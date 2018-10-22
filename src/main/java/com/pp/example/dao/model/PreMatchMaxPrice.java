package com.pp.example.dao.model;

import lombok.NonNull;

public class PreMatchMaxPrice extends MarketTypeMaxPrice {

    public PreMatchMaxPrice(@NonNull Long numerator, @NonNull Long denominator) {
        super(numerator, denominator);
    }

    @Override
    @NonNull
    MarketType getMarketType() {
        return MarketType.PRE_MATCH;
    }
}
