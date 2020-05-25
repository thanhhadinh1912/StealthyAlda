package com.stealthyalda.gui.ui;

import javax.servlet.annotation.WebServlet;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.views.Loginseite;
import com.stealthyalda.gui.views.Registerseite;
import com.stealthyalda.gui.views.Suchseite;
import com.stealthyalda.services.util.Views;
import com.vaadin.annotations.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Title("Stealthy_Alda")
@PreserveOnRefresh
public class MyUI extends UI {

    private Benutzer benutzer = null;

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        System.out.println("LOG: neues UI-Objekt erzeugt, Session-ID" + VaadinSession.getCurrent().toString());

        Navigator navi = new Navigator(this,this);
        navi.addView(Views.MAIN, Suchseite.class);
        navi.addView(Views.LOGIN, Loginseite.class);
        navi.addView(Views.REGISTER, Registerseite.class);

        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);

    }

    public MyUI getMyUI(){
        return (MyUI) UI.getCurrent();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
