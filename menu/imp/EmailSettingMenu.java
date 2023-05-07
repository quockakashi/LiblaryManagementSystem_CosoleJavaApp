package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.menu.Menu;
import libraryManagement.services.MemberManagementService;

import java.util.Scanner;

public class EmailSettingMenu implements Menu {
    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printHeader();
        String email;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("New email: ");
            email = sc.next();
            if (MemberManagementService.getInstance().isUniqueEmail(email)) {
                break;
            } else {
                System.out.println("This email is used, please try again");
            }
        }
        context.getLoggedMember().setEmail(email);
        System.out.println("Change email successfully!");
        new MainMenu().start();

    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** EMAIL SETTING *****");
    }
}
