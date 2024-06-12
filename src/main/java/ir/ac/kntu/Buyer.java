package ir.ac.kntu;

import ir.ac.kntu.menu.BuyerMenu;

import java.util.ArrayList;

/**
 * @author Ermiamirzaei
 * The Buyer class represents a buyer user in the system.
 * A buyer can buy ads, add ads to their saved box, and view their purchase history.
 */
public class Buyer extends Users{
    /**
     * Initializes a new Buyer object with the given username, password,
     * email, phone number, x-location, and y-location.
     *
     * @param username   the buyer's username
     * @param password   the buyer's password
     * @param email      the buyer's email address
     * @param phoneNumber the buyer's phone number
     * @param xLocation  the buyer's x-location
     * @param yLocation  the buyer's y-location
     */
    private int xLocation;
    private int yLocation;
    private ArrayList<Ads> savedBox = new ArrayList<>();
    private ArrayList<Ads> boughtAds = new ArrayList<>(); //History

    public Buyer(String username, String password, String email, String phoneNumber, int xLocation, int yLocation) {
        super(username, password, email, phoneNumber);
        this.xLocation = xLocation;
        this.yLocation = yLocation;

    }

    @Override
    public boolean checkUsernameAndPassword(String username, String password) {
        if(this.username.equals(username) && this.password.equals(password)){
            BuyerMenu buyerMenu = new BuyerMenu();
            buyerMenu.buyerMenu(this);
            return true;
        }
        return false;
    }

    /**
     * Buys the given ad, adds it to the buyer's purchase history,
     * updates the seller's wallet, and updates the admin's wallet.
     *
     * @param ad the ad to buy
     */
    public void buyAnAd(Ads ad){
        boughtAds.add(ad);
        ad.getSeller().addAdToHistory(ad);
        this.decreaseWallet(ad.getProductPrice());
        ad.getSeller().increaseWallet(ad.getProductPrice() * 9 / 10);
        Database.allAdmin.get(0).increaseWallet(ad.getProductPrice() / 10);
        System.out.println("\u001B[37mThe ad bought succesfully.");
    }

    /**
     * Adds the given ad to the buyer's saved box.
     * If the ad is already in the saved box, it is not added again.
     *
     * @param ad the ad to add to the saved box
     */
    public void addToSavedBox(Ads ad){
        if (!savedBox.contains(ad)) {
            savedBox.add(ad);
        }
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public ArrayList<Ads> getSavedBox() {
        return savedBox;
    }

    public ArrayList<Ads> getBoughtAds() {
        return boughtAds;
    }

}


