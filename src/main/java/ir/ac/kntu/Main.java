package ir.ac.kntu;

import ir.ac.kntu.menu.MainMenu;

/**
 * @auther ErmiaMirzaei
 * It initialize the database with some sample data for testing purposes.
 * The main method then displays a welcome message, creates an instance of the MainMenu class,
 * and calls its mainMenu method to begin the application. / public class Main { /*
 */
public class Main {
    public static void main(String[] args) {
        // Adding sample data to the database
        MainAdmin mainAdmin = new MainAdmin("emi", "123", "emi@gmaial.com", "09123456789");
        Admin admin1 = new Admin("ali", "1234", "aliso@gmail.com", "09121212121");
        Admin admin2 = new Admin("poo", "12345", "poo@gmail.com", "09123123123");
        Database.allAdmin.add(mainAdmin);
        Database.allAdmin.add(admin1);
        Database.allAdmin.add(admin2);
        Seller seller1 = new Seller("ermiamm", "ermi123Erm", "emw@gmail.com", "09016901353", 0, 0);
        Seller seller2 = new Seller("alisoley", "eshgh123K", "ali@gmail.com", "09011011010", 1, 1);
        Database.allSeller.add(seller1);
        Database.allSeller.add(seller2);
        Buyer buyer1 = new Buyer("pooyaaa", "pooyA123", "poo@gmail.com", "09011111111", 0, 1);
        Buyer buyer2 = new Buyer("sajaddd", "sajAd123", "saj@gmail.com", "091112233", 1, 2);
        Database.allBuyer.add(buyer1);
        Database.allBuyer.add(buyer2);
        Database.allUser.add(mainAdmin);
        Database.allUser.add(admin1);
        Database.allUser.add(admin2);
        Database.allUser.add(seller1);
        Database.allUser.add(seller2);
        Database.allUser.add(buyer1);
        Database.allUser.add(buyer2);
        Database.allAds.add(new Ads("galaxy s10+", seller1, 12000, ProductKind.MOBILE_PHONE, AdsStatus.READY_TO_SALE));
        Database.allAds.add(new Ads("iphone 12", seller1, 1000, ProductKind.MOBILE_PHONE, AdsStatus.READY_TO_SALE));
        Database.allAds.add(new Ads("fer", seller1, 11000, ProductKind.HOME_APPLIANCES, AdsStatus.READY_TO_SALE));
        seller1.setAdsAccepted(Database.allAds.get(0));
        seller1.setAdsAccepted(Database.allAds.get(1));
        seller1.setAdsAccepted(Database.allAds.get(2));
        Database.allAds.add(new Ads("pejo", seller1, 1101400, ProductKind.AUTOMOBILE, AdsStatus.WAITING_TO_ACCEPT_BY_ADMIN));
        Database.allAds.add(new Ads("Rakhsh", seller1, 1101120, ProductKind.AUTOMOBILE, AdsStatus.WAITING_TO_ACCEPT_BY_ADMIN));
        seller1.setAdsNotAccepted(Database.allAds.get(3));
        seller1.setAdsNotAccepted(Database.allAds.get(4));
        Courier courier1 = new Courier("Iran peyk", 1, 1, CourierKind.MOTOR);
        Courier courier2 = new Courier("Iran peyk gostar", 2, 2, CourierKind.VAN);
        Database.allCourier.add(courier1);
        Database.allCourier.add(courier2);
        //Run
        System.out.println("\u001B[31m*****  WELLCOME  *****\n\n****************\u001B[0m");
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenu();
    }
}
