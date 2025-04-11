import java.util.Random;

public abstract class BattleLoc extends Location {
    private Monster monster;
    private Item award;
    private int maxMonster;

    public BattleLoc(int id, Player player, String name, Monster monster, Item award, int maxMonster) {
        super(id, player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        if (isHaveAward()){
            System.out.println(this.getName() + " Bölgesini Zaten Temizlediniz");
            return true;
        }
        int monsNumber = this.randomMonsterNumber();
        System.out.println("Şuan buradasınız: " + this.getName());
        System.out.println("Dikkatli Ol! Burada " + monsNumber + " tane " + this.getMonster().getName() + " yaşıyor!");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = sc.next();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(monsNumber)) {
            System.out.println(this.getName() + " tüm düşmanları yendiniz");
            takeAward();
            return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz!");
            return false;
        }
        return true;
    }

    public boolean isHaveAward(){
        if (this.getName().equals("Mağara")){
            if (this.getPlayer().getInventory().getItem1().getName() != null){
                return true;
            }
        }
        if (this.getName().equals("Orman")){
            if (this.getPlayer().getInventory().getItem2().getName() != null){
                return true;
            }
        }
        if (this.getName().equals("Nehir")){
            if (this.getPlayer().getInventory().getItem3().getName() != null){
                return true;
            }
        }
        return false;
    }

    public void takeAward(){
        if (this.getName().equals("Mağara")){
            this.getPlayer().getInventory().setItem1(this.getAward());
        }
        if (this.getName().equals("Orman")){
            this.getPlayer().getInventory().setItem2(this.getAward());
        }
        if (this.getName().equals("Nehir")){
            this.getPlayer().getInventory().setItem3(this.getAward());
        }
    }

    public boolean combat(int monsterNumber) {
        for (int i = 1; i <= monsterNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getDefHealth());
            playerStats();
            monsterStats(i);

            boolean playerFirst = Math.random() < 0.5;

            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.println("<V>ur veya <K>aç: ");
                String selectCombat = sc.next();
                System.out.println(playerFirst ? "İlk vuruş hakkı sizde!" : "İlk vuruş hakkı canavarda!");
                selectCombat = selectCombat.toUpperCase();
                if (selectCombat.equals("V")) {
                    if (playerFirst) {
                        // Oyuncu ilk vuruyor
                        System.out.println("Siz Vurdunuz");
                        this.monster.setHealth(this.monster.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar Size Vurdu");
                            int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (monsterDamage < 0) monsterDamage = 0;
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();
                        }
                    } else {
                        // Canavar ilk vuruyor
                        System.out.println("Canavar Size Vurdu");
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (monsterDamage < 0) monsterDamage = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                        afterHit();

                        if (this.getPlayer().getHealth() > 0) {
                            System.out.println("Siz Vurdunuz");
                            this.monster.setHealth(this.monster.getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }
                } else {
                    return false;
                }
            }

            if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı Yendiniz");
                if (this.getName().equals("Maden")){
                    mineReward();
                } else {
                    System.out.println(this.getMonster().getAwardGold() + " para kazandınız");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAwardGold());
                    System.out.println("Güncel Paranız: " + this.getPlayer().getMoney());
                }

            } else {
                return false;
            }
        }

        return true;
    }

    public void mineReward(){
        Random r = new Random();
        int mainReward = r.nextInt(100) + 1;
        if (mainReward <= 15){
            int selection = subReward();
            switch (selection){
                case 1:
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                    System.out.println("Tebrikler " + this.getPlayer().getInventory().getWeapon().getName() + " Kazandınız");
                    break;
                case 2:
                    if (this.getPlayer().getInventory().getWeapon().getId() < 2){
                        this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                        System.out.println("Tebrikler " + this.getPlayer().getInventory().getWeapon().getName() + " Kazandınız");
                        break;
                    } else {
                        System.out.println("Zaten bu eşyaya sahipsiniz");
                        break;
                    }

                case 3:
                    if (this.getPlayer().getInventory().getWeapon().getId() < 1){
                        this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                        System.out.println("Tebrikler " + this.getPlayer().getInventory().getWeapon().getName() + " Kazandınız");
                        break;
                    } else {
                        System.out.println("Zaten bu eşyaya sahipsiniz");
                        break;
                    }
            }
        } else if (mainReward <= 30) {
            int selection = subReward();
            switch (selection){
                case 1:
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                    System.out.println("Tebrikler " + this.getPlayer().getInventory().getArmor().getName() + " Zırh Kazandınız");
                    break;
                case 2:
                    if (this.getPlayer().getInventory().getArmor().getId() < 2){
                        this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                        System.out.println("Tebrikler " + this.getPlayer().getInventory().getArmor().getName() + " Zırh Kazandınız");
                        break;
                    } else {
                        System.out.println("Zaten bu eşyaya sahipsiniz");
                        break;
                    }

                case 3:
                    if (this.getPlayer().getInventory().getArmor().getId() < 1){
                        this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                        System.out.println("Tebrikler " + this.getPlayer().getInventory().getArmor().getName() + " Zırh Kazandınız");
                        break;
                    } else {
                        System.out.println("Zaten bu eşyaya sahipsiniz");
                        break;
                    }

            }
        } else if (mainReward <= 55)  {
            int selection = subReward();
            switch (selection){
                case 1:
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                    System.out.println("Tebrikler 10 Para Kazandınız");
                    break;
                case 2:
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                    System.out.println("Tebrikler 5 Para Kazandınız");
                    break;
                case 3:
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                    System.out.println("Tebrikler 1 Para Kazandınız");
            }
        } else {
            System.out.println("Hiçbir şey kazanamadınız.");
        }
    }

    public int subReward(){
        Random r = new Random();
        int subReward = r.nextInt(100) + 1;

        if (subReward <= 20){
            return 1;
        } else if (subReward <= 50){
            return 2;
        } else {
            return 3;
        }
    }

    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " Canı: " + this.getMonster().getHealth());
        System.out.println("-------------");
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("-----------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void monsterStats(int i) {
        System.out.println(i + ". " + this.getMonster().getName() + " Değerleri");
        System.out.println("-----------------------");
        System.out.println("Sağlık: " + this.getMonster().getHealth());
        System.out.println("Hasar: " + this.getMonster().getDamage());
        System.out.println("Ödül: " + this.getMonster().getAwardGold());
        System.out.println();
    }

    public int randomMonsterNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Item getAward() {
        return award;
    }

    public void setAward(Item award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
