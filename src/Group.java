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

    int getBuildPrice() {
        return housePrice;
    }

    int getUnbuildPrice() {
        return housePrice / 2;
    }

    int getNumberOfProperties() {
        return numberOfProperties;
    }

}