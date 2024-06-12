package ir.ac.kntu;

import ir.ac.kntu.menu.MainAdminMenu;

public class MainAdmin extends Admin{
    /**
     * Constructs a new MainAdmin object with the specified username, password, email, and phone number.
     *
     * @param username The username for the new MainAdmin object.
     * @param password The password for the new MainAdmin object.
     * @param email The email address for the new MainAdmin object.
     * @param phoneNumber The phone number for the new MainAdmin object.
     */
    public MainAdmin(String username, String password, String email, String phoneNumber) {
        super(username, password, email, phoneNumber);
    }

    @Override
    public boolean checkUsernameAndPassword(String username, String password) {
        if(this.username.equals(username) && this.password.equals(password)){
            MainAdminMenu mainAdminMenu = new MainAdminMenu();
            mainAdminMenu.mainAdminMenu(this);
            return true;
        }
        return false;
    }
}
