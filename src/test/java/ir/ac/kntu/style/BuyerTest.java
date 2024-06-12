package ir.ac.kntu.style;

import ir.ac.kntu.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuyerTest {
    private Buyer buyer;
    private Ads ad;
    private Seller seller;

    @BeforeEach
    public void setUp() {
        seller = new Seller("John", "rr","john@example.com", "09123456789", 1, 2);
        buyer = new Buyer("Alice", "password", "alice@example.com", "1234567890", 1, 2);
        ad = new Ads("Product 1", seller, 100, ProductKind.MOBILE_PHONE, AdsStatus.SOLD);
    }

    @Test
    public void testCheckUsernameAndPasswordCorrect() {
        boolean result = buyer.checkUsernameAndPassword("Alice", "password");
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckUsernameAndPasswordIncorrect() {
        boolean result = buyer.checkUsernameAndPassword("Alice", "wrong_password");
        Assertions.assertFalse(result);
    }

    @Test
    public void testAddToSavedBox() {
        buyer.addToSavedBox(ad);
        Assertions.assertEquals(1, buyer.getSavedBox().size());
        Assertions.assertEquals(ad, buyer.getSavedBox().get(0));
    }

    @Test
    public void testGetXLocation() {
        Assertions.assertEquals(1, buyer.getXLocation());
    }

    @Test
    public void testGetYLocation() {
        Assertions.assertEquals(2, buyer.getYLocation());
    }
}

