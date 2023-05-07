package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.entities.Member;
import libraryManagement.menu.Menu;
import libraryManagement.services.MemberManagementService;


import java.util.Scanner;

public class SignInMenu implements Menu {
    private ApplicationContext context;
    private MemberManagementService memberSer;

    {
        context = ApplicationContext.getInstance();
        memberSer = MemberManagementService.getInstance();
    }

    @Override
    public void start() {
        printHeader();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        Member member= memberSer.findMember(email, password);
        if(member == null) {
            System.out.println("Your email or password may be wrong. Please check again");
        } else {
            context.setLoggedMember(member);
            System.out.println("Welcome " + member.getFirstName() + " " + member.getLastName() + "!");
        }

        new MainMenu().start();

    }

    @Override
    public void printHeader() {
        System.out.println("\t\t****** LOG IN *****");
    }
}
