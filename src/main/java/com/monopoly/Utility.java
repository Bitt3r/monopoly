package com.monopoly;

import java.util.List;

class Utility extends Property {

    Utility(String n) {
        super(n, 150);
    }

    @Override
    int rent(List<BoardSpace> allProperties, int roll) {
        int count = (int) allProperties
                .stream()
                .filter(p -> p instanceof Utility)
                .filter(p -> ((Utility) p)
                        .getOwner() == getOwner())
                .count();

        return count == 1 ? roll * 4 : roll * 10;
    }

}
