package ir.ac.kntu;

import java.util.Random;

/**
 * The Order class represents an order made by a buyer for a specific ad.
 * It contains references to the ad, buyer, and courier related to the order,
 * as well as methods for delivering the order and finding the nearest available courier.
 */
public class Order {
    Ads ad;
    Buyer buyer;
    Courier courier;

    public Order(Ads ad, Buyer buyer) {
        this.ad = ad;
        this.buyer = buyer;
    }

    /**
     * @author Ermiamirzaei
     * Delivers the order by finding the nearest available courier,
     * calculating the distance to the buyer and seller, updating the status of the ad,
     * making the courier unavailable during the delivery, and updating the wallets of the buyer and admin.
     */
    public void deliverOrder(){
        this.courier = findNearestCourier();
        if(this.courier == null){
            System.out.println("\u001B[37mNo courier available.");
        }else {
            int distanceBuyerToSeller = (int) Math.sqrt(Math.pow(this.buyer.getXLocation() - this.ad.getSeller().getXLocation(), 2)
                    + Math.pow(this.buyer.getYLocation() - this.ad.getSeller().getYLocation(), 2));
            int distance = (int) Math.sqrt(Math.pow(courier.getXLocation() - this.ad.getSeller().getXLocation(), 2) + Math.pow(courier.getYLocation()
                    - this.ad.getSeller().getYLocation(), 2)) + distanceBuyerToSeller;
            this.ad.setAdsStatus(AdsStatus.SOLD);
            this.courier.makeUnavailable(distance * 3, ad);
            this.courier.setXLocation(this.buyer.getXLocation());
            this.courier.setYLocation(this.buyer.getYLocation());
            if (this.courier.getCourierKind() == CourierKind.MOTOR) {
                Database.allAdmin.get(0).increaseWallet(distanceBuyerToSeller * 2000);
                buyer.decreaseWallet(distanceBuyerToSeller * 2000);
            }
            if (this.courier.getCourierKind() == CourierKind.VAN) {
                Database.allAdmin.get(0).increaseWallet(distanceBuyerToSeller * 4000);
                buyer.decreaseWallet(distanceBuyerToSeller * 4000);
            }
        }
    }

    /**
     * Finds the nearest available courier to deliver the order.
     *
     * @return The nearest available courier, or null if no couriers are available.
     */
    private Courier findNearestCourier(){
        Random random = new Random();
        int index = random.nextInt(Database.allAdmin.size());
        return Database.allAdmin.get(index).findNearestCourier(this.ad, this.ad.getProductKind());
    }
}
