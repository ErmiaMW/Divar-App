package ir.ac.kntu;

import ir.ac.kntu.menu.SellerMenu;

import java.util.ArrayList;

/**
 * @author Ermiamirzaei
 * The Seller class represents a user who sells products in the system.
 * A seller can create ads for their products, view and manage their ads, and
 * receive payment for sold products.
 */
public class Seller extends Users {
    private int xLocation;
    private int yLocation;
    private ArrayList<Ads> adsSold = new ArrayList<>();
    private ArrayList<Ads> adsAccepted = new ArrayList<>();
    private ArrayList<Ads> adsNotAccepted = new ArrayList<>();

    /**
     * Initializes a new Seller object with the given username, password,
     * email, phone number, x-location, and y-location.
     *
     * @param username     the seller's username
     * @param password     the seller's password
     * @param email        the seller's email
     * @param phoneNumber  the seller's phone number
     * @param xLocation    the seller's x-location
     * @param yLocation    the seller's y-location
     */

    public Seller(String username, String password, String email, String phoneNumber, int xLocation, int yLocation) {
        super(username, password, email, phoneNumber);
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    @Override
    public boolean checkUsernameAndPassword(String username, String password) {
        if(this.username.equals(username) && this.password.equals(password)){
            SellerMenu sellerMenu = new SellerMenu();
            sellerMenu.sellerMenu(this);
            return true;
        }
        return false;
    }

    /**
     * Creates a new ad for the given product with the given name, price, and kind.
     * Sets the ad status to "waiting to accept by admin" and adds the ad to the
     * seller's list of not-accepted ads and the system's list of all ads.
     *
     * @param productPrice the price of the product being advertised
     * @param productKind  the kind of the product being advertised
     */
    public void makeAd(String producktName, int productPrice, ProductKind productKind){
        Ads newAd = new Ads(producktName, this, productPrice, productKind, AdsStatus.WAITING_TO_ACCEPT_BY_ADMIN);
        adsNotAccepted.add(newAd);
        Database.allAds.add(newAd);
    }

    public void addAdToHistory(Ads ad){
        ad.setAdsStatus(AdsStatus.SOLD);
        adsAccepted.remove(ad);
        adsSold.add(ad);
    }

    public ArrayList<Ads> getAdsSold() {
        return adsSold;
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }
    public void setAdsAccepted(Ads ad) {
        this.adsAccepted.add(ad);
    }

    public void setAdsNotAccepted(Ads ad) {
        this.adsNotAccepted.add(ad);
    }

    public ArrayList<Ads> getAdsAccepted() {
        return adsAccepted;
    }

    public ArrayList<Ads> getAdsNotAccepted() {
        return adsNotAccepted;
    }

}
