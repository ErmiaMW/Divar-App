package ir.ac.kntu;

import java.util.HashMap;

/**
 * @author Ermiamirzaei
 * The Ads class represents an advertisement for a product.
 * It contains information about the product name, the seller who posted the ad,
 * the price of the product, and the category of the product.
 */
public class Ads {
    /**
     * Constructs a new Ad with the specified product name, seller, product price, and product kind.
     * @param productName the name of the product
     * @param seller the seller who posted the ad
     * @param productPrice the price of the product
     * @param productKind the category of the product
     */
    private String productName;
    private Seller seller;
    private int productPrice;
    private ProductKind productKind;
    private AdsStatus adsStatus;
    private HashMap<Buyer, String> comment = new HashMap<>();

    public Ads(String productName, Seller seller, int productPrice, ProductKind productKind, AdsStatus adsStatus) {
        this.productName = productName;
        this.seller = seller;
        this.productPrice = productPrice;
        this.productKind = productKind;
        this.adsStatus = adsStatus;
    }

    public void addComment(Buyer buyer, String comment){
        this.comment.put(buyer, comment);
    }

    public String getProductName() {
        return productName;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public ProductKind getProductKind() {
        return productKind;
    }

    public AdsStatus getAdsStatus() {
        return adsStatus;
    }

    public void setAdsStatus(AdsStatus adsStatus) {
        this.adsStatus = adsStatus;
    }

    public HashMap<Buyer, String> getComment() {
        return comment;
    }

}
