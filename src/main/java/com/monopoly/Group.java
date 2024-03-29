package com.monopoly;

public enum Group {

    PURPLE(2, 50),
    LIGHT_BLUE(3, 50),
    PINK(3, 100),
    ORANGE(3, 100),
    RED(3, 150),
    YELLOW(3, 150),
    GREEN(3, 200),
    BLUE(2, 200);

    private final int numberOfProperties;
    private final int housePrice;

    Group(int num, int housePrice) {
        numberOfProperties = num;
        this.housePrice = housePrice;
    }

    public int getBuildPrice() {
        return housePrice;
    }

    public int getUnbuildPrice() {
        return housePrice / 2;
    }

    public int getNumberOfProperties() {
        return numberOfProperties;
    }

}