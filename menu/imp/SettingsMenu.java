package libraryManagement.menu.imp;

import libraryManagement.Main;
import libraryManagement.config.ApplicationContext;
import libraryManagement.menu.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SettingsMenu implements Menu {
    private static final String TEXT_COMMAND_SETTINGS_MENU = "Enter the number in the console to proceed: "
            + System.lineSeparator()
            + "1. Change username" + System.lineSeparator()
            + "2. Change email" + System.lineSeparator()
            + "3. Change password" + System.lineSeparator()
            + "4. Navigate to main menu";

    @Override
    public void start() {
        printHeader();
        System.out.println(TEXT_COMMAND_SETTINGS_MENU);
        Menu navigateMenu = null;
        mainLoop: while (true) {
            System.out.print("User input: ");
            Scanner sc = new Scanner(System.in);
            try {
                int input = sc.nextInt();
                switch (input) {
                    case 1:
                        navigateMenu = new UsernameSettingMenu();
                        break mainLoop;
                    case 2:
                        navigateMenu = new EmailSettingMenu();
                        break mainLoop;
                    case 3:
                        navigateMenu = new PasswordSettingMenu();
                        break mainLoop;
                    case 4:
                        navigateMenu = new MainMenu();
                        break mainLoop;
                    default:
                        System.out.println("Only 1, 2, 3, 4 are accepted. Try again!");
                        continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Your input is not valid, please try again!");
            }
            navigateMenu.start();
        }
    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** SETTINGS MENU *****");
    }
}
