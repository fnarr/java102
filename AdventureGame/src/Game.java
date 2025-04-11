import java.util.Scanner;

public class Game {
    private Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hoşgeldiniz.");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = sc.nextLine();
        Player player = new Player(playerName);
        System.out.println();
        System.out.println("Savaşçı " + player.getName() + " bu karanlık ve sisli dünyaya hoşgeldin!");
        player.selectChar();
        Location loc = null;

        while (true) {
            player.printInfo();
            Location[] locations = {new SafeHouse(player), new ToolStore(player), new Cave(player), new Forest(player), new River(player), new Mine(player)};
            System.out.println();
            System.out.println("***** Bölgeler *****");
            System.out.println();
            System.out.println("--------------------------");
            for (Location location : locations) {
                System.out.println(location.getId() + ". Bölge: " + location.getName());
            }
            System.out.println((locations.length + 1) + ". Çıkış Yap");
            System.out.println("--------------------------");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçin: ");
            int selectLocation = sc.nextInt();
            if (selectLocation == locations.length + 1){
                loc = null;
                break;
            }
            switch (selectLocation) {
                case 1:
                    loc = new SafeHouse(player);
                    break;
                case 2:
                    loc = new ToolStore(player);
                    break;
                case 3:
                    loc = new Cave(player);
                    break;
                case 4:
                    loc = new Forest(player);
                    break;
                case 5:
                    loc = new River(player);
                    break;
                case 6:
                    loc = new Mine(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge seçiniz!");
            }

            if (loc == null){
                System.out.println("Oyun Bitti Görüşmek Üzere");
                break;
            }
            if(!loc.onLocation()){
                if (loc.getName().equals("Güvenli Ev")){
                    System.out.println("Tebrikler Tüm Ödülleri Topladınız Oyunu Kazandınız");
                    break;
                }
                System.out.println("***Game Over***");
                break;
            }
        }
    }
}
