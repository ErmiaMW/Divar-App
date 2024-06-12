package ir.ac.kntu;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Ermiamirzaei
 * The Courier class represents a courier in the system.
 * A courier can deliver ads and receive payment for their services.
 */
public class Courier {
    private String username;
    private int xLocation;
    private int yLocation;
    private int wallet = 0;
    private boolean availability = true;
    private CourierKind courierKind;

    /**
     * Initializes a new Courier object with the given username, x-location,
     * y-location, and courier kind.
     *
     * @param username     the courier's username
     * @param xLocation    the courier's x-location
     * @param yLocation    the courier's y-location
     * @param courierKind  the courier's kind (regular or premium)
     */
    public Courier(String username, int xLocation, int yLocation, CourierKind courierKind) {
        this.username = username;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.courierKind = courierKind;
    }
    /**
     * Makes the courier unavailable for the given number of minutes and sets the
     * ad status to "in delivery".
     *
     * @param minute the number of minutes to make the courier unavailable
     * @param ad     the ad being delivered by the courier
     */
    public void makeUnavailable(int minute, Ads ad){
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                availability = false;
                ad.setAdsStatus(AdsStatus.IN_DELIVERI);
            }
        };
        timer.scheduleAtFixedRate(task, 0, minute * 60 * 1000);
    }

    public boolean isAvailability() {
        return availability;
    }

    public int getXLocation() {
        return xLocation;
    }

    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public CourierKind getCourierKind() {
        return courierKind;
    }

    public void increaseWallet(int wallet){
        this.wallet += wallet;
    }

    public String getUsername() {
        return username;
    }
}
