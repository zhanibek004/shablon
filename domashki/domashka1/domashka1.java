import java.util.ArrayList;
import java.util.List;

public class Librarian {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Christine", "Stephen King", "1234567890", 1);
        Book book2 = new Book("The Call of Cthulhu", "H.P. Lovecraft", "0987654321", 1);

        library.addBook(book1);
        library.addBook(book2);

        Reader reader1 = new Reader("Kuro", 1);
        Reader reader2 = new Reader("Kodoku", 2);

        library.registerReader(reader1);
        library.registerReader(reader2);

        library.lendBook(reader1, book1);
        library.lendBook(reader2, book2);

      
        library.lendBook(reader2, book2);

        library.returnBook(reader1, book1);
        library.returnBook(reader2, book2);

        library.returnBook(reader2, book2);
    }
}

class Book {
    private String bookName;
    private String author;
    private String ISBN;
    private int copiesAmount;

    public Book(String bookName, String author, String ISBN, int copiesAmount) {
        this.bookName = bookName;
        this.author = author;
        this.ISBN = ISBN;
        this.copiesAmount = copiesAmount;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getCopiesAmount() {
        return copiesAmount;
    }

    public void setCopiesAmount(int copiesAmount) {
        this.copiesAmount = copiesAmount;
    }
}

class Reader {
    private String name;
    private int ID;
    private List<Book> borrowedBooks;

    public Reader(String name, int ID) {
        this.name = name;
        this.ID = ID;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}

class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        books = new ArrayList<>();
        readers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("'" + book.getBookName() + "', '" + book.getAuthor() + "', '" + book.getISBN() + "', '" + book.getCopiesAmount() + "' has been added to the list");
    }

    public void removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            System.out.println("'" + book.getBookName() + "', '" + book.getAuthor() + "', '" + book.getISBN() + "', '" + book.getCopiesAmount() + "' has been removed from the list");
        } else {
            System.out.println("'" + book.getBookName() + "', '" + book.getAuthor() + "', '" + book.getISBN() + "', '" + book.getCopiesAmount() + "' has not been found in the list");
        }
    }

    public void registerReader(Reader reader) {
        readers.add(reader);
        System.out.println("Reader '" + reader.getName() + "' has been registered");
    }

    public void lendBook(Reader reader, Book book) {
        if (book.getCopiesAmount() > 0) {
            reader.getBorrowedBooks().add(book);
            book.setCopiesAmount(book.getCopiesAmount() - 1);
            System.out.println("'" + book.getBookName() + "', '" + book.getISBN() + "' has been lent to '" + reader.getName() + "', '" + reader.getID() + "'");
        } else {
            System.out.println("Sorry, '" + book.getBookName() + "' is currently not available for lending.");
        }
    }

    public void returnBook(Reader reader, Book book) {
        if (reader.getBorrowedBooks().contains(book)) {
            reader.getBorrowedBooks().remove(book);
            book.setCopiesAmount(book.getCopiesAmount() + 1);
            System.out.println("'" + book.getBookName() + "', '" + book.getISBN() + "' has been returned by '" + reader.getName() + "', '" + reader.getID() + "'");
        } else {
            System.out.println("Reader '" + reader.getName() + "', '" + reader.getID() + "' did not borrow the book '" + book.getBookName() + "', '" + book.getISBN() + "'");
        }
    }
}
