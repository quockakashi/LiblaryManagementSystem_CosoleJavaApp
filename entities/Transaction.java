package libraryManagement.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class    Transaction {

    private static final int DEFAULT_NUMBER_OF_BORROW_DAYS = 14;
    private Book book;
    private int idMember;
    private final LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate = null;

    public Transaction(Book book, int idMember) {
        this.book = book;
        this.idMember = idMember;
        this.borrowDate = LocalDateTime.now();
        this.dueDate = borrowDate.plus(14, ChronoUnit.DAYS).with(LocalTime.MAX);
    }

    public Book getBook() {
        return book;
    }

    public int getIdMember() {
        return idMember;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public String formatDateTime(LocalDateTime localDateTime) {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        return formatter.format(localDateTime);
    }

    public boolean isOverDue() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(dueDate) && !book.isAvailable();
    }

    public void returnBook() {
        if(returnDate != null)
            return;
        returnDate = LocalDateTime.now();
        book.setStatus();
    }

    @Override
    public String toString() {
        return "Id Book: " + book.getIdBook() + System.lineSeparator()
                + "title:  " + book.getTitle() + System.lineSeparator()
                + "id member: " + idMember + System.lineSeparator()
                + "Borrow date: " + formatDateTime(borrowDate) + System.lineSeparator()
                + "Return date: " + (returnDate == null ? "no" : formatDateTime(returnDate))
                + (isOverDue() ? "THIS BOOK IS OVERDUE, PLEASE RETURN IF YOU CAN" : "");
    }



}
