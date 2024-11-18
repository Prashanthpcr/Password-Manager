import java.util.HashMap;
import java.util.Map;

public class PasswordManager {
    private final String masterPassword;
    private final Map<String, Account> accounts;
    private final FileHandler fileHandler;
    private boolean authenticated;

    public PasswordManager(String masterPassword) {
        this.masterPassword = masterPassword;
        this.accounts = new HashMap<>();
        this.fileHandler = new FileHandler();
        this.authenticated = authenticate();
        if (authenticated) {
            loadAccounts();
        }
    }

    private boolean authenticate() {
        String savedHash = fileHandler.getMasterPasswordHash();
        return savedHash == null || savedHash.equals(EncryptionUtils.hashPassword(masterPassword));
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void addAccount(String accountName, String username, String password) {
        String encryptedPassword = EncryptionUtils.encrypt(password, masterPassword);
        Account account = new Account(accountName, username, encryptedPassword);
        accounts.put(accountName, account);
        saveAccounts();
        System.out.println("Account added successfully!");
    }

    public void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        for (Account account : accounts.values()) {
            System.out.println(account);
        }
    }

    public void retrievePassword(String accountName) {
        Account account = accounts.get(accountName);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        String decryptedPassword = EncryptionUtils.decrypt(account.getPassword(), masterPassword);
        System.out.println("Password for " + accountName + ": " + decryptedPassword);
    }

    public void deleteAccount(String accountName) {
        if (accounts.remove(accountName) != null) {
            saveAccounts();
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not found!");
        }
    }

    private void saveAccounts() {
        fileHandler.saveAccounts(accounts);
    }

    private void loadAccounts() {
        Map<String, Account> savedAccounts = fileHandler.loadAccounts();
        if (savedAccounts != null) {
            accounts.putAll(savedAccounts);
        }
    }
}
