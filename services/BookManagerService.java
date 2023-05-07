package libraryManagement.services;

import libraryManagement.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookManagerService {

    private static BookManagerService instance;
    private List<Book> books;


    {
        initBook();
    }

    private void initBook() {
        books = new ArrayList<>();
        books.add(new Book("1", "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book("2", "1984", "George Orwell"));
        books.add(new Book("3", "Brave New World", "Aldous Huxley"));
        books.add(new Book("4", "The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book("5", "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book("6", "Jane Eyre", "F. Scott Fitzgerald"));
        books.add(new Book("7", "Little Women", "F. Scott Fitzgerald"));
        books.add(new Book("8", "Brave New World", "F. Scott Fitzgerald"));
        books.add(new Book("9", "Frankestein", "F. Scott Fitzgerald"));
        books.add(new Book("10", "The Color Purple", "F. Scott Fitzgerald"));
        books.add(new Book("11", "The Sound and the Fury", "F. Scott Fitzgerald"));
        books.add(new Book("12", "Lolita", "F. Scott Fitzgerald"));
    }

    public List<Book> getBooks() {
        return books.stream()
                .filter(Objects::nonNull)
                .toList();
    }


    public List<Book> getAvailableBook() {
        return books.stream()
                .filter(book -> book != null && book.isAvailable())
                .toList();
    }

    public static BookManagerService getInstance() {
        if(instance == null)
            instance = new BookManagerService();
        return instance;
    }

    public Book findBookById(String idBook) {
        if(books == null)
            return null;
        return books.stream()
                .filter(book -> book  != null && book.getIdBook().equalsIgnoreCase(idBook))
                .findFirst().orElse(null);
    }
    boolean isUniqueId(String idBook) {
        var book = findBookById(idBook);
        return (book) != null;
    }
    public void addBook(Book book) {
        if(books == null) {
            books = new ArrayList<>();
        }
        if(isUniqueId(book.getIdBook()))
            return;
        books.add(book);
    }

    public void clear() {
        books = new ArrayList<>();
    }
}
