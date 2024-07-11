import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadLibrary();

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Borrower");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. List Borrowers");
            System.out.println("7. Find Books by title.");
            System.out.println("8. Find Books by author");
            System.out.println("9. Clear library");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addBorrower();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    listBooks();
                    break;
                case 6:
                    listBorrowers();
                    break;
                case 7:
                    System.out.print("Title: ");
                    String title = scanner.next();
                    scanner.nextLine(); // consume newline
                    findBookByTitle(title);
                    break;
                case 8:
                    System.out.print("Author: ");
                    String author = scanner.next();
                    scanner.nextLine(); // consume newline
                    findBookByAuthor(author);
                    break;
                case 9:
                    clearLibrary();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    saveLibrary();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        Author author = new Author(authorName);
        Book book = new Book(title, author);
        library.addBook(book);
        System.out.println("Book added.");
    }

    private static void addBorrower() {
        System.out.print("Enter borrower name: ");
        String name = scanner.nextLine();
        Borrower borrower = new Borrower(name);
        library.addBorrower(borrower);
        System.out.println("Borrower added.");
    }

    private static void borrowBook() {
        System.out.print("Enter borrower name: ");
        String borrowerName = scanner.nextLine();
        Borrower borrower = findBorrower(borrowerName);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();
        Book book = findBook(bookTitle);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        borrower.borrowBook(book);
        System.out.println("Book borrowed.");
    }

    private static void returnBook() {
        System.out.print("Enter borrower name: ");
        String borrowerName = scanner.nextLine();
        Borrower borrower = findBorrower(borrowerName);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();
        Book book = findBook(bookTitle);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        borrower.returnBook(book);
        System.out.println("Book returned.");
    }

    private static void listBooks() {
        System.out.println("Books in the library:");
        for (Book book : library.getBooks()) {
            System.out.println(book);
        }
    }

    private static void findBookByTitle(String title){
        System.out.println("Books by title:");


                library.getBooks().stream()
                .filter(b -> new String(title.toLowerCase()).equals(b.getTitle().toLowerCase()))
                .forEach(b -> System.out.println(b));
    }

    private static void findBookByAuthor(String author){
        System.out.println("Books by author:");

        library.getBooks().stream()
                .filter(b -> new String(author.toLowerCase()).equals(b.getAuthor().getName().toLowerCase()))
                .forEach(b -> System.out.println(b));
    }

    private static void listBorrowers() {
        System.out.println("Borrowers:");
        for (Borrower borrower : library.getBorrowers()) {
            System.out.println(borrower);
        }
    }

    private static void saveLibrary() {
        String fileName = new String("LibraryFile.txt");
        try {
            library.saveLibrary(fileName);
            System.out.println("Library saved.");
        } catch (IOException e) {
            System.out.println("Error saving library: " + e.getMessage());
        }
    }

    private static void loadLibrary() {
        String fileName = new String("LibraryFile.txt");
        try {
            library = Library.loadLibrary(fileName);
            System.out.println("Library loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library: " + e.getMessage());
        }
    }

    private static Borrower findBorrower(String name) {
        for (Borrower borrower : library.getBorrowers()) {
            if (borrower.getName().equalsIgnoreCase(name)) {
                return borrower;
            }
        }
        return null;
    }

    private static Book findBook(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    private static void clearLibrary() {
        String fileName = new String("LibraryFile.txt");
        try {
            Library.clearLibrary(fileName);
            library.clearData();
            System.out.println("Library cleared.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library: " + e.getMessage());
        }
    }
}
