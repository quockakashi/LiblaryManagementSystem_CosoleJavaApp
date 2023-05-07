package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.menu.Menu;

import java.util.Scanner;

public class MainMenu implements Menu {
    private static final String MENU_COMMAND = "menu";
    private static final String MAIN_MENU_FOR_LOGGED_OUT_MEMBER = "Please, enter the number in console to proceed"
            + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator()
            + "2. Sign In";


    private static final String MAIN_MENU_FOR_LOGGED_IN_MEMBER = "Please, enter the number in console to proceed"
            + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator()
            + "2. Sign Out" + System.lineSeparator()
            + "3. Books List " + System.lineSeparator()
            + "4. Your book cart" + System.lineSeparator()
            + "5. Your transaction" + System.lineSeparator()
            + "6. Setting";

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        if(context.getMainMenu() == null)
            context.setMainMenu(this);

        Menu navitigateMenu = null;
        mainLoop: while (true) {
            boolean isLoggedIn = (context.getLoggedMember()!=null);
            printHeader();
            Scanner sc = new Scanner(System.in);
            System.out.print("User input: ");
            int input;
            try {
                input = sc.nextInt();
            } catch (Exception e) {
                input = 7;
                sc.nextLine();
            }
            if(isLoggedIn) {
                switch (input) {
                    case 1:
                        navitigateMenu = new SignUpMenu();
                        break mainLoop;
                    case 2:
                        navitigateMenu = new LogOutMenu();
                        break  mainLoop;
                    case 3:
                        navitigateMenu = new BookListMenu();
                        break mainLoop;
                    case 4:
                        navitigateMenu = new BookCartMenu();
                        break mainLoop;
                    case 5:
                        navitigateMenu = new TransactionsMenu();
                        break mainLoop;
                    case 6:
                        navitigateMenu = new SettingsMenu();
                        break mainLoop;
                    default:
                        System.out.println("Only 1, 2, 3, 4, 5, 6 are accepted, please try again");
                        continue;
                }
            } else {
                switch (input) {
                    case 1:
                        navitigateMenu = new SignUpMenu();
                        break mainLoop;
                    case 2:
                        navitigateMenu = new SignInMenu();
                        break mainLoop;
                    default:
                        System.out.println("Only 1, 2 are accepted, please try again");
                        continue;
                }
            }

        }
        navitigateMenu.start();
    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** MAIN MENU *****");
        if (context.getLoggedMember() == null) {
            System.out.println(MAIN_MENU_FOR_LOGGED_OUT_MEMBER);
        } else  {
            System.out.println(MAIN_MENU_FOR_LOGGED_IN_MEMBER);
        }
        System.out.println("\t\t======================");
    }
}
