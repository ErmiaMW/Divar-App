package ir.ac.kntu.style;

import ir.ac.kntu.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTest {
    private Admin admin;
    private Seller seller;
    private Buyer buyer;
    private Ads ad;

    @BeforeEach
    public void setUp() {
        admin = new Admin("adminUser", "adminPassword", "admin@example.com", "1234567890");
        seller = new Seller("sellerUser", "sellerPassword", "seller@example.com", "0987654321", 1, 1);
        buyer = new Buyer("buyerUser", "buyerPassword", "buyer@example.com", "9876543210", 0, 0);
        ad = new Ads("Product 1", seller, 100, ProductKind.MOBILE_PHONE, AdsStatus.READY_TO_SALE);

        Database.allSeller.add(seller);
        Database.allBuyer.add(buyer);
        Database.allAds.add(ad);
    }

    @Test
    public void testDeleteSeller() {
        admin.deleteSeller(seller);
        Assertions.assertFalse(Database.allSeller.contains(seller));
    }

    @Test
    public void testDeleteBuyer() {
        admin.deleteBuyer(buyer);
        Assertions.assertFalse(Database.allBuyer.contains(buyer));
    }

    @Test
    public void testDeleteAd() {
        admin.deleteAd(ad);
        Assertions.assertFalse(Database.allAds.contains(ad));
    }
}

