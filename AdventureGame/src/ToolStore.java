public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(2, player, "Ekipman Dükkanı");
    }

    @Override
    public boolean onLocation() {
        System.out.println("--------------------------");
        System.out.println("*** Mağazaya Hoşgeldiniz ***");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış Yap");
            System.out.print("Seçiminiz: ");
            int selectCase = sc.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Geçersiz değer, tekrar giriniz: ");
                selectCase = sc.nextInt();
            }
            switch (selectCase) {
                case 1:
                    System.out.println();
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    System.out.println();
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz");
                    showMenu = false;
                    break;
            }
        }
        System.out.println("--------------------------");
        return true;
    }

    public void printWeapon() {
        System.out.println("***** Silahlar *****");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + ". " + w.getName() + " <Para: " + w.getPrice() + ", Hasar: " + w.getDamage() + ">");
        }
        System.out.println((Weapon.weapons().length + 1) + ". Çıkış yap");
    }

    public void buyWeapon(){
        System.out.print("Satın almak istediğiniz silahı seçin: ");
        int selectWeaponID = sc.nextInt();
        while (selectWeaponID < 1 || selectWeaponID > Weapon.weapons().length + 1) {
            System.out.print("Geçersiz değer, tekrar giriniz: ");
            selectWeaponID = sc.nextInt();
        }
        if (selectWeaponID != Weapon.weapons().length + 1){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectedWeapon != null && selectedWeapon.getId() > this.getPlayer().getInventory().getWeapon().getId() && selectedWeapon.getId() != this.getPlayer().getInventory().getWeapon().getId()) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır");
                } else {
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    System.out.println("Önceki Silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni Silahınz: " + this.getPlayer().getInventory().getWeapon().getName());
                }
            } else {
                System.out.println("Bu silaha zaten sahipsiniz");
            }
        }
    }

    public void printArmor() {
        System.out.println("***** Zırhlar *****");
        for (Armor armor : Armor.armors()) {
            System.out.println(armor.getId() + ". " + armor.getName() + " <Para: " + armor.getPrice() + ", Engelleme: " + armor.getBlock() + ">");
        }
        System.out.println((Armor.armors().length + 1) + ". Çıkış Yap");
    }

    public void buyArmor(){
        System.out.print("Satın almak istediğiniz zırhı seçin: ");
        int selectArmorID = sc.nextInt();
        while (selectArmorID < 1 || selectArmorID > Armor.armors().length + 1) {
            System.out.print("Geçersiz değer, tekrar giriniz: ");
            selectArmorID = sc.nextInt();
        }
        if (selectArmorID != 4){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if (selectedArmor != null && selectedArmor.getId() > this.getPlayer().getInventory().getArmor().getId() && selectedArmor.getId() != this.getPlayer().getInventory().getArmor().getId()) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır");
                } else {
                    System.out.println(selectedArmor.getName() + " zırhını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    System.out.println("Önceki Zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Yeni Zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                }
            } else {
                System.out.println("Bu zırha zaten sahipsiniz");
            }
        }
    }
}
