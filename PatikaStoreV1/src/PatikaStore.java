import java.util.*;

public class PatikaStore {
    private static Set<Brand> brands = new TreeSet<>();
    private static List<Product> products = new ArrayList<>();
    private static int nextProductId = 1;
    private static Scanner sc = new Scanner(System.in);

    static void addStaticBrands(){
        brands.add(new Brand(1, "Samsung"));
        brands.add(new Brand(2, "Lenovo"));
        brands.add(new Brand(3, "Apple"));
        brands.add(new Brand(4, "Huawei"));
        brands.add(new Brand(5, "Casper"));
        brands.add(new Brand(6, "Asus"));
        brands.add(new Brand(7, "HP"));
        brands.add(new Brand(8, "Xiaomi"));
        brands.add(new Brand(9, "Monster"));
    }

    static void listBrands(){
        System.out.println("\n--- Markalar ---");
        for (Brand brand: brands){
            System.out.println("- " + brand);
        }
    }

    static void listProductsByCategory() {
        System.out.println("\nHangi kategorideki ürünleri listelemek istersiniz?");
        System.out.println("1 - Cep Telefonları");
        System.out.println("2 - Notebook");
        System.out.print("Seçiminiz: ");
        String categoryChoice = sc.nextLine();

        List<Product> filteredProducts = new ArrayList<>();
        String categoryName = "";

        switch (categoryChoice) {
            case "1":
                categoryName = "Cep Telefonları";
                for (Product product : products) {
                    if (product instanceof MobilePhone) {
                        filteredProducts.add(product);
                    }
                }
                displayProductTable(filteredProducts, categoryName);
                break;
            case "2":
                categoryName = "Notebook";
                for (Product product : products) {
                    if (product instanceof Notebook) {
                        filteredProducts.add(product);
                    }
                }
                displayProductTable(filteredProducts, categoryName);
                break;
            default:
                System.out.println("Geçersiz kategori seçimi.");
        }
    }

    private static void displayProductTable(List<Product> productList, String category) {
        System.out.println("\n--- " + category + " ---");
        if (productList.isEmpty()) {
            System.out.println("Bu kategoride ürün bulunmamaktadır.");
            return;
        }

        if (category.contains("Cep Telefonları")) {
            System.out.format("| %-5s | %-25s | %-18s | %-9s | %-8s | %-12s | %-10s | %-12s | %-12s | %-6s | %-12s |\n",
                    "ID", "Ürün Adı", "Birim Fiyat", "İndirim", "Stok", "Marka", "Hafıza", "Ekran", "Pil Gücü", "RAM", "Renk");
            System.out.println("|-------|---------------------------|--------------------|-----------|----------|--------------|------------|--------------|--------------|--------|--------------|");
            for (Product product : productList) {
                MobilePhone phone = (MobilePhone) product;
                System.out.format("| %-5d | %-25s | %-15.2f TL | %-9.2f | %-8d | %-12s | %-10s | %-12s | %-12d | %-6s | %-12s |\n",
                        phone.getId(), phone.getName(), phone.getUnitPrice(), phone.getDiscountRate(), phone.getStockAmount(),
                        phone.getBrand().getName(), phone.getMemory(), phone.getScreenSize(), phone.getBatteryPower(),
                        phone.getRam(), phone.getColor());
            }
        } else if (category.contains("Notebook")) {
            System.out.format("| %-5s | %-25s | %-18s | %-9s | %-8s | %-12s | %-6s | %-12s | %-12s |\n",
                    "ID", "Ürün Adı", "Birim Fiyat", "İndirim", "Stok", "Marka", "RAM", "Depolama", "Ekran");
            System.out.println("|-------|---------------------------|--------------------|-----------|----------|--------------|--------|--------------|--------------|");
            for (Product product : productList) {
                Notebook notebook = (Notebook) product;
                System.out.format("| %-5d | %-25s | %-15.2f TL | %-9.2f | %-8d | %-12s | %-6s | %-12s | %-12s |\n",
                        notebook.getId(), notebook.getName(), notebook.getUnitPrice(), notebook.getDiscountRate(),
                        notebook.getStockAmount(), notebook.getBrand().getName(), notebook.getRam(), notebook.getStorage(),
                        notebook.getScreenSize());
            }
        }
    }

    static void addProduct() {
        System.out.println("\n--- Ürün Ekle ---");
        System.out.println("Hangi tür ürün eklemek istersiniz?");
        System.out.println("1 - Cep Telefonu");
        System.out.println("2 - Notebook");
        System.out.print("Seçiminiz: ");
        String productTypeChoice = sc.nextLine();

        System.out.print("Ürün Adı: ");
        String name = sc.nextLine();
        System.out.print("Birim Fiyatı: ");
        double unitPrice = sc.nextDouble();
        System.out.print("İndirim Oranı (0-1 arasında): ");
        double discountRate = sc.nextDouble();
        System.out.print("Stok Miktarı: ");
        int stockAmount = sc.nextInt();
        sc.nextLine(); // Boş satırı oku

        System.out.println("Mevcut Markalar:");
        for (Brand brand : brands) {
            System.out.println("- " + brand.getId() + ": " + brand.getName());
        }
        System.out.print("Marka ID'si: ");
        int brandId = sc.nextInt();
        sc.nextLine(); // Boş satırı oku

        Brand selectedBrand = null;
        for (Brand brand : brands) {
            if (brand.getId() == brandId) {
                selectedBrand = brand;
                break;
            }
        }

        if (selectedBrand == null) {
            System.out.println("Geçersiz marka ID'si.");
            return;
        }

        if (productTypeChoice.equals("1")) {
            System.out.print("Hafıza (Örn: 128 GB): ");
            String memory = sc.nextLine();
            System.out.print("Ekran Boyutu (Örn: 6.1 Inc): ");
            String screenSize = sc.nextLine();
            System.out.print("Pil Gücü (Örn: 4000): ");
            int batteryPower = sc.nextInt();
            sc.nextLine(); // Boş satırı oku
            System.out.print("RAM (Örn: 6 MB): ");
            String ram = sc.nextLine();
            System.out.print("Renk (Örn: Siyah): ");
            String color = sc.nextLine();

            products.add(new MobilePhone(nextProductId++, unitPrice, discountRate, stockAmount, name, selectedBrand,
                    memory, screenSize, batteryPower, ram, color));
            System.out.println("Cep telefonu başarıyla eklendi.");

        } else if (productTypeChoice.equals("2")) {
            System.out.print("RAM (Örn: 8 GB): ");
            String ram = sc.nextLine();
            System.out.print("Depolama (Örn: 512 SSD): ");
            String storage = sc.nextLine();
            System.out.print("Ekran Boyutu (Örn: 14 inç): ");
            String screenSize = sc.nextLine();

            products.add(new Notebook(nextProductId++, unitPrice, discountRate, stockAmount, name, selectedBrand,
                    ram, storage, screenSize));
            System.out.println("Notebook başarıyla eklendi.");

        } else {
            System.out.println("Geçersiz ürün türü seçimi.");
        }
    }

    static void deleteProduct() {
        System.out.println("\n--- Ürün Sil ---");
        System.out.print("Silmek istediğiniz ürünün ID'sini girin: ");
        int productIdToDelete = sc.nextInt();
        sc.nextLine(); // Boş satırı oku

        boolean removed = products.removeIf(product -> product.getId() == productIdToDelete);

        if (removed) {
            System.out.println("Ürün ID " + productIdToDelete + " başarıyla silindi.");
        } else {
            System.out.println("Belirtilen ID'ye sahip ürün bulunamadı.");
        }
    }

    static void addInitialProducts() {
        // Markaları ID'lerine göre bul (veya doğrudan nesnelerini oluşturabilirsiniz)
        Brand samsung = findBrandByName("Samsung");
        Brand lenovo = findBrandByName("Lenovo");
        Brand apple = findBrandByName("Apple");
        Brand huawei = findBrandByName("Huawei");
        Brand asus = findBrandByName("Asus");
        Brand xiaomi = findBrandByName("Xiaomi");
        Brand monster = findBrandByName("Monster"); // Monster markasını da bul

        // Cep Telefonları
        if (samsung != null) {
            products.add(new MobilePhone(nextProductId++, 3199.0, 0.10, 20, "SAMSUNG GALAXY A51", samsung, "128 GB", "6.5 Inc", 4000, "6 GB", "Siyah"));
        }
        if (apple != null) {
            products.add(new MobilePhone(nextProductId++, 7379.0, 0.05, 15, "iPhone 11 64 GB", apple, "64 GB", "6.1 Inc", 3046, "6 GB", "Mavi"));
        }
        if (xiaomi != null) {
            products.add(new MobilePhone(nextProductId++, 4012.0, 0.15, 25, "Redmi Note 10 Pro 8GB", xiaomi, "128 GB", "6.5 Inc", 5020, "8 GB", "Beyaz"));
        }
        if (huawei != null) {
            products.add(new MobilePhone(nextProductId++, 4500.0, 0.08, 12, "HUAWEI P40 Lite", huawei, "128 GB", "6.4 Inc", 4200, "6 GB", "Yeşil"));
        }

        // Notebooklar
        if (huawei != null) {
            products.add(new Notebook(nextProductId++, 7000.0, 0.12, 8, "HUAWEI Matebook 14", huawei, "16 GB", "512 SSD", "14.0 inç"));
        }
        if (lenovo != null) {
            products.add(new Notebook(nextProductId++, 3699.0, 0.05, 18, "LENOVO V14 IGL", lenovo, "8 GB", "1024 HDD", "14.0 inç"));
        }
        if (asus != null) {
            products.add(new Notebook(nextProductId++, 8199.0, 0.18, 7, "ASUS Tuf Gaming", asus, "32 GB", "2048 SSD", "15.6 inç"));
        }
        if (monster != null) {
            products.add(new Notebook(nextProductId++, 12000.0, 0.10, 3, "Monster Abra A5", monster, "16 GB", "500 SSD", "15.6 inç"));
        }
    }

    static Brand findBrandByName(String brandName) {
        for (Brand brand : brands) {
            if (brand.getName().equalsIgnoreCase(brandName)) {
                return brand;
            }
        }
        return null;
    }

    static void filterProducts() {
        System.out.println("\n--- Ürünleri Filtrele ---");
        System.out.print("Filtrelemek istediğiniz ürün ID'sini girin (atlamak için boş bırakın): ");
        String idFilterStr = sc.nextLine();
        Integer idFilter = null;
        if (!idFilterStr.isEmpty()) {
            try {
                idFilter = Integer.parseInt(idFilterStr);
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz ID formatı.");
                return;
            }
        }

        System.out.print("Filtrelemek istediğiniz marka adını girin (atlamak için boş bırakın): ");
        String brandFilter = sc.nextLine().trim().toLowerCase();

        List<Product> filteredList = new ArrayList<>();
        for (Product product : products) {
            boolean idMatch = (idFilter == null || product.getId() == idFilter);
            boolean brandMatch = (brandFilter.isEmpty() || product.getBrand().getName().toLowerCase().contains(brandFilter));

            if (idMatch && brandMatch) {
                filteredList.add(product);
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("Filtre kriterlerine uygun ürün bulunamadı.");
        } else {
            System.out.println("\n--- Filtrelenen Ürünler ---");
            if (!filteredList.isEmpty()) {
                if (filteredList.get(0) instanceof MobilePhone) {
                    displayProductTable(filteredList, "Cep Telefonları (Filtrelenmiş)");
                } else if (filteredList.get(0) instanceof Notebook) {
                    displayProductTable(filteredList, "Notebook (Filtrelenmiş)");
                } else {
                    System.out.println("Filtrelenen ürünler listesi:");
                    for (Product product : filteredList) {
                        System.out.println(product.getName() + " (" + product.getBrand() + ")");
                    }
                }
            }
        }
    }
}
