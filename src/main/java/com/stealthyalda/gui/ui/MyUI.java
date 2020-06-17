package com.stealthyalda.gui.ui;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.views.*;
import com.stealthyalda.services.util.Views;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    private transient Benutzer benutzer = null;

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        String sess = VaadinSession.getCurrent().toString();
        Logger.getLogger(MyUI.class.getName()).log(Level.INFO, sess);

        Navigator navi = new Navigator(this, this);
        navi.addView(Views.MAIN, Suchseite.class);
        navi.addView(Views.LOGIN, Loginseite.class);
        navi.addView(Views.REGISTER, Registerseite.class);
        navi.addView(Views.PASSWORTVERGESSEN, Passwortvergessenseite.class);
        navi.addView(Views.STARTSEITE, Startseite.class);
        navi.addView(Views.REGISTERFUERSTUDENT, RegisterseiteFuerStudent.class);
        navi.addView(Views.REGISTERFUERARBEITGEBER, RegisterseiteFuerArbeitgeber.class);
        navi.addView(Views.REGWEITERS, RegWeiterStudent.class);
        navi.addView(Views.REGWEITERA, RegWeiterArbeitgeber.class);
        navi.addView(Views.DASHBOARDS, DashboardStudent.class);
        navi.addView(Views.DASHBOARDA, DashboardArbeitgeber.class);
        navi.addView(Views.STELLENANZEIGE, Stellenanzeige.class);

        UI.getCurrent().getNavigator().navigateTo(Views.STARTSEITE);

    }

    public MyUI getMyUI() {
        return (MyUI) UI.getCurrent();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
