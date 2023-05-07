package libraryManagement.entities;

public class Book {
    private String idBook; // Ex: CS001
    private String title;
    private String author;
    private boolean isAvailable; // true: if it is available to borrow.

    public Book(String idBook, String title, String author) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getIdBook() {
        return idBook;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setStatus() {
        isAvailable = !isAvailable;
    }

    @Override
    public String toString() {
        return "ID: " + idBook + System.lineSeparator()
                + "Title: " + title + System.lineSeparator()
                + "Author: " + author + System.lineSeparator()
                + "Status " + (isAvailable?"Available":"Not available");
    }
}
