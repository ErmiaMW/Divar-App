package ir.ac.kntu.menu;

import ir.ac.kntu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Ermiamirzaei
 * The AdminMenu class represents the menu options available to an admin user.
 * It extends the UsersMenu class.
 */
public class AdminMenu extends UsersMenu {
    public void adminMenu(Admin admin) {
        System.out.println("\u001B[34m1.Profile\n2.Seller\n3.Buyer\n4.All ads\n5.Requests\n6.Chat\n7.Back\nChoose an option:");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);
        System.out.println("\u001B[33m***************\u001B[34m");
        switch (option) {
            case 1:
                profileMenu(admin);
                break;
            case 2:
                sellerAdminMenu(admin);
                System.out.println("\u001B[33m***************\u001B[34m");
                adminMenu(admin);
                break;
            case 3:
                buyerAdminMenu(admin);
                System.out.println("\u001B[33m***************\u001B[34m");
                adminMenu(admin);
                break;
            case 4:
                allAdsAdminMenu(admin);
                System.out.println("\u001B[33m***************\u001B[34m");
                adminMenu(admin);
                break;
            case 5:
                requestAdminMenu();
                adminMenu(admin);
                break;
            case 6:
                chatMenu(admin);
                break;
            case 7:
                MainMenu mainMenu = new MainMenu();
                mainMenu.mainMenu();
                break;
            default:
        }
    }

    protected void requestAdminMenu(){
        System.out.println("\u001B[34mAll requests :");
        Database.allAds.stream()
                .filter(ad -> ad.getAdsStatus() == AdsStatus.WAITING_TO_ACCEPT_BY_ADMIN)
                .forEach(ad -> {
                    System.out.println( "*" + ad.getProductName() + " -- " + ad.getSeller().getUsername() + " -- " + ad.getProductKind() + " -- " + ad.getProductPrice());
                    System.out.println("\u001B[34Do you want to accept this ad ?\n1.YES\n2.NO");
                    String optoin1 = ScannerUtil.getInstance().nextLine();
                    int option = Integer.parseInt(optoin1);
                    if (option == 1) {
                        ad.setAdsStatus(AdsStatus.READY_TO_SALE);
                    } else if (option == 2) {

                    }
                    System.out.println("\u001B[33m***************\u001B[34m");
                });
    }

    protected void allAdsAdminMenu(Admin admin) {
        System.out.println("\u001B[34mAll ads : \u001B[0m");

        AtomicInteger j = new AtomicInteger(1);
        List<Ads> adsToDelete = new ArrayList<>();

        Database.allAds.stream()
                .filter(ad -> ad.getAdsStatus() == AdsStatus.READY_TO_SALE)
                .forEach(ad -> {
                    System.out.println(j.getAndIncrement() + "." + ad.getProductName() + " -- " + ad.getSeller().getUsername() + " -- " + ad.getProductKind() + " -- " + ad.getProductPrice());
                    displayComment(ad);
                    System.out.println("\u001B[34mDo you want to delete this ad ?\n1.YES\n2.NO");
                    String optoin1 = ScannerUtil.getInstance().nextLine();
                    int option = Integer.parseInt(optoin1);
                    if (option == 1) {
                        adsToDelete.add(ad);
                    } else if (option == 2) {

                    }
                    System.out.println("\u001B[33m***************\u001B[34m");
                });

        adsToDelete.forEach(admin::deleteAd);
    }

    private void displayComment(Ads ad) {
        if (ad.getComment().isEmpty()) {
            System.out.println("\u001B[37mNo comment about this advertisement.\u001B[0m");
        } else {
            ad.getComment().forEach((buyer, comment) -> {
                System.out.println(buyer.getUsername() + " : " + comment);
                System.out.println("\u001B[33m-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            });
        }
    }

    protected void buyerAdminMenu(Admin admin) {
        System.out.println("\u001B[34mBuyer : ");
        IntStream.range(0, Database.allBuyer.size())
                .forEach(i -> System.out.println((i + 1) + "." + Database.allBuyer.get(i).getUsername()));

        System.out.println("\u001B[34mDo you want to delete any buyer ?\n1.Yes\n2.No");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);

        if (option == 1) {
            System.out.println("\u001B[34mWhich buyer ?");
            int buyerToDelete = Integer.parseInt(ScannerUtil.getInstance().nextLine());
            admin.deleteBuyer(Database.allBuyer.get(buyerToDelete - 1));
        } else if (option == 2) {

        }
    }

    protected void sellerAdminMenu(Admin admin) {
        System.out.println("\u001B[34mSellers : ");
        IntStream.range(0, Database.allSeller.size())
                .forEach(i -> System.out.println((i + 1) + "." + Database.allSeller.get(i).getUsername()));

        System.out.println("\u001B[34mDo you want to delete any seller ?\n1.Yes\n2.No");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);

        if (option == 1) {
            System.out.println("\u001B[34mWhich seller ?");
            int sellerToDelete = Integer.parseInt(ScannerUtil.getInstance().nextLine());
            admin.deleteSeller(Database.allSeller.get(sellerToDelete - 1));
        } else if (option == 2) {

        }
    }
}
