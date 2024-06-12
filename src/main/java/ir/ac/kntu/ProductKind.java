package ir.ac.kntu;

/**
 * @author ErmiaMirzaei
 * The ProductKind enum represents the category of a product.
 * It includes different categories such as mobile phones, home appliances, stationery, clothing, and automobiles.
 */
public enum ProductKind {
    MOBILE_PHONE(1),
    HOME_APPLIANCES(2),
    STATIONERY(3),
    CLOTHING(4),
    AUTOMOBILE(5);

    private final int value;

    ProductKind(int value) {
        this.value = value;
    }

    public static ProductKind reurnByValue(int i){
        switch (i){
            case 1:
                return ProductKind.MOBILE_PHONE;
            case 2:
                return ProductKind.HOME_APPLIANCES;
            case 3:
                return ProductKind.STATIONERY;
            case 4:
                return ProductKind.CLOTHING;
            case 5:
                return ProductKind.AUTOMOBILE;
            default:
                return null;
        }
    }

    public int getValue() {
        return value;
    }
}
