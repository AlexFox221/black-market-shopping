import cz.uhk.fim.pro2.shopping.model.Child;
import cz.uhk.fim.pro2.shopping.model.GenderType;
import cz.uhk.fim.pro2.shopping.model.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Testovaci trida pro nakupni kosik
 */
public class ShoppingCartTest {

    // reference na testovanou tridu
    private ShoppingCart cart;

    /**
     * Metoda se provadi vzdy pred kazdym testem
     */
    @BeforeEach
    public void setup() {
        this.cart = new ShoppingCart();
    }

    @Test
    @DisplayName("Test na ziskani poctu deti v listu")
    public void test_getChildCount() {
        // test pro prazdny list
        Assertions.assertEquals(0, cart.getChildCount());

        // test pro 2 deti v listu
        List<Child> childList2Childs = new ArrayList<>();
        childList2Childs.add(new Child());
        childList2Childs.add(new Child());
        cart.setChildList(childList2Childs);
        Assertions.assertEquals(2, cart.getChildCount(), "ocekavam 2 deti");
    }

    @Test
    @DisplayName("Test pro overeni pridani deti do listu")
    public void test_addChild() {
        this.cart.addChild(new Child());
        Assertions.assertEquals(1, this.cart.getChildCount());
    }

    @Test
    @DisplayName("Test pro smazani ditete z listu s konkretnim indexem")
    public void test_removeChildOnIndex() {
        Child child1 = new Child();
        child1.setPersonalId("1234567890");
        Child child2 = new Child();
        child2.setPersonalId("0987654321");
        List<Child> childList = new ArrayList<>();
        childList.add(child1);
        childList.add(child2);
        this.cart.setChildList(childList);

        this.cart.removeChild(0);

        Assertions.assertNotEquals(child1, this.cart.getChildList().get(0));
    }

    @Test
    @DisplayName("Test pro smazani ditete z listu pomoci reference")
    public void test_removeChildByObject() {
        Child child1 = new Child();
        child1.setPersonalId("1234567890");
        Child child2 = new Child();
        child2.setPersonalId("0987654321");
        List<Child> childList = new ArrayList<>();
        childList.add(child1);
        childList.add(child2);
        this.cart.setChildList(childList);

        this.cart.removeChild(child1);

        Assertions.assertNotEquals(child1, this.cart.getChildList().get(0));
    }

    @Test
    @DisplayName("Test pro vycisteni celeho kosiku")
    public void test_clearCart() {
        List<Child> childList = new ArrayList<>();
        childList.add(new Child());
        childList.add(new Child());
        childList.add(new Child());
        childList.add(new Child());

        this.cart.setChildList(childList);
        this.cart.clearCart();

        Assertions.assertEquals(0, this.cart.getChildCount());
    }

    @Test
    @DisplayName("Test pro vypocet mezisouctu ceny kosiku")
    public void test_calculateSubtotal() {
        List<Child> childList = new ArrayList<>();
        Child child1 = new Child("987654321", "Karel", 1000.0, new Date(), GenderType.MALE, true, 45.6, true, "Czech", 0xffffff, 0x0000ff, 0xff0a3b, null);
        Child child2 = new Child("123456789", "Shaniqua", 2000.0, new Date(), GenderType.FEMALE, true, 72.8, false, "Afroamerican", 0x000000, 0x00aa00, 0x000000, null);
        childList.add(child1);
        childList.add(child2);

        this.cart.setChildList(childList);

        Assertions.assertEquals(3000.0, this.cart.calculateSubtotal());
    }

    @Test
    @DisplayName("Test pro vypocet celkove ceny kosiku vcetne DPH")
    public void test_calculateTotal() {
        List<Child> childList = new ArrayList<>();
        Child child1 = new Child();
        child1.setPrice(1000.0);
        Child child2 = new Child();
        child2.setPrice(2000.0);
        childList.add(child1);
        childList.add(child2);

        this.cart.setChildList(childList);
        this.cart.setVat(0.21);
        this.cart.setFee(170);

        Assertions.assertEquals(3800.0, this.cart.calculateTotal());
    }
}
