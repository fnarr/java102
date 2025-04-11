public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private Item item1;
    private Item item2;
    private Item item3;
    public Inventory() {
        this.weapon = new Weapon("Yumruk", 0,0,0);
        this.armor = new Armor(0,"Paçavra",0,0);
        this.item1 = new Item(0, null);
        this.item2 = new Item(0, null);
        this.item3 = new Item(0, null);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Item getItem1() {
        return item1;
    }

    public void setItem1(Item item1) {
        this.item1 = item1;
    }

    public Item getItem2() {
        return item2;
    }

    public void setItem2(Item item2) {
        this.item2 = item2;
    }

    public Item getItem3() {
        return item3;
    }

    public void setItem3(Item item3) {
        this.item3 = item3;
    }
}
