public class InvalidAuthenticationException extends Exception{

    public InvalidAuthenticationException() {
        super("Geçersiz kullanıcı adı veya şifre");
    }

    public InvalidAuthenticationException(String message) {
        super(message);
    }

}
