package libraryManagement.services;

import libraryManagement.entities.Book;
import libraryManagement.entities.Member;
import libraryManagement.entities.Transaction;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;

public class TransactionManagementService {

    private static TransactionManagementService instance;
    private List<Transaction> transactions;

    private TransactionManagementService() {
        transactions = new ArrayList<>();
    }

    public static TransactionManagementService getInstance() {
        if(instance == null)
            instance = new TransactionManagementService();
        return instance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Book book, Member member) {
        transactions.add(new Transaction(book, member.getId()));
        book.setStatus();
    }
    public void addTransactions(List<Book> books, Member idMember) {
        books.forEach(book -> {
            transactions.add(new Transaction(book, idMember.getId()));
            book.setStatus();
        });
    }

    public List<Transaction> isBorrowingByMember(int memberId) {
        return transactions.stream()
                .filter(transaction -> transaction != null
                && transaction.getIdMember() == memberId
                && transaction.getReturnDate() == null)
                .toList();
    }

    public List<Transaction> allTransactionsByMember(int memberId) {
        return transactions.stream()
                .filter(transaction -> transaction != null
                && transaction.getIdMember() == memberId)
                .toList();
    }

    public Transaction findBorrowingTransactionById(String idBook) {
        return transactions.stream()
                .filter(transaction -> transaction != null
                        && transaction.getBook().getIdBook().equalsIgnoreCase(idBook)
                        && transaction.getReturnDate() == null)
                .findFirst().orElse(null);
    }

}
