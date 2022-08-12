import java.util.Random;


class Dice {

    private final Random r = new Random();
    private final int[] current = new int[2];

    int[] getCurrent() {
        return current;
    }

    void setCurrent(int x, int y) {
        current[0] = x;
        current[1] = y;
    }

    void roll() {
        //Roll each dice in the set.
        for (int i = 0; i < current.length; i++)
            current[i] = r.nextInt(6) + 1;
    }

    String rollString() {
        roll();
        return toString();
    }

    int rollTotal() {
        roll();
        return total();
    }

    int total() {
        return current[0] + current[1];
    }

    boolean isDoubles() {
        return current[0] == current[1];
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("(");

        //This loop only matters if there are more than 2 dice.
        for (int i = 0; i < current.length - 1; i++)
            ret.append(current[i]).append(", ");
        ret.append(current[current.length - 1]).append(") - ").append(total());

        return ret.toString();
    }
}
