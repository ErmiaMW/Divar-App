package ir.ac.kntu.menu;

import ir.ac.kntu.*;

/**
 * @author Ermiamirzaei
 * The SellerMenu class represents the menu options available to an seller user.
 * It extends the UsersMenu class.
 */
public class SellerMenu extends UsersMenu {
    public void sellerMenu(Seller seller){
        System.out.println("\u001B[34m1.Profile\n2.Available Ads\n3.History\n4.Add ad\n5.Chat\n6.Back\nChoose an option :");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);
        System.out.println("\u001B[33m***************\u001B[34m");
        switch(option){
            case 1:
                profileSellerMenu(seller);
                break;
            case 2:
                showAvailableAds(seller);
                System.out.println("\u001B[33m***************\u001B[34m");
                sellerMenu(seller);
                break;
            case 3:
                showHistory(seller);
                System.out.println("\u001B[33m***************\u001B[34m");
                sellerMenu(seller);
                break;
            case 4:
                System.out.println("\u001B[34mEnter the ad name :");
                String productName = ScannerUtil.getInstance().nextLine();
                System.out.println("\u001B[34mEnter the ad price :");
                int productPrice = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                System.out.println("\u001B[34mChoose the ad kind :\n1.MOBILE_PHONE\n2.HOME_APPLIANCES\n3.STATIONERY\n4.CLOTHING\n5.AUTOMOBILE");
                int productKind = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                seller.makeAd(productName, productPrice, ProductKind.reurnByValue(productKind));
                System.out.println("\u001B[33m***************\u001B[34m");
                sellerMenu(seller);
                break;
            case 5:
                chatMenu(seller);
                break;
            case 6:
                MainMenu mainMenu = new MainMenu();
                mainMenu.mainMenu();
                break;
            default:
        }
    }

    public void showAvailableAds(Seller seller) {
        System.out.println("\u001B[34mAvailable Ads:\n\u001B[33m---------------------------\u001B[34m");
        System.out.println("\u001B[34mAds are accepted by admin:");
        seller.getAdsAccepted().stream()
                .forEach(ad -> System.out.println(ad.getProductName() + " | " + ad.getProductKind() + " | " + ad.getProductPrice()));
        System.out.println("\u001B[34mAds are not accepted by admin:\u001B[34m");
        seller.getAdsNotAccepted().stream()
                .forEach(ad -> System.out.println(ad.getProductName() + " | " + ad.getProductKind() + " | " + ad.getProductPrice()));
        System.out.println("\u001B[33m---------------------------\u001B[34m");
    }

    private void showHistory(Seller seller) {
        if (seller.getAdsSold().isEmpty()) {
            System.out.println("\u001B[37mYou have not sold an advertisement yet.");
        } else {
            System.out.println("\u001B[34mAds you sold:");
            seller.getAdsSold().stream()
                    .forEach(ad -> {
                        System.out.println("Product Name: " + ad.getProductName());
                        System.out.println("Category: " + ad.getProductKind());
                        System.out.println("Price: " + ad.getProductPrice());
                        System.out.println("---------------------------");
                    });
        }
    }

    private void profileSellerMenu(Seller seller){
        System.out.println("\u001B[34m1.Profile information\n2.Change information\n3.Withdraw from wallet\n4.Back\nChoose an option :");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);
        System.out.println("\u001B[33m***************\u001B[34m");
        switch (option){
            case 1:
                showProfile(seller);
                System.out.println("\u001B[33m***************\u001B[34m");
                profileSellerMenu(seller);
                break;
            case 2:
                changeInformation(seller);
                System.out.println("\u001B[33m***************\u001B[34m");
                profileSellerMenu(seller);
                break;
            case 3:
                System.out.println("\u001B[34mEnter the amount you want to withdraw from your wallet: ");
                int wallet = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                seller.decreaseWallet(wallet);
                System.out.println("\u001B[34mYou withdraw " + wallet + " from your wallet.");
                System.out.println("\u001B[33m***************\u001B[34m");
                profileSellerMenu(seller);
                break;
            case 4:
                sellerMenu(seller);
                break;
            default:
        }
    }
}
