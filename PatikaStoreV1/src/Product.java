public abstract class Product {
    private int id;
    private double unitPrice;
    private double discountRate;
    private int stockAmount;
    private String name;
    private Brand brand;

    public Product(int id, double unitPrice, double discountRate, int stockAmount, String name, Brand brand) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.discountRate = discountRate;
        this.stockAmount = stockAmount;
        this.name = name;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public abstract String getProductType();

    public abstract void displayProperties();

    public double getDiscountedPrice() {
        return unitPrice * (1 - discountRate);
    }

    public void displayCommonProperties(){
        System.out.format("| %-5d | %-15s | %-10.2f | %-8.2f | %-6d | %-10s |", id, name, unitPrice, discountRate, stockAmount, brand.getName());
    }
}
