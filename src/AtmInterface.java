import java.util.ArrayList;
import java.util.Scanner;

public class AtmInterface{
    private static double balance = 1000.00; 
    private static ArrayList<String> miniStatement = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        if (pin == 1234) { 
            System.out.println("Account authorized!\n");
            atmMenu(scanner);
        } else {
            System.out.println("Incorrect PIN. Exiting...");
        }
        scanner.close();
    }

    private static void atmMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("1. View Available Balance");
            System.out.println("2. Withdraw Amount");
            System.out.println("3. Deposit Amount");
            System.out.println("4. View Mini Statement");
            System.out.println("5. Exit");
            System.out.print("\nEnter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewBalance();
                    break;
                case 2:
                    withdrawAmount(scanner);
                    break;
                case 3:
                    depositAmount(scanner);
                    break;
                case 4:
                    viewMiniStatement();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private static void viewBalance() {
        System.out.printf("Available Balance: $%.2f\n\n", balance);
    }

    private static void withdrawAmount(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= balance) {
            System.out.print("Confirm (y/n)? ");
            char confirm = scanner.next().charAt(0);
            if (confirm == 'y' || confirm == 'Y') {
                balance -= amount;
                miniStatement.add("Withdrawn: $" + amount);
                System.out.printf("Collect the cash: $%.2f\n\n", amount);
            } else {
                System.out.println("Withdrawal canceled.\n");
            }
        } else {
            System.out.println("Insufficient balance.\n");
        }
    }

    private static void depositAmount(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        System.out.print("Confirm (y/n)? ");
        char confirm = scanner.next().charAt(0);
        if (confirm == 'y' || confirm == 'Y') {
            balance += amount;
            miniStatement.add("Deposited: $" + amount);
            System.out.printf("Successfully deposited: $%.2f\n\n", amount);
        } else {
            System.out.println("Deposit canceled.\n");
        }
    }

    private static void viewMiniStatement() {
        System.out.println("Mini Statement:");
        if (miniStatement.isEmpty()) {
            System.out.println("No transactions available.\n");
        } else {
            for (String transaction : miniStatement) {
                System.out.println(transaction);
            }
            System.out.println();
        }
    }
}