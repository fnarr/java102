import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fixture {

    public static List<List<String>> generateFixture(List<String> teams) {
        List<String> allTeams = new ArrayList<>(teams);
        if (allTeams.size() % 2 != 0) {
            allTeams.add("Bay");
        }

        Collections Collections = null;
        Collections.shuffle(allTeams); // Takım listesini karıştır

        int numTeams = allTeams.size();
        int numRounds = (numTeams - 1) * 2;
        List<List<String>> fixture = new ArrayList<>();

        // İlk devre fikstürü oluştur
        for (int round = 0; round < numTeams - 1; round++) {
            List<String> currentRound = new ArrayList<>();
            for (int i = 0; i < numTeams / 2; i++) {
                String team1 = allTeams.get(i);
                String team2 = allTeams.get(numTeams - 1 - i);
                currentRound.add(team1 + " vs " + team2);
            }
            fixture.add(currentRound);

            String temp = allTeams.get(numTeams - 1);
            for (int i = numTeams - 1; i > 1; i--) {
                allTeams.set(i, allTeams.get(i - 1));
            }
            allTeams.set(1, temp);
        }

        // İkinci devre fikstürü oluştur (ilk devrenin tersi eşleşmeler)
        for (int round = 0; round < numTeams - 1; round++) {
            List<String> currentRound = new ArrayList<>();
            List<String> firstLegRound = fixture.get(round);
            for (String match : firstLegRound) {
                String[] teamsInMatch = match.split(" vs ");
                currentRound.add(teamsInMatch[1] + " vs " + teamsInMatch[0]);
            }
            fixture.add(currentRound);
        }

        return fixture;
    }

    public static void printFixture(List<String> teams, List<List<String>> fixture) {
        int numTeams = teams.size();
        if (numTeams % 2 != 0) {
            numTeams++;
        }
        int numRounds = (numTeams - 1) * 2;

        System.out.println("Takımlar");
        for (String team : teams) {
            System.out.println("- " + team);
        }
        System.out.println();

        int roundCounter = 1;
        for (List<String> round : fixture) {
            System.out.println("Round " + roundCounter++);
            for (String match : round) {
                System.out.println(match);
            }
            System.out.println();
        }
    }
}
