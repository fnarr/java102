import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        PatikaStore.addStaticBrands();
        PatikaStore.addInitialProducts();

        while (true) {
            System.out.println("\nPatikaStore Ürün Yönetim Sistemi");
            System.out.println("1 - Markaları Listele");
            System.out.println("2 - Ürünleri Listele (Kategoriye Göre)");
            System.out.println("3 - Ürün Ekle");
            System.out.println("4 - Ürün Sil (ID'ye Göre)");
            System.out.println("5 - Ürünleri Filtrele (ID ve/veya Markaya Göre)");
            System.out.println("0 - Çıkış");
            System.out.print("Lütfen bir işlem seçin: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    PatikaStore.listBrands();
                    break;
                case "2":
                    PatikaStore.listProductsByCategory();
                    break;
                case "3":
                    PatikaStore.addProduct();
                    break;
                case "4":
                    PatikaStore.deleteProduct();
                    break;
                case "5":
                    PatikaStore.filterProducts();
                    break;
                case "0":
                    System.out.println("PatikaStore'dan çıkılıyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Geçersiz işlem. Lütfen tekrar deneyin.");
            }
        }
    }
}