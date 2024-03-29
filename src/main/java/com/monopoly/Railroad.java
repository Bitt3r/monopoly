package com.monopoly;

import java.util.List;

public class Railroad extends Property {

    public Railroad(String n) {
        super(n, 200);
    }

    @Override
    public int rent(List<BoardSpace> allProperties, int roll) {
        int count = (int) allProperties
                .stream()
                .filter(p -> p instanceof Railroad)
                .filter(p -> ((Railroad) p)
                        .getOwner() == getOwner())
                .count();

        return 25 * (int) Math.pow(2, count - 1);
    }

    @Override
    String getFormattedName() {
        return "\u001B[38;5;8m" + super.getFormattedName();
    }
}
