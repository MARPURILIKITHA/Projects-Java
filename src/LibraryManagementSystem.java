import java.util.*;

class Book {
    String title;
    boolean isIssued;

    Book(String title) {
        this.title = title;
        this.isIssued = false;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private Map<String, List<Book>> issuedBooks = new HashMap<>();

    void addBook(String title) {
        books.add(new Book(title));
        System.out.println("Book added: " + title);
    }

    void issueBook(String title, String user) {
        for (Book book : books) {
            if (book.title.equals(title) && !book.isIssued) {
                book.isIssued = true;
                issuedBooks.computeIfAbsent(user, k -> new ArrayList<>()).add(book);
                System.out.println("Book issued: " + title + " to " + user);
                return;
            }
        }
        System.out.println("Book not available or already issued.");
    }

    void returnBook(String title, String user) {
        if (issuedBooks.containsKey(user)) {
            List<Book> userBooks = issuedBooks.get(user);
            for (Book book : userBooks) {
                if (book.title.equals(title)) {
                    book.isIssued = false;
                    userBooks.remove(book);
                    System.out.println("Book returned: " + title + " from " + user);
                    return;
                }
            }
        }
        System.out.println("No record of this book being issued to " + user);
    }

    void generateReport() {
        System.out.println("Library Report:");
        for (Book book : books) {
            System.out.println(book.title + " - " + (book.isIssued ? "Issued" : "Available"));
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        String command;

        while (true) {
            System.out.print("Enter command (add/issue/return/report/exit): ");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    library.addBook(title);
                    break;
                case "issue":
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String user = scanner.nextLine();
                    library.issueBook(title, user);
                    break;
                case "return":
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    user = scanner.nextLine();
                    library.returnBook(title, user);
                    break;
                case "report":
                    library.generateReport();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}