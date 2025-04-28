package auth;

public class Admin {
    private static final String ADMIN_PASSWORD = "marvel123";

    public static boolean authenticate(String password) {
        return ADMIN_PASSWORD.equals(password);
    }
}
