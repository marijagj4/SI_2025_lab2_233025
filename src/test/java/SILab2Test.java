import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        // null list
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567812345678"));
        assertEquals("allItems list can't be null!", ex.getMessage());
        double result;

        // invalid item (null name)
        Item i1 = new Item(null, 1, 100, 0);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(i1), "1234567812345678"));
        assertEquals("Invalid item!", ex.getMessage());


        // invalid card number
        Item i2 = new Item("Test", 1, 100, 0);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(i2), "abc"));
        assertEquals("Invalid card number!", ex.getMessage());

        // test -30 rule
        Item i3 = new Item("Expensive", 5, 400, 0);
        result = SILab2.checkCart(List.of(i3), "1234567812345678");
        assertEquals(400 * 5 - 30, result);

        Item i4 = new Item("Bread", 1, 60, 0.0);
         ex = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(List.of(i4), "12345678901234ab")
        );
        assertEquals("Invalid character in card number!", ex.getMessage());

        Item i5 = new Item("Milk", 2, 50, 0.0); // 2 * 50 = 100
         result = SILab2.checkCart(List.of(i5), "1234567812345678");
        assertEquals(100.0, result);
    }

    @Test
    public void testMultipleCondition() {
        // cardNumber null
        Item item = new Item("Item", 1, 100, 0);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item), null));
        assertEquals("Invalid card number!", ex.getMessage());

        // cardNumber with length ≠ 16
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item), "123"));
        assertEquals("Invalid card number!", ex.getMessage());

        // cardNumber with invalid char
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item), "12345678901234ab"));
        assertEquals("Invalid character in card number!", ex.getMessage());

        // valid card
        double result = SILab2.checkCart(List.of(item), "1234567890123456");
        assertEquals(100, result);
    }
}
