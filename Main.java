import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your master password: ");
        String masterPassword = scanner.nextLine();

        PasswordManager passwordManager = new PasswordManager(masterPassword);

        if (passwordManager.isAuthenticated()) {
            System.out.println("\n--- Welcome to Password Manager ---\n");
            while (true) {
                System.out.println("1. Add Account");
                System.out.println("2. View All Accounts");
                System.out.println("3. Retrieve Account Password");
                System.out.println("4. Delete Account");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter account name: ");
                        String accountName = scanner.nextLine();
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        passwordManager.addAccount(accountName, username, password);
                        break;
                    case 2:
                        passwordManager.viewAllAccounts();
                        break;
                    case 3:
                        System.out.print("Enter account name to retrieve: ");
                        String retrieveAccount = scanner.nextLine();
                        passwordManager.retrievePassword(retrieveAccount);
                        break;
                    case 4:
                        System.out.print("Enter account name to delete: ");
                        String deleteAccount = scanner.nextLine();
                        passwordManager.deleteAccount(deleteAccount);
                        break;
                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting...");
        }

        scanner.close();
    }
}
