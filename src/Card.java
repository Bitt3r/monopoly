import java.util.function.Consumer;


class Card {

    private final String text;
    private final Consumer<Monopoly> func;

    Card(String text, Consumer<Monopoly> func) {
        this.text = text;
        this.func = func;
    }

    String getText() {
        return text;
    }

    void doFunc(Monopoly m) {
        func.accept(m);
    }
}
