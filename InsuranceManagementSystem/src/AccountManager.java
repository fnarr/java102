import java.util.TreeSet;

public class AccountManager {
    private TreeSet<Account> accounts;

    public AccountManager() {
        this.accounts = new TreeSet<>();
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public Account login(String email, String password) throws InvalidAuthenticationException {
        Account foundAccount = null;
        for (Account account : accounts) {
            if (account.getUser() != null && account.getUser().getEmail().equals(email)) {
                foundAccount = account;
                break; // E-posta adresi eşleşen bir hesap bulduk, döngüden çıkabiliriz
            }
        }

        if (foundAccount != null) {
            if (foundAccount.getUser().getPassword().equals(password)) {
                foundAccount.setLoginStatus(AuthenticationStatus.SUCCESS);
                foundAccount.getUser().setLastLoginDate(new java.util.Date());
                return foundAccount;
            } else {
                throw new InvalidAuthenticationException("Yanlış şifre.");
            }
        } else {
            throw new InvalidAuthenticationException("Bu e-posta adresine kayıtlı kullanıcı bulunamadı.");
        }
    }

    public void register(String name, String surname, String email, String password, String occupation, int age) {
        User newUser = new User(name, surname, email, password, occupation, age);
        accounts.add(new Individual(newUser));
    }
}
