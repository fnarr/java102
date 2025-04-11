public class SafeHouse extends NormalLoc{

    public SafeHouse(Player player) {
        super(1,player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation(){
        System.out.println("--------------------------");
        System.out.println("***Güvenli Evdesiniz***");
        System.out.println("Canınız yenilendi");
        this.getPlayer().setHealth(this.getPlayer().getDefHealth());
        if (isWin()) return false;
        System.out.println("--------------------------");
        return true;
    }

    public boolean isWin(){
        if (this.getPlayer().getInventory().getItem1().getName() != null && this.getPlayer().getInventory().getItem2().getName() != null && this.getPlayer().getInventory().getItem3().getName() != null){
            return true;
        } else return false;
    }
}
