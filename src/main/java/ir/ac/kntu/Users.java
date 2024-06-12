package ir.ac.kntu;

import java.util.ArrayList;

public class Users {
    protected String username;
    protected String password;
    private String email;
    private String phoneNumber;
    private int wallet = 0;
    private ArrayList<Chat> olderChats = new ArrayList<>();

    /**
     * Constructs a new User object with the specified username, password, email, and phone number.
     *
     * @param username The username of the new User object.
     * @param password The password of the new User object.
     * @param email The email address of the new User object.
     * @param phoneNumber The phone number of the new User object.
     */
    public Users(String username, String password, String email, String phoneNumber){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean checkUsernameAndPassword(String username, String password){
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public int getWallet() {
        return wallet;
    }

    public void increaseWallet(int wallet){
        this.wallet += wallet;
    }

    public void decreaseWallet(int wallet){
        this.wallet -= wallet;
    }

    public void addToOlderChats(Chat chat){
        olderChats.add(chat);
    }

    public ArrayList<Chat> getOlderChats() {
        return olderChats;
    }
}
