package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.menu.Menu;

import java.util.Scanner;

public class UsernameSettingMenu implements Menu {
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printHeader();
        Scanner sc = new Scanner(System.in);
        System.out.print("Name first name: ");
        String firstName = sc.next();
        System.out.print("Name last name: ");
        String lastName = sc.next();

        context.getLoggedMember().setFirstName(firstName);
        context.getLoggedMember().setLastName(lastName);
        System.out.println("Change username successfully!");
        new MainMenu().start();
    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** USERNAME SETTING *****");
    }
}
