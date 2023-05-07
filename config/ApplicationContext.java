package libraryManagement.config;

import libraryManagement.entities.Cart;
import libraryManagement.entities.Member;
import libraryManagement.menu.Menu;

public class ApplicationContext {
    private static ApplicationContext instance;

    private Member loggedMember;
    private Cart sessionCart;
    private Menu mainMenu;


    private ApplicationContext() {
    }


    public void setLoggedMember(Member loggedMember) {
        if(sessionCart != null) {
            sessionCart.clear();
        }

        this.loggedMember = loggedMember;
    }

    public Member getLoggedMember() {
        return loggedMember;
    }

    public void setMainMenu(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public Menu getMainMenu() {
        return mainMenu;
    }

    public Cart getSessionCart() {
        if(sessionCart == null)
            sessionCart = new Cart();
        return sessionCart;
    }

    public static ApplicationContext getInstance() {
        if(instance == null) {
            instance = new ApplicationContext();
        }

        return instance;
    }
}
