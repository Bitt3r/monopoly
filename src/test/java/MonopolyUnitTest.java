import com.monopoly.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonopolyUnitTest {

    @Test
    public void testDiceRoll() {
        Dice dice = new Dice();
        dice.roll();
        int[] current = dice.getCurrent();
        Assertions.assertTrue(current[0] >= 1 && current[0] <= 6);
        Assertions.assertTrue(current[1] >= 1 && current[1] <= 6);
    }

    @Test
    public void testGroup() {
        Group group = Group.PURPLE;
        Assertions.assertEquals(2, group.getNumberOfProperties());
        Assertions.assertEquals(50, group.getBuildPrice());
        Assertions.assertEquals(25, group.getUnbuildPrice());
    }

    @Test
    public void testPlayerInitialization() {
        Player player = new Player("John");
        Assertions.assertEquals("John", player.getName());
        Assertions.assertEquals(0, player.getPos());
        Assertions.assertEquals(1500, player.getMoney());
        Assertions.assertFalse(player.isInJail());
        Assertions.assertFalse(player.isTurn());
    }

    // Example test for Property
    @Test
    public void testProperty() {
        Property property = new Utility("Electric Company");
        Assertions.assertEquals("Electric Company", property.getName());
        Assertions.assertEquals(150, property.getPrice());
        Assertions.assertNull(property.getOwner());
        Assertions.assertFalse(property.isMortgaged());
    }

    // Example test for Railroad
    @Test
    public void testRailroadRent() {
        Player owner = new Player("Owner");
        Railroad railroad1 = new Railroad("Reading Railroad");
        Railroad railroad2 = new Railroad("Pennsylvania Railroad");
        railroad1.setOwner(owner);
        railroad2.setOwner(owner);

        List<BoardSpace> allProperties = new ArrayList<>();
        allProperties.add(railroad1);
        allProperties.add(railroad2);

        int rent1 = railroad1.rent(allProperties, 3);
        int rent2 = railroad2.rent(allProperties, 3);

        Assertions.assertEquals(50, rent1);
        Assertions.assertEquals(50, rent2);
    }

    @Test
    public void testPropertyIsMortgaged() {
        Player owner = new Player("Owner");
        Property property = new TestProperty("Property", 200);
        property.setOwner(owner);

        property.mortgage(Collections.emptyList());

        Assertions.assertTrue(property.isMortgaged());
    }

    @Test
    public void testPlayerGainMoney() {
        Player player = new Player("John");
        int initialMoney = player.getMoney();
        int amount = 200;

        player.gainMoney(amount);

        Assertions.assertEquals(initialMoney + amount, player.getMoney());
    }

    @Test
    public void testPlayerLoseMoney() {
        Player player = new Player("John");
        player.gainMoney(500);
        int initialMoney = player.getMoney();
        int amount = 200;

        player.loseMoney(amount);

        Assertions.assertEquals(initialMoney - amount, player.getMoney());
    }

    @Test
    public void testPropertySetOwner() {
        Player owner = new Player("Owner");
        Property property = new TestProperty("Property", 200);

        property.setOwner(owner);

        Assertions.assertEquals(owner, property.getOwner());
    }

    @Test
    public void testPropertyUnmortgage() {
        Player owner = new Player("Owner");
        Property property = new TestProperty("Property", 200);
        property.setOwner(owner);
        property.mortgage(Collections.emptyList());

        property.unmortgage();

        Assertions.assertFalse(property.isMortgaged());
    }

    @Test
    public void testColorPropertyRent() {
        Player owner = new Player("Owner");
        ColorProperty property = new ColorProperty("Property", Group.BLUE, 200, new int[]{50, 100});
        property.setOwner(owner);

        int rent = property.rent(Collections.emptyList(), 3);

        Assertions.assertEquals(50, rent);
    }


    static class TestProperty extends Property {

        TestProperty(String n, int p) {
            super(n, p);
        }

        @Override
        public int rent(List<BoardSpace> allProperties, int roll) {
            // Implement rent calculation for testing
            return 0;
        }
    }


}