package com.monopoly;

class CardSpace extends BoardSpace {

    private final CardType type;

    CardSpace(String n, CardType t) {
        super(n);
        type = t;
    }

    CardType getType() {
        return type;
    }
}