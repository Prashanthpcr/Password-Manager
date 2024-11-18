import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {
    private static final String DATA_FILE = "data/accounts.data";
    private static final String PASSWORD_FILE = "data/master_password.data";

    @SuppressWarnings("unchecked")
    public Map<String, Account> loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (Map<String, Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }

    public void saveAccounts(Map<String, Account> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMasterPasswordHash() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
