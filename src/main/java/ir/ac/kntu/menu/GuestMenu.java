package ir.ac.kntu.menu;

import ir.ac.kntu.Ads;
import ir.ac.kntu.AdsStatus;
import ir.ac.kntu.Database;

public class GuestMenu {
    public void guestMenu() {
        System.out.println("\u001B[34mAds :\u001B[34m");
        int[] j = {1};
        Database.allAds.stream()
                .filter(ad -> ad.getAdsStatus() == AdsStatus.READY_TO_SALE)
                .forEach(ad -> {
                    System.out.println((j[0]++) + ".Name: " + ad.getProductName() + " -- Seller: " + ad.getSeller().getUsername() + " -- Price: " + ad.getProductPrice() + " -- Category: " + ad.getProductKind());
                });
    }
}
