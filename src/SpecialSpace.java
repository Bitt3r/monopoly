import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class SpecialSpace extends BoardSpace {

    private final SpecialType type;
    private final Consumer<Monopoly> func;

    SpecialSpace(String n, SpecialType t) {
        super(n);
        type = t;
        func = functions.get(type);
    }

    void doFunc(Monopoly m) {
        func.accept(m);
    }

    private static final Map<SpecialType, Consumer<Monopoly>> functions = new HashMap<>() {{
        put(SpecialType.INCOME_TAX, m -> {
            Player p = m.getCurrentPlayer();
            int price = Math.min(p.totalAssets(m.getBoardSpaces()) / 10, 200);
            p.loseMoney(price);
            System.out.printf("You paid $%d Income Tax.%n", price);
        });

        put(SpecialType.LUXURY_TAX, m -> {
            int price = 100;
            m.getCurrentPlayer()
                    .loseMoney(price);
            System.out.printf("You paid $%d Luxury Tax.%n", price);
        });

        put(SpecialType.GO_TO_JAIL, m -> {
            m.getCurrentPlayer()
                    .gotoJail();
            System.out.println("You are now in Jail.");
        });
    }};

    SpecialType getType() {
        return type;
    }

}