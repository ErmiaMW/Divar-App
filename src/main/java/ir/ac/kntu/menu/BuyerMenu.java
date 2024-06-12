package ir.ac.kntu.menu;

import ir.ac.kntu.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Ermiamirzaei
 * The BuyerMenu class represents the menu options available to an buyer user.
 * It extends the UsersMenu class.
 */
public class BuyerMenu extends UsersMenu {
    public void buyerMenu(Buyer buyer){
        System.out.println("\u001B[34m1.Profile\n2.Saved Box\n3.History\n4.Sales Ad\n5.Search\n6.Chat\n7.Back\nChoose an option :");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);
        System.out.println("\u001B[33m***************\u001B[34m");
        switch(option){
            case 1:
                profileMenu(buyer);
                break;
            case 2:
                savedboxBuyerMenu(buyer);
                break;
            case 3:
                showHistiry(buyer);
                System.out.println("\u001B[33m***************\u001B[34m");
                buyerMenu(buyer);
                break;
            case 4:
                salesAdBuyerMenu(buyer);
                buyerMenu(buyer);
                break;
            case 5:
                searchByKeyword();
                System.out.println("\u001B[33m***************\u001B[34m");
                buyerMenu(buyer);
                break;
            case 6:
                chatMenu(buyer);
                break;
            case 7:
                MainMenu mainMenu = new MainMenu();
                mainMenu.mainMenu();
                break;
            default:
        }
    }

    private void showSavedBox(Buyer buyer){
        if(buyer.getSavedBox().isEmpty()) {
            System.out.println("\u001B[37mNo ads saved in the box.");
        } else {
            printAdsInBox(buyer);
        }
    }

    private void deleteAdFromSavedBox(Buyer buyer){
        printAdsInBox(buyer);
        System.out.println("Enter the ad you want to delete:");
        int j = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        if(buyer.getSavedBox().contains(buyer.getSavedBox().get(j-1))){
            buyer.getSavedBox().remove(buyer.getSavedBox().get(j-1));
        }
    }

    private void printAdsInBox(Buyer buyer) {
        System.out.println("\u001B[34mAds saved in the box:\u001B[0m");
        IntStream.range(0, buyer.getSavedBox().size())
                .mapToObj(i -> buyer.getBoughtAds().get(i))
                .forEach(ad -> System.out.println((buyer.getSavedBox().indexOf(ad) + 1) + ". Product Name: " + ad.getProductName() +
                        ", Category: " + ad.getProductKind() +
                        ", Seller Name: " + ad.getSeller().getUsername() +
                        ", Price: " + ad.getProductPrice()));
    }

    private void showHistiry(Buyer buyer) {
        if (buyer.getBoughtAds().isEmpty()) {
            System.out.println("\u001B[37mYou have not bought an advertisement yet.");
        } else {
            System.out.println("\u001B[34mAds you bought:\u001B[0m");
            buyer.getBoughtAds().stream()
                    .forEach(ad -> {
                        System.out.println("Product Name: " + ad.getProductName());
                        System.out.println("Category: " + ad.getProductKind());
                        System.out.println("Seller Name: " + ad.getSeller().getUsername());
                        System.out.println("Price: " + ad.getProductPrice());
                        System.out.println("Status: " + ad.getAdsStatus());
                        System.out.println("\u001B[33m---------------------------\u001B[0m");
                    });
        }
    }

    private void searchByKeyword() {
        List<Ads> foundedAds = new ArrayList<>();
        System.out.println("\u001B[34m1. Search seller name\n2. Search product name\nChoose an option:");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");

        if (option == 1) {
            System.out.println("\u001B[34mEnter the seller name:");
            String keyword = ScannerUtil.getInstance().nextLine();
            System.out.println("\u001B[33m***************\u001B[34m");
            foundedAds = Database.allAds.stream()
                    .filter(ad -> ad.getAdsStatus() == AdsStatus.READY_TO_SALE)
                    .filter(ad -> ad.getSeller().getUsername().contains(keyword))
                    .collect(Collectors.toList());
        } else if (option == 2) {
            System.out.println("\u001B[34mEnter the product name:");
            String keyword = ScannerUtil.getInstance().nextLine();
            System.out.println("\u001B[33m***************\u001B[34m");
            foundedAds = Database.allAds.stream()
                    .filter(ad -> ad.getAdsStatus() == AdsStatus.READY_TO_SALE)
                    .filter(ad -> ad.getProductName().contains(keyword))
                    .collect(Collectors.toList());
        }

        List<Ads> finalFoundedAds = foundedAds;
        IntStream.range(0, foundedAds.size())
                .forEach(i -> {
                    System.out.println((i + 1) + ". Name: " + finalFoundedAds.get(i).getProductName() +
                            " -- Seller: " + finalFoundedAds.get(i).getSeller().getUsername() +
                            " -- Price: " + finalFoundedAds.get(i).getProductPrice());
                });
    }


    private int getCategoryOption() {
        System.out.println("\u001B[34mChoose a category :\n1.MOBILE_PHONE\n2.HOME_APPLIANCES\n3.STATIONERY\n4.CLOTHING\n5.AUTOMOBILE");
        String option = ScannerUtil.getInstance().nextLine();
        return Integer.parseInt(option);
    }

    private List<Ads> getPriceFilter() {
        System.out.println("\u001B[34mFilter for price?\n1. Yes\n2. No");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33***************\u001B[34m");

        List<Ads> priceFilter = Database.allAds.stream()
                .filter(ad -> ad.getAdsStatus() == AdsStatus.READY_TO_SALE)
                .filter(ad -> option == 2 || (ad.getProductPrice() < getMaxPrice() && ad.getProductPrice() > getMinPrice()))
                .collect(Collectors.toList());

        return priceFilter;
    }

    private int getMaxPrice() {
        System.out.println("\u001B[34mMax price:");
        return Integer.parseInt(ScannerUtil.getInstance().nextLine());
    }

    private int getMinPrice() {
        System.out.println("\u001B[34mMin price:");
        return Integer.parseInt(ScannerUtil.getInstance().nextLine());
    }

    private int getSortOption() {
        System.out.println("\u001B[34mDo you want to sort ads by price ?\n1.Yes.Ascending\n2.Yes.Descending\n3.No");
        String option = ScannerUtil.getInstance().nextLine();
        System.out.println("\u001B[33m***************\u001B[34m");
        return Integer.parseInt(option);
    }

    private void salesAdBuyerMenu(Buyer buyer) {
        List<Ads> ads = new ArrayList<>();
        System.out.println("\u001B[34mDo you want to filter ?\n1.Yes\n2.No");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");

        if (option == 1) {
            ads = getAdsByCategoryAndPriceFilter();
        }

        if (option == 2) {
            ads = Database.allAds.stream()
                    .filter(ad -> ad.getAdsStatus() == AdsStatus.READY_TO_SALE)
                    .collect(Collectors.toList());
        }

        if (ads.isEmpty()) {
            System.out.println("\u001B[37mThere is no ads by this filtering.");
        } else {
            int sortOption = getSortOption();
            System.out.println("\u001B[34mAds :");

            switch (sortOption) {
                case 1:
                    ads.sort(Comparator.comparingInt(Ads::getProductPrice));
                    break;
                case 2:
                    ads.sort(Comparator.comparingInt(Ads::getProductPrice).reversed());
                    break;
                case 3:
                    break;
                default:
                    break;
            }

            List<Ads> finalAds = ads;
            IntStream.range(0, ads.size())
                    .forEach(z -> System.out.println((z + 1) + ".Name: " + finalAds.get(z).getProductName() + " -- Seller: "
                            + finalAds.get(z).getSeller().getUsername() + " -- Price: " + finalAds.get(z).getProductPrice()));

            System.out.println("\u001B[33m***************\u001B[34m");
            System.out.println("\u001B[34mChoose an ad :");
            String option11 = ScannerUtil.getInstance().nextLine();
            int choosedAd = Integer.parseInt(option11);
            System.out.println("\u001B[33m***************\u001B[34m");

            commentMenu(ads.get(choosedAd - 1), buyer); // for comment

            System.out.println("\u001B[34m1.Buy\n2.Add to savedbox");
            int opt = Integer.parseInt(ScannerUtil.getInstance().nextLine());
            System.out.println("\u001B[33m***************\u001B[34m");

            switch (opt) {
                case 1:
                    if (ads.get(choosedAd - 1).getProductPrice() > buyer.getWallet()) {
                        System.out.println("\u001B[37myou do not have enough money...");
                    } else {
                        buyer.buyAnAd(ads.get(choosedAd - 1));
                        wantDelivery(buyer, ads.get(choosedAd - 1));
                    }
                    System.out.println("\u001B[33m***************\u001B[34m");
                    break;
                case 2:
                    buyer.addToSavedBox(ads.get(choosedAd - 1));
                    System.out.println("\u001B[33m***************\u001B[34m");
                    buyerMenu(buyer);
                    break;
                default:
                    break;
            }
        }
    }



    private void commentMenu(Ads ad, Buyer buyer){
        System.out.println("\u001B[34m1.Add comment\n2.See comments\n3.Continue\nChoose an option :");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");
        if (option == 1){
            System.out.println("\u001B[34mWrite your comment :");
            String comment = ScannerUtil.getInstance().nextLine();
            ad.addComment(buyer, comment);
        }
        if (option == 2){
            displayComment(ad);
        }
        if (option == 3){

        }
    }

    private void displayComment(Ads ad) {
        Map<Buyer, String> comments = ad.getComment();
        if (comments.keySet().isEmpty()) {
            System.out.println("\u001B[37mNo comment about this advertisement.\u001B[0m");
        } else {
            comments.keySet().stream()
                    .forEach(buyer -> {
                        System.out.println(buyer.getUsername() + " : " + comments.get(buyer));
                        System.out.println("\u001B[33m-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
                    });
        }
    }


    private void wantDelivery(Buyer buyer, Ads ad){
        System.out.println("D\u001B[34mo you want delivery ?\n1.Yes\n2.No");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        if (option == 1){
            if(ad.getProductKind() == ProductKind.AUTOMOBILE){
                System.out.println("\u001B[37mThere is no delivery for automibile.");
                ad.setAdsStatus(AdsStatus.SOLD);
            }else {
                Order order = new Order(ad, buyer);
                order.deliverOrder();
            }
        }
        if (option == 2){
            ad.setAdsStatus(AdsStatus.SOLD);
        }
    }

    private List<Ads> getAdsByCategoryAndPriceFilter() {
        List<Ads> priceAndCategoryFilter = new ArrayList<>();
        List<Ads> priceFilter = getPriceFilter();
        System.out.println("\u001B[34mDo you want to filter by category ?\n1.Yes\n2.No");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************");
        if (option == 1) {
            int category = getCategoryOption();
            System.out.println("\u001B[33m***************\u001B[34m");
            priceAndCategoryFilter = priceFilter.stream()
                    .filter(ad -> ad.getAdsStatus() == AdsStatus.READY_TO_SALE)
                    .filter(ad -> ad.getProductKind().getValue() == category)
                    .collect(Collectors.toList());
        }
        if (option == 2) {
            priceAndCategoryFilter = priceFilter;
        }
        return priceAndCategoryFilter;
    }

    private void savedboxBuyerMenu(Buyer buyer){
        System.out.println("\u001B[34m1.Show Savedbox\n2.Delete Ads from savedbox\n3.Buy Ads from savedbox\n4.Back\nChoose an option");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);
        switch (option){
            case 1:
                showSavedBox(buyer);
                System.out.println("\u001B[33m***************\u001B[34m");
                savedboxBuyerMenu(buyer);
                break;
            case 2:
                deleteAdFromSavedBox(buyer);
                System.out.println("\u001B[33m***************\u001B[34m");
                savedboxBuyerMenu(buyer);
                break;
            case 3:
                buyFromSavedBox(buyer);
                System.out.println("\u001B[33m***************\u001B[34m");
                savedboxBuyerMenu(buyer);
                break;
            case 4:
                buyerMenu(buyer);
                break;
            default:
        }
    }

    private void buyFromSavedBox(Buyer buyer){
        boolean toContinue = true;
        if(buyer.getSavedBox().isEmpty()) {
            System.out.println("\u001B[37mNo ads saved in the box.");
        } else {
            printAdsInBox(buyer);
            while (toContinue) {
                System.out.print("\u001B[34mEnter the number of the ad you want to buy: ");
                int selectedAdIndex = Integer.parseInt(ScannerUtil.getInstance().nextLine());

                if (selectedAdIndex < 0 || selectedAdIndex > buyer.getSavedBox().size()) {
                    System.out.println("\u001B[37mInvalid selection. Please try again.");
                } else {
                    if (buyer.getSavedBox().get(selectedAdIndex-1).getProductPrice() > buyer.getWallet()) {
                        System.out.println("\u001B[37mInsufficient funds. Please add more money to your wallet.");
                    }else {
                        if(buyer.getSavedBox().get(selectedAdIndex - 1).getProductPrice() > buyer.getWallet()){
                            System.out.println("\u001B[37myou do not have enough money...");
                        }else {
                            buyer.buyAnAd(buyer.getSavedBox().get(selectedAdIndex - 1));
                            wantDelivery(buyer, buyer.getSavedBox().get(selectedAdIndex - 1));
                            System.out.println("\u001B[37mAd successfully bought and removed from the saved box.");
                        }
                    }
                    toContinue = false;
                }
            }
        }
    }
}
