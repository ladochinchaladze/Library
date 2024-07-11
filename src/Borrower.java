import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Borrower implements Serializable {
    private String name;
    private List<Book> borrowedBooks;

    public Borrower(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (!book.isBorrowed()) {
            book.borrowBook();
            borrowedBooks.add(book);
        } else {
            System.out.println("This book is already borrowed.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
        } else {
            System.out.println("This book is not borrowed by this borrower.");
        }
    }

    @Override
    public String toString() {
        return name + " (Borrowed books: " + borrowedBooks.size() + ")";
    }
}
