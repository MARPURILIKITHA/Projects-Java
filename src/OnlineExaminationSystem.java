import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void updateProfile(String newPassword) {
        this.password = newPassword;
    }
}

public class OnlineExaminationSystem {
    private static User user;
    private static boolean loggedIn = false;
    private static final int TIMER_DURATION = 20; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        user = new User("student", "password"); 

        while (true) {
            System.out.println("1. Login\n2. Exit");
            int choice = scanner.nextInt();
            if (choice == 1) {
                login(scanner);
            } else {
                break;
            }
        }
        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        if (user.getUsername().equals(username) && user.checkPassword(password)) {
            loggedIn = true;
            System.out.println("Login successful!");
            examSession(scanner);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void examSession(Scanner scanner) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Submitting your exam.");
                logout();
            }
        }, TIMER_DURATION * 1000);

        String[][] questions = {
            {"What is the capital of France?", "A) Berlin", "B) Madrid", "C) Paris", "D) Rome"},
            {"What is 2 + 2?", "A) 3", "B) 4", "C) 5", "D) 6"}
        };

        for (int i = 0; i < questions.length; i++) {
            System.out.println((i + 1) + ". " + questions[i][0]);
            for (int j = 1; j < questions[i].length; j++) {
                System.out.println(questions[i][j]);
            }
            System.out.print("Select an answer (A/B/C/D): ");
            String answer = scanner.next().toUpperCase();
            System.out.println("You selected: " + answer);
        }

        logout();
        timer.cancel();
    }

    private static void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully.");
    }
}