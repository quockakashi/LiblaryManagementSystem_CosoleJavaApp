package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.menu.Menu;
import libraryManagement.services.TransactionManagementService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TransactionsMenu implements Menu {
    private static final String TEXT_COMMANDS_TRANSACTION_MENU = "Please, enter the number in console to proceed"
            + System.lineSeparator()
            + "1. Borrowing Book List" + System.lineSeparator()
            + "2. All book you have borrowed" + System.lineSeparator()
            + "3. Navigate to main menu";


    @Override
    public void start() {
        printHeader();
        System.out.println(TEXT_COMMANDS_TRANSACTION_MENU);
        Menu navigateMenu = null;
        mainLoop: while (true) {
            System.out.print("User input: ");
            Scanner sc = new Scanner(System.in);
            try {
                int input = sc.nextInt();
                switch (input) {
                    case 1:
                        navigateMenu = new BorrowingBookListMenu();
                        break mainLoop;
                    case 2:
                        navigateMenu = new AllBorrowedBookMenu();
                        break mainLoop;
                    case 3:
                        navigateMenu = new MainMenu();
                        break mainLoop;
                    default:
                        System.out.println("Only 1, 2, 3 are accepted. Try again!");
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Your input is not valid, please try again!");
            }
        }
        navigateMenu.start();
    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** YOUR TRANSACTIONS *****");
    }
}
