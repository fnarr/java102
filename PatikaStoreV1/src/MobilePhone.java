public class MobilePhone extends Product{
    private String memory;
    private String screenSize;
    private int batteryPower;
    private String ram;
    private String color;

    public MobilePhone(int id, double unitPrice, double discountRate, int stockAmount, String name, Brand brand, String memory, String screenSize, int batteryPower, String ram, String color) {
        super(id, unitPrice, discountRate, stockAmount, name, brand);
        this.memory = memory;
        this.screenSize = screenSize;
        this.batteryPower = batteryPower;
        this.ram = ram;
        this.color = color;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public int getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(int batteryPower) {
        this.batteryPower = batteryPower;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void displayProperties() {
        displayCommonProperties();
        System.out.format(" %-8s | %-10s | %-10d | %-5s | %-10s |\n", memory, screenSize, batteryPower, ram, color);
    }

    @Override
    public String getProductType() {
        return "Cep Telefonu";
    }
}
