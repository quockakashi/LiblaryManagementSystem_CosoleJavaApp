package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.menu.Menu;
import libraryManagement.services.MemberManagementService;

import java.util.Scanner;

public class PasswordSettingMenu implements Menu {

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printHeader();
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.print("New password: ");
        password = sc.next();
        context.getLoggedMember().setPassword(password);
        System.out.println("Change email successfully!");
        new MainMenu().start();

    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** PASSWORD SETTING *****");
    }
}
