package se.contribe.model;


public enum BuyStatus {

    OK(0), NOT_IN_STOCK(1), Does_not_exist(2);

    private int value;

    /**
     * @param value
     */
    private BuyStatus(int value) {
        this.value = value;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public int getValue() {
        return value;
    }

}
