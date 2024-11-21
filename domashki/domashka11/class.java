import java.util.*;
import java.util.stream.Collectors;

public class LibrarySystem {
    public static void main(String[] args) {
        Book book1 = new Book("C# in Depth", "Jon Skeet", "123", 5);
        Book book2 = new Book("Clean Code", "Robert Martin", "456", 2);
        Book book3 = new Book("Design Patterns", "GoF", "789", 4);
        Book book4 = new Book("Cooking with Kuro", "Kuro Kodoku", "101112", 2);
        Book book5 = new Book("Creating a particle engine", "Acerola", "131415", 1);

        BookCategory programmingCategory = new BookCategory("Programming");
        BookCategory cookingCategory = new BookCategory("Cooking");
        BookCategory graphicsProgrammingCategory = new BookCategory("Graphics Programming");

        Librarian librarian = new Librarian(1, "Bakontea", "Senior Librarian");
        librarian.addBook(book1, programmingCategory);
        librarian.addBook(book2, programmingCategory);
        librarian.addBook(book3, programmingCategory);
        librarian.addBook(book4, cookingCategory);
        librarian.addBook(book5, graphicsProgrammingCategory);

        System.out.println(programmingCategory.getDetails());
        System.out.println(cookingCategory.getDetails());
        System.out.println(graphicsProgrammingCategory.getDetails());

        Reader reader = new Reader(1, "Zhanibek", "no@gmail.com", 3);

        Loan loan1 = new Loan(book1, reader);
        loan1.issueLoan(programmingCategory);
        Loan loan2 = new Loan(book2, reader);
        loan2.issueLoan(programmingCategory);
        Loan loan3 = new Loan(book3, reader);
        loan3.issueLoan(programmingCategory);

        Reader reader2 = new Reader(2, "Adil", "nah@gmail.com", 2);
        Loan loan4 = new Loan(book4, reader2);
        loan4.issueLoan(cookingCategory);

        List<Book> searchResults = graphicsProgrammingCategory.searchBooks("Acerola");
        System.out.println("\nSearch Results for 'Acerola':");
        for (Book book : searchResults) {
            System.out.println(book.getDetails());
        }

        loan1.completeLoan(programmingCategory);
        loan2.completeLoan(programmingCategory);
        loan3.completeLoan(programmingCategory);

        System.out.println(programmingCategory.getDetails());
        System.out.println(cookingCategory.getDetails());
        System.out.println(graphicsProgrammingCategory.getDetails());
    }
}

interface ILibraryItem {
    String getDetails();
}

class Book implements ILibraryItem {
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;

    public Book(String title, String author, String isbn, int totalCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void borrowCopy() {
        if (isAvailable()) {
            availableCopies--;
        } else {
            System.out.println(title + " is not available.");
        }
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    public String getDetails() {
        return title + " by " + author + " (ISBN: " + isbn + ") - copies Available: " + availableCopies + "/" + totalCopies;
    }
}

class BookCategory implements ILibraryItem {
    private String name;
    private List<Book> availableBooks = new ArrayList<>();
    private List<Book> notAvailableBooks = new ArrayList<>();

    public BookCategory(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        if (book.isAvailable()) {
            availableBooks.add(book);
        } else {
            notAvailableBooks.add(book);
        }
    }

    public void removeBook(Book book) {
        availableBooks.remove(book);
        notAvailableBooks.remove(book);
    }

    public void updateBookStatus(Book book) {
        if (book.isAvailable()) {
            notAvailableBooks.remove(book);
            if (!availableBooks.contains(book)) {
                availableBooks.add(book);
            }
        } else {
            availableBooks.remove(book);
            if (!notAvailableBooks.contains(book)) {
                notAvailableBooks.add(book);
            }
        }
    }

    public List<Book> searchBooks(String searchTerm) {
        return availableBooks.stream().filter(book -> book.getDetails().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public String getDetails() {
        StringBuilder details = new StringBuilder("\nCategory: " + name + "\nAvailable Books:\n");
        for (Book book : availableBooks) {
            details.append("- ").append(book.getDetails()).append("\n");
        }
        details.append("Rented Books:\n");
        for (Book book : notAvailableBooks) {
            details.append("- ").append(book.getDetails()).append("\n");
        }
        return details.toString();
    }
}

class Reader {
    private int id;
    private String name;
    private String email;
    private int maxBooksAllowed;
    private int booksRentedCount = 0;

    public Reader(int id, String name, String email, int maxBooksAllowed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.maxBooksAllowed = maxBooksAllowed;
    }

    public void borrowBook(Book book, BookCategory category) {
        if (booksRentedCount >= maxBooksAllowed) {
            System.out.println(name + " cannot borrow more than " + maxBooksAllowed + " books at a time.");
            return;
        }
        if (book.isAvailable()) {
            book.borrowCopy();
            category.updateBookStatus(book);
            booksRentedCount++;
            System.out.println(name + " borrowed " + book.getDetails() + ".");
        } else {
            System.out.println(book.getDetails() + " is not available.");
        }
    }

    public void returnBook(Book book, BookCategory category) {
        book.returnCopy();
        category.updateBookStatus(book);
        booksRentedCount--;
        System.out.println(name + " returned " + book.getDetails() + ".");
    }
}

class Librarian {
    private int id;
    private String name;
    private String position;

    public Librarian(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public void addBook(Book book, BookCategory category) {
        category.addBook(book);
        System.out.println(name + " added " + book.getDetails() + " to " + category.getDetails() + ".");
    }

    public void removeBook(Book book, BookCategory category) {
        category.removeBook(book);
        System.out.println(name + " removed " + book.getDetails() + " from " + category.getDetails() + ".");
    }
}

class Loan {
    private Book book;
    private Reader reader;
    private Date loanDate;
    private Date returnDate;

    public Loan(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.loanDate = new Date();
        this.returnDate = null;
    }

    public void issueLoan(BookCategory category) {
        if (book.isAvailable()) {
            reader.borrowBook(book, category);
            System.out.println("Loan issued: " + reader.name + " borrowed " + book.getDetails() + " on " + loanDate + ".");
        } else {
            System.out.println(book.getDetails() + " is not available for loan.");
        }
    }

    public void completeLoan(BookCategory category) {
        if (returnDate == null) {
            reader.returnBook(book, category);
            returnDate = new Date();
            System.out.println("Loan completed: " + reader.name + " returned " + book.getDetails() + " on " + returnDate + ".");
        } else {
            System.out.println("Loan already completed for " + book.getDetails() + ".");
        }
    }
}
