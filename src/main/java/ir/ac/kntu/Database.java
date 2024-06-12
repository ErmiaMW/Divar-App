package ir.ac.kntu;

import java.util.ArrayList;

/**
 * @author ErmiaMirzaei
 * Database class is responsible for storing data related to the system.
 * It maintains lists of users, admins, sellers, buyers, ads, and courier
 */
public class Database {
    public static ArrayList<Users> allUser= new ArrayList<>();
    public static ArrayList<Courier> allCourier = new ArrayList<>();
    public static ArrayList<Admin> allAdmin = new ArrayList<>();
    public static ArrayList<Seller> allSeller  = new ArrayList<>();
    public static ArrayList<Buyer> allBuyer  = new ArrayList<>();
    public static ArrayList<Ads> allAds = new ArrayList<>();

}
