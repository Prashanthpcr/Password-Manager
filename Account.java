import java.io.Serializable;

public class Account implements Serializable {
    private final String name;
    private final String username;
    private final String password;

    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
