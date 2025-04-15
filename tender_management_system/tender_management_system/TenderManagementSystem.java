import java.util.Scanner;

public class TenderManagementSystem {
    private User loggedInUser;

    public TenderManagementSystem() {
    }

    public void register(String username, String password) {
        if (User.register(username, password)) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("User registration failed.");
        }
    }

    public boolean login(String username, String password) {
        loggedInUser = User.login(username, password);
        if (loggedInUser != null) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    public void createTender(String tenderId, String description) {
        if (Tender.createTender(tenderId, description)) {
            System.out.println("Tender created successfully.");
        } else {
            System.out.println("Failed to create tender.");
        }
    }

    public void placeBid(String tenderId, double amount) {
        if (loggedInUser != null) {
            if (Bid.placeBid(tenderId, loggedInUser, amount)) {
                System.out.println("Bid placed successfully.");
            } else {
                System.out.println("Failed to place bid.");
            }
        } else {
            System.out.println("Please login to place a bid.");
        }
    }

    public void viewTenders() {
        for (Tender tender : Tender.getAllTenders()) {
            System.out.println("Tender ID: " + tender.getTenderId());
            System.out.println("Description: " + tender.getDescription());
            System.out.println("Bids:");
            for (Bid bid : tender.getBids()) {
                System.out.println("User: " + bid.getUser().getUsername() + ", Amount: " + bid.getAmount());
            }
        }
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TenderManagementSystem tms = new TenderManagementSystem();

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Create Tender");
            System.out.println("4. Place Bid");
            System.out.println("5. View Tenders");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    tms.register(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    tms.login(username, password);
                    break;
                case 3:
                    if (tms.loggedInUser != null) {
                        System.out.print("Enter tender ID: ");
                        String tenderId = scanner.nextLine();
                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();
                        tms.createTender(tenderId, description);
                    } else {
                        System.out.println("Please login to create a tender.");
                    }
                    break;
                case 4:
                    if (tms.loggedInUser != null) {
                        System.out.print("Enter tender ID: ");
                         String  tenderId = scanner.nextLine();
                        System.out.print("Enter bid amount: ");
                        double amount = scanner.nextDouble();
                        tms.placeBid(tenderId, amount);
                    } else {
                        System.out.println("Please login to place a bid.");
                    }
                    break;
                case 5:
                    tms.viewTenders();
                    break;
                case 6:
                    tms.logout();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
