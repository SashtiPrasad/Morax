package app.morax.Model.Base;

public class Login {

    private static Login instance;
    private String password;
    private String username;

    private Login(String username, String password){
        this.username = username;
        this.password = password;
    }

    public static Login getInstance() {
        return instance;
    }

    public static void initialize(String username, String password) {
        instance = new Login(username, password);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
