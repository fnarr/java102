import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final AccountManager accountManager = new AccountManager();
    private static Scanner scanner = new Scanner(System.in);
    private static Account loggedInAccount = null;

    public static void main(String[] args) {
        createSampleAccounts();

        while (true) {
            System.out.println("\n--- Sigorta Yönetim Sistemi ---");
            System.out.println("1. Giriş Yap");
            System.out.println("2. Kayıt Ol");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    if (loggedInAccount != null) {
                        showMainMenu();
                    }
                    break;
                case "2":
                    registerUser(); // YENİ AD
                    break;
                case "3":
                    System.out.println("Çıkış yapılıyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
    }

    private static void createSampleAccounts() {
        // İlk kullanıcı (sadece hesap)
        accountManager.register("Ali", "Yılmaz", "aliylmz@gmail.com", "12345", "Mühendis", 30);

        // İkinci kullanıcı (hesap ve bir iş adresi)
        User ayse = new User("Ayşe", "Demir", "aysedemir@hotmail.com", "abcde", "Öğretmen", 25);
        BusinessAddress ayseIsAdresi = new BusinessAddress("XYZ A.Ş.", "Yönetim", "İş Merkezi Kat:3, İstanbul - 34000");
        ayse.getAddresses().add(ayseIsAdresi);
        Enterprise ayseHesabi = new Enterprise(ayse);
        accountManager.addAccount(ayseHesabi);

        // Üçüncü kullanıcı (hesap, bir ev adresi ve bir sağlık sigortası)
        User veli = new User("Veli", "Koç", "veli.koc@outlook.com", "qwerty", "Doktor", 40);
        HomeAddress veliEvAdresi = new HomeAddress("Güneş Sokak No:5", "Ankara", "06500");
        veli.getAddresses().add(veliEvAdresi);
        Individual veliHesabi = new Individual(veli);
        veliHesabi.addInsurancePolicy(new HealthInsurance("Özel Sağlık Sigortası", 750.0, new Date(), new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000L)));
        accountManager.addAccount(veliHesabi);
    }

    private static void login() {
        System.out.println("\n--- Giriş Yap ---");
        System.out.print("E-posta: ");
        String email = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        try {
            loggedInAccount = accountManager.login(email, password);
            System.out.println("Giriş Başarılı!");
        } catch (InvalidAuthenticationException e) {
            System.out.println("Giriş Başarısız: " + e.getMessage());
        }
    }

    private static void registerUser() { // YENİ AD VE GÜNCELLEME
        System.out.println("\n--- Kayıt Ol ---");
        System.out.print("Adınız: ");
        String name = scanner.nextLine();
        System.out.print("Soyadınız: ");
        String surname = scanner.nextLine();
        System.out.print("E-posta: ");
        String email = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();
        System.out.print("Meslek: ");
        String occupation = scanner.nextLine();
        System.out.print("Yaş: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Yeni satır karakterini tüket

        accountManager.register(name, surname, email, password, occupation, age);
        System.out.println("Kayıt Başarılı. Giriş yapabilirsiniz.");
    }

    private static void showMainMenu() {
        while (loggedInAccount != null) {
            System.out.println("\n--- Ana Menü ---");
            System.out.println("1. Bilgilerimi Görüntüle");
            System.out.println("2. Sigorta Poliçelerimi Görüntüle");
            System.out.println("3. Yeni Sigorta Poliçesi Ekle");
            System.out.println("4. Sigorta Poliçesi Sil");
            System.out.println("5. Adres Ekle"); // YENİ SEÇENEK
            System.out.println("6. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    loggedInAccount.showUserInfo();
                    break;
                case "2":
                    loggedInAccount.displayInsurances();
                    break;
                case "3":
                    addInsurancePolicyToAccount();
                    break;
                case "4":
                    removeInsurancePolicyFromAccount();
                    break;
                case "5":
                    addAddressToAccount(); // YENİ METOT ÇAĞRISI
                    break;
                case "6":
                    System.out.println("Çıkış yapılıyor...");
                    loggedInAccount = null;
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
    }

    private static void addAddressToAccount() {
        if (loggedInAccount != null) {
            System.out.println("\n--- Adres Ekle ---");
            System.out.println("Hangi tür adres eklemek istersiniz?");
            System.out.println("1. Ev Adresi");
            System.out.println("2. İş Adresi");
            System.out.print("Seçiminizi yapın: ");
            String addressTypeChoice = scanner.nextLine();

            switch (addressTypeChoice) {
                case "1":
                    System.out.print("Sokak: ");
                    String street = scanner.nextLine();
                    System.out.print("Şehir: ");
                    String city = scanner.nextLine();
                    System.out.print("Posta Kodu: ");
                    String zipCode = scanner.nextLine();
                    HomeAddress homeAddress = new HomeAddress(street, city, zipCode);
                    loggedInAccount.addAddress(homeAddress);
                    System.out.println("Ev adresi başarıyla eklendi.");
                    break;
                case "2":
                    System.out.print("Şirket Adı: ");
                    String companyName = scanner.nextLine();
                    System.out.print("Departman: ");
                    String department = scanner.nextLine();
                    System.out.print("Tam Adres: ");
                    String fullAddress = scanner.nextLine();
                    BusinessAddress businessAddress = new BusinessAddress(companyName, department, fullAddress);
                    loggedInAccount.addAddress(businessAddress);
                    System.out.println("İş adresi başarıyla eklendi.");
                    break;
                default:
                    System.out.println("Geçersiz adres türü seçimi.");
            }
        } else {
            System.out.println("Lütfen önce giriş yapın.");
        }
    }

    private static void addInsurancePolicyToAccount() { // YENİ AD VE İÇERİK
        if (loggedInAccount != null) {
            System.out.println("\n--- Yeni Sigorta Poliçesi Ekle ---");
            System.out.println("Hangi tür sigorta eklemek istersiniz?");
            System.out.println("1. Sağlık Sigortası");
            System.out.println("2. Konut Sigortası");
            System.out.println("3. Seyahat Sigortası");
            System.out.println("4. Araç Sigortası");
            System.out.print("Seçiminizi yapın: ");
            String insuranceTypeChoice = scanner.nextLine();

            System.out.print("Sigorta Adı: ");
            String name = scanner.nextLine();
            System.out.print("Ücreti: ");
            double fee = scanner.nextDouble();
            scanner.nextLine(); // Yeni satır karakterini tüket
            System.out.print("Başlangıç Tarihi (YYYY-MM-DD): ");
            String startDateStr = scanner.nextLine();
            System.out.print("Bitiş Tarihi (YYYY-MM-DD): ");
            String endDateStr = scanner.nextLine();

            try {
                Date startDate = java.sql.Date.valueOf(startDateStr);
                Date endDate = java.sql.Date.valueOf(endDateStr);
                Insurance newInsurance = null;

                switch (insuranceTypeChoice) {
                    case "1":
                        newInsurance = new HealthInsurance(name, fee, startDate, endDate);
                        break;
                    case "2":
                        newInsurance = new ResidenceInsurance(name, fee, startDate, endDate);
                        break;
                    case "3":
                        newInsurance = new TravelInsurance(name, fee, startDate, endDate);
                        break;
                    case "4":
                        newInsurance = new CarInsurance(name, fee, startDate, endDate);
                        break;
                    default:
                        System.out.println("Geçersiz sigorta türü seçimi.");
                        return;
                }

                loggedInAccount.addInsurancePolicy(newInsurance);
                System.out.println("Sigorta poliçesi başarıyla eklendi.");

            } catch (IllegalArgumentException e) {
                System.out.println("Geçersiz tarih formatı. Lütfen YYYY-MM-DD formatında girin.");
            }
        } else {
            System.out.println("Lütfen önce giriş yapın.");
        }
    }

    private static void removeInsurancePolicyFromAccount() { // YENİ AD VE İÇERİK
        if (loggedInAccount != null && !loggedInAccount.getInsurances().isEmpty()) {
            System.out.println("\n--- Sigorta Poliçesi Sil ---");
            loggedInAccount.displayInsurances(); // GÜNCELLEME
            System.out.print("Silmek istediğiniz poliçenin numarasını girin: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Yeni satır karakterini tüket

            loggedInAccount.removeInsurance(choice - 1); // GÜNCELLEME
            System.out.println("Sigorta poliçesi başarıyla silindi.");
        } else {
            System.out.println("Silinebilecek sigorta poliçeniz bulunmuyor.");
        }
    }
}