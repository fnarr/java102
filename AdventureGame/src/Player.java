import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int defHealth;
    private int money;
    private String name;
    private String charName;
    private Scanner sc = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("--------------------------------------------------------------");
        System.out.println("Karakterler");
        System.out.println("--------------------------------------------------------------");
        for (GameChar gameChar : charList) {
            System.out.println("ID: " + gameChar.getId() +
                    "\t Karakter: " + gameChar.getName() +
                    "\t Hasar: " + gameChar.getDamage() +
                    "\t Saglik: " + gameChar.getHealth() +
                    "\t Para: " + gameChar.getMoney());
        }
        System.out.println("--------------------------------------------------------------");
        boolean isCorrectChar = true;
        do {
            System.out.print("Lütfen bir karakter seçiniz: ");
            int selectCharacter = sc.nextInt();
            switch (selectCharacter) {
                case 1:
                    initPlayer(new Samurai());
                    isCorrectChar = false;
                    break;
                case 2:
                    initPlayer(new Archer());
                    isCorrectChar = false;
                    break;
                case 3:
                    initPlayer(new Knight());
                    isCorrectChar = false;
                    break;
                default:
                    System.out.println("Lütfen geçerli bir karakter girin!!");
            }
        } while (isCorrectChar);
        /* System.out.println("Karakter: " + this.getCharName() +
                        ", Hasar: " + this.getDamage() +
                        ", Sağlık: " + this.getHealth() +
                        ", Para: " + this.getMoney());
        */
    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setDefHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public void printInfo(){
        System.out.println("Silahınız: " + this.getInventory().getWeapon().getName() +
                ", Zırhınız: " + this.getInventory().getArmor().getName() +
                ", Bloklama: " + this.getInventory().getArmor().getBlock() +
                ", Hasar: " + this.getTotalDamage() +
                ", Sağlık: " + this.getHealth() +
                ", Para: " + this.getMoney());
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0){
            health = 0;
        }
        this.health = health;
    }

    public int getDefHealth() {
        return defHealth;
    }

    public void setDefHealth(int defHealth) {
        this.defHealth = defHealth;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
