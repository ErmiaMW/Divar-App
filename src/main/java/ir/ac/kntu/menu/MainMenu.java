package ir.ac.kntu.menu;

import ir.ac.kntu.*;
import ir.ac.kntu.menu.AdminMenu;
import ir.ac.kntu.menu.BuyerMenu;
import ir.ac.kntu.menu.GuestMenu;
import ir.ac.kntu.menu.SellerMenu;

/**
 * @author Ermiamirzaei
 * The MainMenu class represents the start menu .
 */
public class MainMenu {
    public void mainMenu(){
        System.out.println("\u001B[34m1.Sign in\n2.Sign up\n3.Guest\n4.Exit\nChoose an option :");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");
        if(option == 1){
            while (true) {
                System.out.println("\u001B[34mEnter Username :");
                String username = ScannerUtil.getInstance().nextLine();
                System.out.println("\u001B[34mEnter Password :");
                String password = ScannerUtil.getInstance().nextLine();
                System.out.println("\u001B[33m***************\u001B[34m");
                boolean isValidUser = Database.allUser.stream()
                        .anyMatch(user -> user.checkUsernameAndPassword(username, password));
                if (isValidUser) {
                    break;
                }
                System.out.println("\u001B[37mInvalid username or password. Try again...");
            }
        }
        if (option == 2){
            System.out.println("\u001B[34Sign up as :\n1.Admin\n2.Buyer\n3.Seller");
            option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
            if (option == 1){
                Admin newAdmin = new Admin(validateUsername(), validatePassword(), validateEmail(), validatePhoneNumber());
                Database.allAdmin.add(newAdmin);
                Database.allUser.add(newAdmin);
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.adminMenu(newAdmin);
            }
            if (option == 2){
                String username = validateUsername();
                String password = validatePassword();
                String email = validateEmail();
                String phoneNumber = validatePhoneNumber();
                System.out.println("\u001B[34mEnter X of location :");
                int xLocation = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                System.out.println("\u001B[34mEnter Y of location :");
                int yLocation = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                Buyer newBuyer = new Buyer(username, password, email, phoneNumber, xLocation, yLocation);
                System.out.println("\u001B[34mSign up seccesfully...");
                Database.allBuyer.add(newBuyer);
                Database.allUser.add(newBuyer);
                System.out.println("\u001B[33m***************\u001B[34m");
                BuyerMenu buyerMenu = new BuyerMenu();
                buyerMenu.buyerMenu(newBuyer);
            }
            if (option == 3){
                String username = validateUsername();
                String password = validatePassword();
                String email = validateEmail();
                String phoneNumber = validatePhoneNumber();
                System.out.println("\u001B[34mEnter X of location :");
                int xLocation = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                System.out.println("\u001B[34mEnter Y of location :");
                int yLocation = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                Seller newSeller = new Seller(username, password, email, phoneNumber, xLocation, yLocation);
                System.out.println("\u001B[34mSign up seccesfully...");
                Database.allSeller.add(newSeller);
                Database.allUser.add(newSeller);
                System.out.println("\u001B[33m***************\u001B[34m");
                SellerMenu sellerMenu = new SellerMenu();
                sellerMenu.sellerMenu(newSeller);
            }
        }
        if (option == 3){
            GuestMenu guestMenu = new GuestMenu();
            guestMenu.guestMenu();
            mainMenu();
        }
        if (option == 4){
            System.out.println("\u001B[31m*****  GOOD LUCK  *****");
            System.exit(0);
        }
    }

    private String validateUsername() {
        boolean isUnique = false;
        String username = "";
        while (!isUnique) {
            isUnique = true;
            System.out.println("\u001B[34mEnter a username:");
            String temp = ScannerUtil.getInstance().nextLine();
            if (temp == null || temp.length() < 6) {
                System.out.println("\u001B[37mThe username must be at least 6 characters. Try again...");
                isUnique = false;
            } else {
                boolean isDuplicate = Database.allUser.stream()
                        .anyMatch(user -> user.getUsername().equals(temp));
                if (isDuplicate) {
                    isUnique = false;
                }
            }
            username = temp;
        }
        return username;
    }

    private String validatePassword(){
        boolean isUnique = false;
        String password = "";
        while (!isUnique) {
            System.out.println("\u001B[34mEnter a password:");
            String temp = ScannerUtil.getInstance().nextLine();
            if(temp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")){
                isUnique = true;
            }else {
                System.out.println("\u001B[37mWrong password.Try again...");
                password = temp;
                isUnique = false;
            }
        }
        return password;
    }

    private String validateEmail(){
        boolean isUnique = false;
        String email = "";
        while (!isUnique){
            System.out.println("\u001B[34mEnter an email:");
            String temp = ScannerUtil.getInstance().nextLine();
            if(temp.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")){
                isUnique = true;
            }else {
                System.out.println("\u001B[37mWrong E-mail.Try again...");
                email = temp;
                isUnique = false;
            }
        }
        return email;
    }

    private String validatePhoneNumber(){
        boolean isUnique = false;
        String phoneNumber = "";
        while (!isUnique){
            System.out.println("\u001B[34mEnter a phone number:");
            String temp = ScannerUtil.getInstance().nextLine();
            if(temp.matches("^0\\d{10}$")){
                isUnique = true;
            }else {
                System.out.println("\u001B[37mWrong phone number.Try again...");
                phoneNumber = temp;
                isUnique = false;
            }
        }
        return phoneNumber;
    }
}
