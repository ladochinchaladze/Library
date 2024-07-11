import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private Author author;
    private boolean isBorrowed;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return title + " by " + author.getName() + (isBorrowed ? " (Borrowed)" : "");
    }
}
