import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Çift sayıda takım senaryosu
        List<String> teamsEven = new ArrayList<>();
        teamsEven.add("Galatasaray");
        teamsEven.add("Bursaspor");
        teamsEven.add("Fenerbahçe");
        teamsEven.add("Beşiktaş");
        teamsEven.add("Başakşehir");
        teamsEven.add("Trabzonspor");

        List<List<String>> fixtureEven = Fixture.generateFixture(teamsEven);
        Fixture.printFixture(teamsEven, fixtureEven);

        System.out.println("------------------------------------\n");

        // Tek sayıda takım senaryosu
        List<String> teamsOdd = new ArrayList<>();
        teamsOdd.add("Galatasaray");
        teamsOdd.add("Bursaspor");
        teamsOdd.add("Fenerbahçe");
        teamsOdd.add("Beşiktaş");
        teamsOdd.add("Başakşehir");
        teamsOdd.add("Keçiörengücü");
        teamsOdd.add("Boluspor");

        List<List<String>> fixtureOdd = Fixture.generateFixture(teamsOdd);
        Fixture.printFixture(teamsOdd, fixtureOdd);
    }
}