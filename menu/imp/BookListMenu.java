package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.entities.Book;
import libraryManagement.menu.Menu;
import libraryManagement.menu.imp.MainMenu;
import libraryManagement.services.BookManagerService;
import libraryManagement.services.TransactionManagementService;

import java.util.List;
import java.util.Scanner;

public class BookListMenu implements Menu {
    private ApplicationContext context;
    private BookManagerService bookSer;

    {
        context = ApplicationContext.getInstance();
        bookSer = BookManagerService.getInstance();
    }

    @Override
    public void start() {
        printHeader();
        List<Book> books = bookSer.getAvailableBook();
        if(books.size() == 0) {
            System.out.println("There is no available book to borrow right now");
        } else {
            for(int i = 0; i < books.toArray().length; i++) {
                System.out.println("No: " + i
                + System.lineSeparator()
                        + books.get(i));
            }
        }
        Menu navigate = null;
        mainLoop: while (true) {
            System.out.print("Enter No of book that you want to borrow to add in you cart" + System.lineSeparator() +
                    "Or enter 'menu' to navigate to main menu, 'cart' to your cart: ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if(input.equals("menu")) {
                navigate = new MainMenu();
                break mainLoop;
            } else if(input.equals("cart")) {
                navigate = new BookCartMenu();
                break mainLoop;
            } else {
                try {
                    int number = Integer.parseInt(input);
                    Book book = books.get(number);
                    context.getSessionCart().addBookToCart(book);
                    System.out.println(book.getTitle() + " added to your cart successfully");
                } catch (NumberFormatException e) {
                    System.out.println("Your input is not valid, try again!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You need to choose the book in current list. Try again");
                }
            }
        }
        navigate.start();
    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** BOOK LIST *****");
    }
}
