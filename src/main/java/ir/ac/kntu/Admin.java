package ir.ac.kntu;

import ir.ac.kntu.menu.AdminMenu;

import java.util.Comparator;

/**
 * @author Ermiamirzaei
 * The Admin class represents an admin user in the system.
 * An admin has the ability to delete sellers, buyers, and ads,
 * as well as find the nearest courier for a given ad.
 */
public class Admin extends Users{
    /**
     * Initializes a new Admin object with the given username,
     * password, email, and phone number.
     *
     * @param username the admin's username
     * @param password the admin's password
     * @param email the admin's email address
     * @param phoneNumber the admin's phone number
     */
    public Admin(String username, String password, String email, String phoneNumber) {
        super(username, password, email, phoneNumber);
    }

    @Override
    public boolean checkUsernameAndPassword(String username, String password) {
        if(this.username.equals(username) && this.password.equals(password)){
            AdminMenu adminMenu = new AdminMenu();
            adminMenu.adminMenu(this);
            return true;
        }
        return false;
    }

    public void deleteSeller(Seller seller){
        if(Database.allSeller.contains(seller)) {
            Database.allSeller.remove(seller);
        }
    }

    public void deleteBuyer(Buyer buyer){
        if(Database.allBuyer.contains(buyer)) {
            Database.allBuyer.remove(buyer);
        }
    }

    public void deleteAd(Ads ad){
        if(Database.allAds.contains(ad) && ad.getAdsStatus() == AdsStatus.READY_TO_SALE) {
            Database.allAds.remove(ad);
        }
    }

    /**
     * Finds the nearest courier for the given ad and product kind and sets the ad's status to waiting for delivery.
     *
     * @param ad the ad for which to find the nearest courier
     * @param productKind the product kind of the ad
     * @return the nearest available courier, or null if no couriers are available
     */
    public Courier findNearestCourier(Ads ad, ProductKind productKind) {
        ad.setAdsStatus(AdsStatus.WAITING_FOR_DELIVERY);

        return Database.allCourier.stream()
                .filter(courier -> (courier.getCourierKind() == CourierKind.MOTOR &&
                        (productKind == ProductKind.CLOTHING || productKind == ProductKind.MOBILE_PHONE ||
                                productKind == ProductKind.STATIONERY)) ||
                        (courier.getCourierKind() == CourierKind.VAN && productKind == ProductKind.HOME_APPLIANCES))
                .filter(Courier::isAvailability)
                .min(Comparator.comparingInt(courier ->
                        (int) Math.sqrt(Math.pow(courier.getXLocation() - ad.getSeller().getXLocation(), 2) +
                                Math.pow(courier.getYLocation() - ad.getSeller().getYLocation(), 2))))
                .orElse(null);
    }
}
