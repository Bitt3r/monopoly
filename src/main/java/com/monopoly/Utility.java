package com.monopoly;

import java.util.List;

public class Utility extends Property {

    public Utility(String n) {
        super(n, 150);
    }

    @Override
    public int rent(List<BoardSpace> allProperties, int roll) {
        int count = (int) allProperties
                .stream()
                .filter(p -> p instanceof Utility)
                .filter(p -> ((Utility) p)
                        .getOwner() == getOwner())
                .count();

        return count == 1 ? roll * 4 : roll * 10;
    }

}
