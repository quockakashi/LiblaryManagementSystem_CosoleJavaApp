package libraryManagement.menu.imp;

import libraryManagement.config.ApplicationContext;
import libraryManagement.menu.Menu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class LogOutMenu implements Menu {
    private ApplicationContext context;
    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        printHeader();
        System.out.println("Are you want to log out? " +
                "\nPress enter to logout" +
                "\nOr after 5s, your account will be logged out");
        AtomicBoolean isLogOut = new AtomicBoolean(false);
        Thread thread = new Thread(() -> {
            int i = 5;
            while (i != 0 && !isLogOut.get()) {
                System.out.println(i);
                i--;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    return;
                }
            }
            if(i == 0) {
                isLogOut.set(true);
            }
        });
        thread.start();
        var bfs = new BufferedInputStream(System.in);
        while (!isLogOut.get()) {
            try {
                if(System.in.available() > 0 && bfs.read() == '\n') {
                    isLogOut.set(true);
                    thread.interrupt();
                    break;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        if(isLogOut.get()) {
            context.setLoggedMember(null);
        }
        new MainMenu().start();


    }

    @Override
    public void printHeader() {
        System.out.println("\t\t***** LOG OUT ******");
    }
}
