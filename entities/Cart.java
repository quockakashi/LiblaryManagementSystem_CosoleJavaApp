package libraryManagement.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private static final int MAXIMUM_BOOK_IN_CART = 15;
    private List<Book> books;

    public Cart() {
        books = new ArrayList<>();
    }

    public void addBookToCart(Book book) {
        if(book == null || !book.isAvailable())
            throw new IllegalArgumentException("Can't add this book");
        if(books.size() >= MAXIMUM_BOOK_IN_CART) {
            throw new ArrayIndexOutOfBoundsException("Your cart has reached its maximum capacity of books." +
                    " Please remove some books before adding more.");
        }
        books.add(book);
    }

    public void removeBook(String idBook) {
        if(books == null || idBook == null)
            return;
        Book removeBook = books.stream()
                .filter(book -> {
                    return book != null
                            && book.getIdBook().equalsIgnoreCase(idBook);
                })
                .findFirst().orElse(null);
        books.remove(removeBook);
    }

    public List<Book> getBooks() {
        return books.stream()
                .filter(Objects::nonNull)
                .toList();
    }

    public void clear() {
        books = new ArrayList<>();
    }
}
