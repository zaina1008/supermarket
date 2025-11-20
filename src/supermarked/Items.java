package supermarked;

public class Items {
    private String itemEANCode;       // EAN code
    private String name;
    private double price;
    private double amount;       // number of items or weight
    private String unitType;    // pcs, gram, ml
    private boolean sale;

    public Items(String itemEANCode, String name, double price, double amount, String unitType, boolean sale) {
        this.itemEANCode = itemEANCode;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.unitType = unitType;
        this.sale = sale;
    }

    public String getItemEANCode() {
        return itemEANCode;
    }

    public void setItemEANCode(String itemEANCode) {
        this.itemEANCode = itemEANCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }
}
