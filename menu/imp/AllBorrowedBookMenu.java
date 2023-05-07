package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.entities.Transaction;
import libraryManagement.menu.Menu;
import libraryManagement.services.TransactionManagementService;

import java.util.List;
import java.util.Scanner;

public class AllBorrowedBookMenu implements Menu {
    private ApplicationContext context;
    private TransactionManagementService transactionSer;

    {
        context = ApplicationContext.getInstance();
        transactionSer = TransactionManagementService.getInstance();
    }


    @Override
    public void start() {
        printHeader();
        List<Transaction> transactions = transactionSer.allTransactionsByMember(context.getLoggedMember().getId());
        if(transactions == null) {
            System.out.println("You have not borrow any book.");
        } else {
            System.out.println(transactions);
        }
        while (true) {
            String input;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter 'menu' to navigate to main menu: ");
            input = sc.next();
            if(input.equals("menu")) {
                break;
            }
        }
        new MainMenu().start();
    }

    @Override
    public void printHeader() {
        System.out.println("\t\t****** ALL TRANSACTION ******");
    }
}
