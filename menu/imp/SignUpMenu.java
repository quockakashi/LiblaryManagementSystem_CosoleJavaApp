package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.entities.Member;
import libraryManagement.menu.Menu;
import libraryManagement.services.MemberManagementService;


import java.util.Scanner;

public class SignUpMenu implements Menu {
    private ApplicationContext context;
    private MemberManagementService memberSer;

    {
        context = ApplicationContext.getInstance();
        memberSer = MemberManagementService.getInstance();
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("First name: ");
        String firstName = sc.next();
        System.out.print("Last name: ");
        String lastName = sc.next();
        String email;
        while (true) {
            System.out.print("Email: ");
            email = sc.next();
            if(memberSer.isUniqueEmail(email))
                break;
            else {
                System.out.print("This email is used! Try again!");
            }
        }
        System.out.print("Password: ");
        String password = sc.next();
        Member newMember = new Member(firstName, lastName, email, password);
        memberSer.register(newMember);
        context.setLoggedMember(newMember);

        System.out.println("Hello " + newMember.getFirstName() + " " + newMember.getLastName()
        + ", your account is created successfully");

        new MainMenu().start();
    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** SIGN UP *****");
    }
}
