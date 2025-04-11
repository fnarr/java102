public class Monster {
    private String name;
    private int id;
    private int damage;
    private int health;
    private int awardGold;
    private int defHealth;

    public Monster(String name, int id, int damage, int health, int awardGold) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.defHealth = health;
        this.awardGold = awardGold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAwardGold() {
        return awardGold;
    }

    public void setAwardGold(int awardGold) {
        this.awardGold = awardGold;
    }

    public int getDefHealth() {
        return defHealth;
    }

    public void setDefHealth(int defHealth) {
        this.defHealth = defHealth;
    }
}
