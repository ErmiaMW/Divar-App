package ir.ac.kntu.style;

import ir.ac.kntu.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AdsTest {
    private Ads ads;
    private Seller seller;
    private Buyer buyer;

    @BeforeEach
    public void setUp() {
        seller = new Seller("John", "rr","john@example.com", "09123456789", 1, 2);
        buyer = new Buyer("Alice", "ee","alice@example.com", "09213456789", 1, 3);
        ads = new Ads("Product 1", seller, 100, ProductKind.MOBILE_PHONE, AdsStatus.READY_TO_SALE);
    }

    @Test
    public void testAddComment() {
        ads.addComment(buyer, "Great product!");
        Assertions.assertEquals(1, ads.getComment().size());
        Assertions.assertEquals("Great product!", ads.getComment().get(buyer));
    }

    @Test
    public void testGetters() {
        Assertions.assertEquals("Product 1", ads.getProductName());
        Assertions.assertEquals(seller, ads.getSeller());
        Assertions.assertEquals(100, ads.getProductPrice());
        Assertions.assertEquals(ProductKind.MOBILE_PHONE, ads.getProductKind());
    }

    @Test
    public void testSetAdsStatus() {
        ads.setAdsStatus(AdsStatus.SOLD);
        Assertions.assertEquals(AdsStatus.SOLD, ads.getAdsStatus());
    }
}
