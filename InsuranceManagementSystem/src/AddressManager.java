import java.util.ArrayList;

public class AddressManager {

    public static void addUserAddress(User user, Address address){
        if (user != null && address != null){
            ArrayList<Address> addresses = user.getAddresses();
            addresses.add(address);
            user.setAddresses(addresses);
            System.out.println("Adres başarıyla eklendi: " + address.getAddressDetails());
        } else {
            System.out.println("Kullanıcı veya adres nesnesi null olamaz.");
        }
    }

    public static void removeUserAddress(User user, Address address){
        if (user != null && address != null){
            ArrayList<Address> addresses = user.getAddresses();
            if (addresses.contains(address)){
                addresses.remove(address);
                user.setAddresses(addresses);
                System.out.println("Adres başarıyla silindi: " + address.getAddressDetails());
            } else {
                System.out.println("Bu adres kullanıcının listesinde bulunmuyor.");
            }
        } else {
            System.out.println("Kullanıcı veya adres nesnesi null olamaz.");
        }
    }
}
