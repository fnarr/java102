import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Book sınıfı
class Book {
    private String kitapIsmi;
    private int sayfaSayisi;
    private String yazarIsmi;
    private String yayinTarihi; // String olarak tutuluyor

    public Book(String kitapIsmi, int sayfaSayisi, String yazarIsmi, String yayinTarihi) {
        this.kitapIsmi = kitapIsmi;
        this.sayfaSayisi = sayfaSayisi;
        this.yazarIsmi = yazarIsmi;
        this.yayinTarihi = yayinTarihi;
    }

    // Getter Metotları
    public String getKitapIsmi() {
        return kitapIsmi;
    }

    public int getSayfaSayisi() {
        return sayfaSayisi;
    }

    public String getYazarIsmi() {
        return yazarIsmi;
    }

    public String getYayinTarihi() {
        return yayinTarihi;
    }

    @Override
    public String toString() {
        return "Kitap [Isim='" + kitapIsmi + '\'' +
                ", Yazar='" + yazarIsmi + '\'' +
                ", Sayfa=" + sayfaSayisi +
                ", Yayin Tarihi='" + yayinTarihi + '\'' +
                ']';
    }
}

public class KitapListesi {

    public static void main(String[] args) {

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Sefiller", 1488, "Victor Hugo", "1862"));
        bookList.add(new Book("Suç ve Ceza", 672, "Fyodor Dostoyevski", "1866"));
        bookList.add(new Book("Küçük Prens", 96, "Antoine de Saint-Exupéry", "1943"));
        bookList.add(new Book("Hayvan Çiftliği", 152, "George Orwell", "1945"));
        bookList.add(new Book("1984", 328, "George Orwell", "1949"));
        bookList.add(new Book("Dönüşüm", 104, "Franz Kafka", "1915"));
        bookList.add(new Book("Kürk Mantolu Madonna", 177, "Sabahattin Ali", "1943"));
        bookList.add(new Book("Satranç", 88, "Stefan Zweig", "1942"));
        bookList.add(new Book("Beyaz Diş", 288, "Jack London", "1906"));
        bookList.add(new Book("Martin Eden", 512, "Jack London", "1909"));

        System.out.println("--- Tüm Kitaplar ---");
        bookList.forEach(System.out::println);
        System.out.println("\n--------------------\n");

        Map<String, String> kitapYazarMap = bookList.stream()
                .collect(Collectors.toMap(Book::getKitapIsmi, Book::getYazarIsmi));

        System.out.println("--- Kitap İsmi ve Yazar İsmi Haritası ---");
        kitapYazarMap.forEach((kitap, yazar) -> System.out.println("Kitap: " + kitap + ", Yazar: " + yazar));
        System.out.println("\n----------------------------------------\n");
        
        List<Book> yuzdenFazlaSayfaliKitaplar = bookList.stream()
                .filter(book -> book.getSayfaSayisi() > 100)
                .collect(Collectors.toList());

        System.out.println("--- Sayfa Sayısı 100'den Fazla Olan Kitaplar ---");
        if (yuzdenFazlaSayfaliKitaplar.isEmpty()) {
            System.out.println("100 sayfadan fazla kitap bulunmamaktadır.");
        } else {
            yuzdenFazlaSayfaliKitaplar.forEach(System.out::println);
        }
        System.out.println("\n-------------------------------------------------\n");
    }
}