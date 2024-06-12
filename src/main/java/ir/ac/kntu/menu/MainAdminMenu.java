package ir.ac.kntu.menu;

import ir.ac.kntu.*;

/**
 * @author Ermiamirzaei
 * The MainAdminMenu class represents the menu options available to an main admin user.
 * It extends the AdminMenu class.
 */
public class MainAdminMenu extends AdminMenu {
    public void mainAdminMenu(MainAdmin mainAdmin){
        System.out.println("\u001B[34m1.Profile\n2.Seller\n3.Buyer\n4.All ads\n5.Requests\n6.Pay salary\n7.Chat\n8.Back\nChoose an option:");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);
        System.out.println("\u001B[33m***************\u001B[34m");
        switch (option){
            case 1:
                profileMenu(mainAdmin);
                break;
            case 2:
                sellerAdminMenu(mainAdmin);
                System.out.println("\u001B[33m***************\u001B[34m");
                mainAdminMenu(mainAdmin);
                break;
            case 3:
                buyerAdminMenu(mainAdmin);
                System.out.println("\u001B[33m***************\u001B[34m");
                mainAdminMenu(mainAdmin);
                break;
            case 4:
                allAdsAdminMenu(mainAdmin);
                System.out.println("\u001B[33m***************\u001B[34m");
                mainAdminMenu(mainAdmin);
                break;
            case 5:
                requestAdminMenu();
                System.out.println("\u001B[33m***************\u001B[34m");
                mainAdminMenu(mainAdmin);
                break;
            case 6:
                paySalaryMainAdminMenu(mainAdmin);
                mainAdminMenu(mainAdmin);
                break;
            case 7:
                chatMenu(mainAdmin);
                break;
            case 8:
                MainMenu mainMenu = new MainMenu();
                mainMenu.mainMenu();
                break;
            default:
        }
    }

    private void paySalaryMainAdminMenu(MainAdmin mainAdmin){
        System.out.println("\u001B[34mPay salary to :\n1.Admins\n2.Couriers");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");
        if (option == 1){
            paySalaryToAdmins(mainAdmin);
        }
        if (option == 2) {
            paySalaryToCouriers(mainAdmin);
        }
    }

    private void paySalaryToAdmins(MainAdmin mainAdmin) {
        System.out.println("\u001B[334mHow much do you want to pay ?");
        int salary = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");
        System.out.println("\u001B[34mFor all admins ?\n1.Yes\n2.No");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");
        if (option == 1) {
            long totalSalary = salary * (Database.allAdmin.size() - 1);
            if (totalSalary > mainAdmin.getWallet()) {
                System.out.println("\u001B[34mYou don't have enough money to pay...");
                System.out.println("\u001B[33m***************\u001B[34m");
            } else {
                Database.allAdmin.stream()
                        .skip(1)
                        .forEach(admin -> {
                            mainAdmin.decreaseWallet(salary);
                            admin.increaseWallet(salary);
                        });
                System.out.println("\u001B[34mAll admins receive their salary.");
            }
        }
        if (option == 2) {
            for (int i = 1; i < Database.allAdmin.size(); i++) {
                System.out.println("\u001B[34mPay salary to " + Database.allAdmin.get(i).getUsername() + " ?\n1.Yes\n2.No");
                option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                System.out.println("\u001B[33m***************\u001B[34m");
                if (option == 1) {
                    if (salary > mainAdmin.getWallet()) {
                        System.out.println("\u001B[37mYou don't have enough money to pay...");
                        System.out.println("\u001B[33m***************\u001B[34m");
                        break;
                    } else {
                        mainAdmin.decreaseWallet(salary);
                        Database.allAdmin.get(i).increaseWallet(salary);
                    }
                }
            }
        }
    }

    private void paySalaryToCouriers(MainAdmin mainAdmin) {
        System.out.println("\u001B[34mHow much do you want to pay ?");
        int salary = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");
        System.out.println("\u001B[34mFor all couriers ?\n1.Yes\n2.No");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");

        if (option == 1) {
            long totalSalary = salary * Database.allCourier.size();
            if (totalSalary > mainAdmin.getWallet()) {
                System.out.println("\u001B[37mYou don't have enough money to pay...");
                System.out.println("\u001B[33m***************\u001B[34m");
            } else {
                Database.allCourier.forEach(courier -> {
                    mainAdmin.decreaseWallet(salary);
                    courier.increaseWallet(salary);
                });
                System.out.println("\u001B[34mAll couriers receive their salary.");
            }
        }

        if (option == 2) {
            for (int i = 0; i < Database.allCourier.size(); i++) {
                System.out.println("\u001B[34mPay salary to " + Database.allCourier.get(i).getUsername() + " ?\n1.Yes\n2.No");
                option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                System.out.println("\u001B[33m***************\u001B[34m");
                if (option == 1) {
                    if (salary > mainAdmin.getWallet()) {
                        System.out.println("\u001B[34mYou don't have enough money to pay...");
                        System.out.println("\u001B[33m***************\u001B[34m");
                        break;
                    } else {
                        mainAdmin.decreaseWallet(salary);
                        Database.allCourier.get(i).increaseWallet(salary);
                    }
                }
            }
        }
    }


    @Override
    protected void sellerAdminMenu(Admin admin) {
        super.sellerAdminMenu(admin);
    }

    @Override
    protected void buyerAdminMenu(Admin admin) {
        super.buyerAdminMenu(admin);
    }

    @Override
    protected void allAdsAdminMenu(Admin admin) {
        super.allAdsAdminMenu(admin);
    }

    @Override
    protected void requestAdminMenu() {
        super.requestAdminMenu();
    }
}
