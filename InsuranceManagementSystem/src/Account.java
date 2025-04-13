import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Account implements Comparable<Account> {
    private AuthenticationStatus loginStatus;
    private User user;
    private List<Insurance> insurances;

    public Account(User user) {
        this.user = user;
        this.loginStatus = AuthenticationStatus.FAIL;
        this.insurances = new ArrayList<>();
    }

    public AuthenticationStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(AuthenticationStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public final void showUserInfo() {
        if (user != null) {
            System.out.println("Kullanıcı Bilgileri:");
            System.out.println("Ad: " + user.getName());
            System.out.println("Soyad: " + user.getSurname());
            System.out.println("E-posta: " + user.getEmail());
            System.out.println("Meslek: " + user.getOccupation());
            System.out.println("Yaş: " + user.getAge());
            System.out.println("Son Giriş Tarihi: " + user.getLastLoginDate());
            System.out.println("Adresler:");
            if (user.getAddresses() != null && !user.getAddresses().isEmpty()) {
                for (Address address : user.getAddresses()) {
                    System.out.println("- " + address.getAddressDetails());
                }
            } else {
                System.out.println("  Kayıtlı adres bulunmuyor.");
            }
            System.out.println("Sigorta Poliçeleri:");
            if (insurances != null && !insurances.isEmpty()) {
                for (Insurance insurance : insurances) {
                    System.out.println("- " + insurance.getName() + " (Ücret: " + insurance.getFee() + ")");
                }
            } else {
                System.out.println("  Kayıtlı sigorta poliçesi bulunmuyor.");
            }
        } else {
            System.out.println("Kullanıcı bilgisi bulunamadı.");
        }
    }

    public void displayInsurances() {
        if (!insurances.isEmpty()) {
            System.out.println("\n--- Sigorta Poliçeleriniz ---");
            for (int i = 0; i < insurances.size(); i++) {
                Insurance insurance = insurances.get(i);
                System.out.println((i + 1) + ". " + insurance.getName() + " (Ücret: " + insurance.getFee() + ")");
            }
        } else {
            System.out.println("Henüz kayıtlı sigorta poliçeniz bulunmuyor.");
        }
    }

    public void removeInsurance(int index) {
        if (index >= 0 && index < insurances.size()) {
            insurances.remove(index);
            System.out.println("Sigorta poliçesi başarıyla silindi.");
        } else {
            System.out.println("Geçersiz poliçe numarası.");
        }
    }


    public void addAddress(Address address){
        if (this.user != null && address != null){
            AddressManager.addUserAddress(this.user, address);
        }
    }

    public void removeAddress(Address address){
        if (this.user != null && address != null){
            AddressManager.removeUserAddress(this.user, address);
        }
    }

    public abstract void addInsurancePolicy(Insurance insurance);

    @Override
    public int compareTo(Account o) {
        if (this.user != null && o.getUser() != null){
            return this.user.getEmail().compareTo(o.getUser().getEmail());
        }
        return 0;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(user.getEmail(), account.getUser().getEmail());
    }

    @Override
    public int hashCode(){
        return Objects.hash(user.getEmail());
    }
}
