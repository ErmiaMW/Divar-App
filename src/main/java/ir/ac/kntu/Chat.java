package ir.ac.kntu;

import java.util.ArrayList;

/**
 * @author Ermiamirzaei
 * The Chat class represents a chat between two users in the system.
 * It keeps track of the sender, receiver, and the messages exchanged in the chat.
 */
public class Chat {
    private Users sender;
    private Users receiver;
    private ArrayList<String> chats = new ArrayList<>();

    /**
     * Initializes a new Chat object with the given sender and receiver.
     *
     * @param sender   the user sending the messages in the chat
     * @param receiver the user receiving the messages in the chat
     */
    public Chat (Users sender, Users receiver){
        this.sender = sender;
        this.receiver = receiver;
    }

    public Users getSender() {
        return sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void addChat(String text){
        chats.add(text);
    }

    public ArrayList<String> getChats() {
        return chats;
    }
}
