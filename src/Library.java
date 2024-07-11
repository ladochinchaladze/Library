import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private List<Book> books;
    private List<Borrower> borrowers;

    public Library() {
        books = new ArrayList<>();
        borrowers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    public void saveLibrary(String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
        }
    }


    public  void clearData(){
        books.clear();
        borrowers.clear();
    }

    public static Library loadLibrary(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Library) in.readObject();
        }
    }

    public static void clearLibrary(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
                out.reset();
        }
    }

}
