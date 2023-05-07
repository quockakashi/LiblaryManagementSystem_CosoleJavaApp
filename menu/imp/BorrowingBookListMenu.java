package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.entities.Book;
import libraryManagement.entities.Transaction;
import libraryManagement.menu.Menu;
import libraryManagement.services.TransactionManagementService;

import java.util.List;
import java.util.Scanner;

public class BorrowingBookListMenu implements Menu {
    private ApplicationContext context;
    private TransactionManagementService transactionSer;

    {
        context = ApplicationContext.getInstance();
        transactionSer = TransactionManagementService.getInstance();
    }


    @Override
    public void start() {
        printHeader();
        List<Transaction> transactions = transactionSer.isBorrowingByMember(context.getLoggedMember().getId());
        if(transactions == null || transactions.size() == 0) {
            System.out.println("These is no borrowing book.");
        } else {
            System.out.println(transactions);
        }

        mainLoop: while (true) {
            System.out.print("Enter No of book that you want to return" + System.lineSeparator() +
                    "Or enter 'all' to borrow entire book in your list"+ System.lineSeparator() +
                    "Or enter 'menu' to navigate to main menu");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if(input.equals("menu")) {
                break mainLoop;
            }else if(input.equals("all")) {
                if(transactions == null || transactions.size() == 0) {
                    System.out.println("Sorry, there is no book in your cart. Try again!");
                } else {
                    transactions.forEach(Transaction::returnBook);
                    System.out.println("You returned all book");
                }
                System.out.print("Do you want navigate to main menu [y/n]: ");
                if(sc.next().equalsIgnoreCase("y")) {
                    break mainLoop;
                } else {
                    continue;
                }
            } else {
                try {
                    int number = Integer.parseInt(input);
                    Transaction transaction = transactions.get(number);
                    transaction.returnBook();
                    System.out.println(transaction.getBook().getTitle() + "is returned successfully");
                } catch (NumberFormatException e) {
                    System.out.println("Your input is not valid, try again!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You need to choose the transaction in current list. Try again");
                }
            }
        }
        new MainMenu().start();

    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** BORROWING BOOKS ******");
    }
}
