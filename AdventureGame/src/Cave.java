public class Cave extends BattleLoc{
    public Cave(Player player) {
        super(3, player, "Mağara", new Zombie(), new Item(1,"food"), 3);
    }
}
