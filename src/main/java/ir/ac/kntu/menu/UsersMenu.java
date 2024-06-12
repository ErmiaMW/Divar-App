package ir.ac.kntu.menu;

import ir.ac.kntu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UsersMenu {
    public void profileMenu(Users user){
        System.out.println("\u001B[34m1.Profile information\n2.Change information\n3.Charge wallet\n4.Back\nChoose an option :");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");
        switch (option){
            case 1:
                showProfile(user);
                System.out.println("\u001B[33m***************\u001B[34m");
                profileMenu(user);
                break;
            case 2:
                changeInformation(user);
                System.out.println("\u001B[33m***************\u001B[34m");
                profileMenu(user);
                break;
            case 3:
                System.out.println("\u001B[34mEnter the amount you want to charge your wallet: ");
                int wallet = Integer.parseInt(ScannerUtil.getInstance().nextLine());
                user.increaseWallet(wallet);
                System.out.println("\u001B[33m***************\u001B[34m");
                profileMenu(user);
                break;
            case 4:
                callUsersSpeceficMenu(user);
                break;
            default:
        }
    }
    protected void changeInformation(Users user){
        System.out.println("\u001B[34m1.Change Username\n2.Change Passsword\n3.Change E-mail\n4.Change phonenumber\nChoose an option :");
        String option1 = ScannerUtil.getInstance().nextLine();
        int option = Integer.parseInt(option1);
        switch (option){
            case 1:
                changeUserName(user);
                break;
            case 2:
                changePassword(user);
                break;
            case 3:
                changeEmail(user);
                break;
            case 4:
                changePhonenuber(user);
                break;
            default:
        }
    }

    private void changeUserName(Users user) {
        boolean isUnique = false;
        while (!isUnique) {
            isUnique = true;
            System.out.println("\u001B[34mEnter new username:");
            String newUsername = ScannerUtil.getInstance().nextLine();
            if (newUsername == null || newUsername.length() < 6) {
                System.out.println("\u001B[37mThe username must be at least 6 character.");
                isUnique = false;
            }
            if (Database.allSeller.stream().anyMatch(seller -> seller.getUsername().equals(newUsername))) {
                isUnique = false;
                System.out.println("\u001B[37mThis username already exists.");
            }
            user.setUsername(newUsername);
        }
    }

    private void changePassword(Users user){
        boolean isUnique = false;
        while (!isUnique) {
            System.out.println("\u001B[34mEnter new password:");
            String newPassword = ScannerUtil.getInstance().nextLine();
            if(newPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")){
                isUnique = true;
            }else {
                System.out.println("\u001B[37mWrong password.Try again...");
                isUnique = false;
            }
            user.setPassword(newPassword);
        }
    }

    private void changeEmail(Users user){
        boolean isUnique = false;
        while (!isUnique){
            System.out.println("\u001B[34mEnter an email:");
            String newEmail = ScannerUtil.getInstance().nextLine();
            if(newEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")){
                isUnique = true;
            }else {
                System.out.println("\u001B[37mWrong E-mail.Try again...");
                isUnique = false;
            }
            user.setEmail(newEmail);
        }
    }

    private void changePhonenuber(Users user){
        boolean isUnique = false;
        while (!isUnique){
            System.out.println("\u001B[34mEnter new phone number:");
            String newPhonenumber = ScannerUtil.getInstance().nextLine();
            if(newPhonenumber.matches("^0\\d{10}$")){
                isUnique = true;
            }else {
                System.out.println("\u001B[37mWrong phone number.Try again...");
                isUnique = false;
            }
            user.setPhoneNumber(newPhonenumber);
        }
    }

    protected void showProfile(Users user){
        System.out.println("\u001B[34mInformation:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("Wallet: " + user.getWallet());
    }

    private void callUsersSpeceficMenu(Users user){
        if (user instanceof Buyer){
          Buyer buyer = (Buyer) user;
          BuyerMenu buyerMenu = new BuyerMenu();
          buyerMenu.buyerMenu(buyer);
        }
        if (user instanceof Seller){
            Seller seller = (Seller) user;
            SellerMenu sellerMenu = new SellerMenu();
            sellerMenu.sellerMenu(seller);
        }
        if (user instanceof Admin){
            Admin admin = (Admin) user;
            AdminMenu adminMenu = new AdminMenu();
            adminMenu.adminMenu(admin);
        }
        if (user instanceof MainAdmin){
            MainAdmin mainAdmin = (MainAdmin) user;
            MainAdminMenu mainAdminMenu = new MainAdminMenu();
            mainAdminMenu.mainAdminMenu(mainAdmin);
        }
    }

    public void chatMenu(Users user){
        System.out.println("\u001B[34m1.Older chats\n2.New chats\n3.Back\nchoose an option :");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");
        if (option == 1){
            olderChat(user);
        }
        if (option == 2){
            newChat(user);
            chatMenu(user);
        }
        if (option == 3){
            callUsersSpeceficMenu(user);
        }
    }

    private void olderChat(Users user) {
        ArrayList<Chat> olderChats = user.getOlderChats();
        if (olderChats.isEmpty()) {
            System.out.println("\u001B[37mYou have no chats with others.");
            System.out.println("\u001B[33m***************\u001B[34m");
            chatMenu(user);
        } else {
            IntStream.range(0, olderChats.size())
                    .forEach(i -> {
                        Chat chat = olderChats.get(i);
                        String username = chat.getSender().equals(user) ? chat.getReceiver().getUsername() : chat.getSender().getUsername();
                        System.out.println((i + 1) + "." + username);
                    });
            System.out.println("\u001B[34mChoose a user to see chat :");
            int option = Integer.parseInt(ScannerUtil.getInstance().nextLine()) - 1;
            System.out.println("\u001B[33m***************\u001B[34m");
            Chat selectedChat = olderChats.get(option);
            boolean isUserSender = selectedChat.getSender().equals(user);
            IntStream.range(0, selectedChat.getChats().size())
                    .forEach(j -> {
                        String text = selectedChat.getChats().get(j);
                        String senderUsername = isUserSender ? "You" : selectedChat.getSender().getUsername();
                        String receiverUsername = isUserSender ? selectedChat.getReceiver().getUsername() : "You";
                        System.out.println((j % 2 == 0 ? "\u001B[34m" + senderUsername : receiverUsername)
                                + " : " + text);
                    });
            System.out.println("\u001B[33m***************\u001B[34m");
            System.out.println("\u001B[34mDo you want to send text :\n1.Yes\n2.No");
            int answer = Integer.parseInt(ScannerUtil.getInstance().nextLine());
            System.out.println("\u001B[33m***************\u001B[34m");
            if (answer == 1) {
                System.out.println("\u001B[34mEnter the text you want to send :");
                String text = ScannerUtil.getInstance().nextLine();
                System.out.println("\u001B[33m***************\u001B[34m");
                selectedChat.addChat(text);
                chatMenu(user);
            } else if (answer == 2) {
                chatMenu(user);
            }
        }
    }

    private void newChat(Users user) {
        System.out.println("\u001B[34m1.Show all user\n2.Search by username\nChoose an option:");
        int option = Integer.parseInt(ScannerUtil.getInstance().nextLine());
        System.out.println("\u001B[33m***************\u001B[34m");

        List<Users> foundedUsers = Database.allUser.stream()
                .filter(u -> !u.equals(user) && !isInOldChats(user, u))
                .filter(u -> option == 1 || u.getUsername().contains(ScannerUtil.getInstance().nextLine()))
                .collect(Collectors.toList());

        IntStream.range(0, foundedUsers.size())
                .forEach(i -> System.out.println((i + 1) + "." + foundedUsers.get(i).getUsername()));

        System.out.println("\u001B[34mChoose a user to chat:");
        int receiver = Integer.parseInt(ScannerUtil.getInstance().nextLine()) - 1;
        System.out.println("\u001B[33m***************\u001B[34m");

        chat(user, foundedUsers.get(receiver));
    }

    private boolean isInOldChats(Users user, Users userToSearch) {
        return userToSearch.getOlderChats().stream()
                .anyMatch(chat -> chat.getReceiver().equals(user) || chat.getSender().equals(user));
    }

    private void chat(Users sender, Users receiver){
        Chat chat = new Chat(sender, receiver);
        System.out.println("\u001B[34mEnter the text you want to send :");
        String text = ScannerUtil.getInstance().nextLine();
        System.out.println("\u001B[33m***************\u001B[34m");
        chat.addChat(text);
        sender.addToOlderChats(chat);
        receiver.addToOlderChats(chat);
    }
}
