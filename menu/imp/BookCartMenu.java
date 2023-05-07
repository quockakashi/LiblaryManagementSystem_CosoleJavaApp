package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.entities.Book;
import libraryManagement.menu.Menu;
import libraryManagement.services.TransactionManagementService;

import java.util.List;
import java.util.Scanner;

public class BookCartMenu implements Menu {
    private ApplicationContext context;
    private TransactionManagementService transactionSer;

    {
        context = ApplicationContext.getInstance();
        transactionSer = TransactionManagementService.getInstance();
    }
    @Override
    public void start() {
        printHeader();
        List<Book> books = context.getSessionCart().getBooks();
        if(books.size() == 0) {
            System.out.println("There is no book in your cart, please add more book in cart");
        } else {
            for(int i = 0; i < books.toArray().length; i++) {
                System.out.println("No: " + i
                        + System.lineSeparator()
                        + books.get(i));
            }
        }
        mainLoop: while (true) {
            System.out.print("Enter No of book that you want to borrow" + System.lineSeparator() +
                    "Or enter 'all' to borrow entire book in your list"+ System.lineSeparator() +
                    "Or enter 'menu' to navigate to main menu: ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if(input.equals("menu")) {
                break mainLoop;
            } else if(input.equals("all")) {
                if(books == null || books.size() == 0) {
                    System.out.println("Sorry, there is no book in your cart. Try again!");
                } else {
                    transactionSer.addTransactions(books, context.getLoggedMember());
                    System.out.println("Your borrowed all of book in you cart");
                    context.getSessionCart().clear();
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
                    Book book = books.get(number);
                    transactionSer.addTransaction(book, context.getLoggedMember());
                    System.out.println(book.getTitle() + " borrowed successfully");
                    context.getSessionCart().removeBook(book.getIdBook());
                } catch (NumberFormatException e) {
                    System.out.println("Your input is not valid, try again!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You need to choose the book in cart. Try again");
                }
            }
        }
        new MainMenu().start();
    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** BOOK CART *****");
    }
}
