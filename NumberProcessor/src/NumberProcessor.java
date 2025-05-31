import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberProcessor {

    // Ortak tek ve çift sayılar için ArrayList'ler
    private static final List<Integer> oddNumbers = Collections.synchronizedList(new ArrayList<>());
    private static final List<Integer> evenNumbers = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        ArrayList<Integer> fullList = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            fullList.add(i);
        }

        int chunkSize = 2500;
        List<List<Integer>> subLists = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int start = i * chunkSize;
            int end = start + chunkSize;
            subLists.add(new ArrayList<>(fullList.subList(start, end)));
        }

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new NumberFinder(subLists.get(i), i + 1));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Tek Sayılar Toplamı: " + oddNumbers.size());
        System.out.println("Çift Sayılar Toplamı: " + evenNumbers.size());

        // İsteğe bağlı: Tek ve çift sayıları ekrana yazdırma
        // System.out.println("Tek Sayılar: " + oddNumbers);
        // System.out.println("Çift Sayılar: " + evenNumbers);
    }

    static class NumberFinder implements Runnable {
        private final List<Integer> numbers;
        private final int threadId;

        public NumberFinder(List<Integer> numbers, int threadId) {
            this.numbers = numbers;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println("Thread " + threadId + " başladı. İşlenen eleman sayısı: " + numbers.size());
            for (Integer number : numbers) {
                if (number % 2 == 0) {
                    evenNumbers.add(number);
                } else {
                    oddNumbers.add(number);
                }
            }
            System.out.println("Thread " + threadId + " bitti.");
        }
    }
}