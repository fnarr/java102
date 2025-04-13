import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("Yüzüklerin Efendisi", 1000, "J.R.R Tolkien", "1954");
        Book b2 = new Book("Harry Potter", 500, "J.K. Rowling", "1997");
        Book b3 = new Book("Otostopçunun Galaksi Rehberi", 600, "Douglas Adams", "1979");
        Book b4 = new Book("Metro 2033", 599, "Dmitri Gluhovski", "2002");
        Book b5 = new Book("1984", 300, "George Orwell", "1949");

        Set<Book> booksByName = new TreeSet<>();
        booksByName.add(b1);
        booksByName.add(b2);
        booksByName.add(b3);
        booksByName.add(b4);
        booksByName.add(b5);

        System.out.println("Kitaplar isme göre sıralı:");
        for (Book book: booksByName){
            System.out.println(book);
        }

        System.out.println("\n-------------------------------------------------------------------------------------------------------\n");

        Set<Book> booksByPage = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPageNumber() - o2.getPageNumber();
            }
        });

        booksByPage.add(b1);
        booksByPage.add(b2);
        booksByPage.add(b3);
        booksByPage.add(b4);
        booksByPage.add(b5);

        System.out.println("Kitaplar sayfa sayısına göre sıralı:");
        for (Book book: booksByPage){
            System.out.println(book);
        }
    }
}