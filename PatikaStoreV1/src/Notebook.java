public class Notebook extends Product{
    private String ram;
    private String storage;
    private String screenSize;

    public Notebook(int id, double unitPrice, double discountRate, int stockAmount, String name, Brand brand, String ram, String storage, String screenSize) {
        super(id, unitPrice, discountRate, stockAmount, name, brand);
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public String getProductType() {
        return "Notebook";
    }

    @Override
    public void displayProperties() {
        System.out.format(" %-5s | %-10s | %-10s | %-10s | %-10s |\n", "", "", "", ram, storage);
    }
}
