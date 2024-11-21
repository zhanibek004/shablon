import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("C# in Depth", "Jon Skeet", "Programming", "123", 5));
        library.addBook(new Book("Clean Code", "Robert Martin", "Programming", "456", 2));
        library.addBook(new Book("Design Patterns", "GoF", "Software Engineering", "789", 4));
        library.addBook(new Book("Cooking with Kuro", "Kuro Kodoku", "Cooking", "101112", 2));
        library.addBook(new Book("Creating a Particle Engine", "Acerola", "Game Development", "131415", 1));

        library.registerUser(new Reader(1, "Zhanibek", "Khan", "T001", "no@gmail.com"));
        library.registerUser(new Reader(2, "Adil", "Muratov", "T002", "nah@gmail.com"));
        library.registerUser(new Reader(3, "Kuro", "Kodoku", "T003", "none@gmail.com"));
        library.registerUser(new Reader(4, "Jngu", "Null", "T004", "null@gmail.com"));

        library.borrowBook(1, "123");
        library.borrowBook(2, "123");
        library.borrowBook(3, "131415");
        library.borrowBook(4, "131415");

        library.returnBook(1, "123");
        library.returnBook(2, "123");
        library.returnBook(3, "131415");

        library.generateReport();
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        logAction("Book added: " + book.getTitle());
    }

    public void registerUser(User user) {
        users.add(user);
        logAction("User registered: " + user.getName() + " (" + user.getEmail() + ")");
    }

    public void borrowBook(int userId, String isbn) {
        User user = findUserById(userId);
        Book book = findBookByIsbn(isbn);

        if (user instanceof Reader && book != null && book.isAvailable()) {
            loans.add(new Loan(book, (Reader) user));
            book.borrowCopy();
            System.out.println(user.getName() + " borrowed the book: " + book.getTitle());
        }
    }

    public void returnBook(int userId, String isbn) {
        User user = findUserById(userId);
        Book book = findBookByIsbn(isbn);

        if (user instanceof Reader && book != null) {
            Loan loan = findActiveLoan((Reader) user, book);
            if (loan != null) {
                loan.completeLoan();
                book.returnCopy();
                System.out.println(user.getName() + " returned the book: " + book.getTitle());
            }
        }
    }

    public void generateReport() {
        System.out.println("\n--- Library Report ---");
        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book.getDetails());
        }

        System.out.println("\nLoans:");
        for (Loan loan : loans) {
            System.out.println(loan.getDetails());
        }
    }

    private User findUserById(int userId) {
        return users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
    }

    private Book findBookByIsbn(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    private Loan findActiveLoan(Reader reader, Book book) {
        return loans.stream()
                .filter(l -> l.getReader().equals(reader) && l.getBook().equals(book))
                .findFirst()
                .orElse(null);
    }

    private void logAction(String message) {
        try (FileWriter writer = new FileWriter("library_log.txt", true)) {
            writer.write(LocalDateTime.now() + ": " + message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log: " + e.getMessage());
        }
    }
}

class Book {
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private int totalCopies;
    private int availableCopies;

    public Book(String title, String author, String genre, String isbn, int totalCopies) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void borrowCopy() {
        if (isAvailable()) {
            availableCopies--;
        } else {
            System.out.println("Book not available: " + title);
        }
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    public String getDetails() {
        return title + " by " + author + " (Genre: " + genre + ", ISBN: " + isbn + ", Available: " + availableCopies + ")";
    }
}

class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public User(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }
}

class Reader extends User {
    private String ticketNumber;

    public Reader(int id, String firstName, String lastName, String ticketNumber, String email) {
        super(id, firstName, lastName, email);
        this.ticketNumber = ticketNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }
}

class Loan {
    private Book book;
    private Reader reader;
    private LocalDateTime loanDate;

    public Loan(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.loanDate = LocalDateTime.now();
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public void completeLoan() {
        System.out.println("Loan completed for: " + book.getTitle());
    }

    public String getDetails() {
        return reader.getName() + " borrowed " + book.getTitle() + " on " + loanDate;
    }
}
