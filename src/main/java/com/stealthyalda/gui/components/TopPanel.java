package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.Views;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;


public class TopPanel extends HorizontalLayout {
    static final String TOPPANELBUTTON = "toppanelbutton";


    public TopPanel(Benutzer user) {

        this.setSizeFull();

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda1.png"));
        Image logo = new Image("", resource);

        GridLayout gridTop = new GridLayout(8, 1);
        gridTop.setSizeFull();

        gridTop.addComponent(logo, 0, 0);
        Button buttonFuerStudent = new Button("Für Studenten");

        buttonFuerStudent.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerStudent.addStyleName(TOPPANELBUTTON);
        buttonFuerStudent.addClickListener(clickEvent -> {
            if (user.getRole().equals("Student") || user.getRole().equals("admin")) {
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);
                UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDS);
            } else {
                Notification.show("Fehler", "Seite ist nur für Student verfügbar", Notification.Type.ERROR_MESSAGE);
            }
        });
        gridTop.addComponent(buttonFuerStudent, 5, 0);

        Button buttonFuerArbeitgeber = new Button("Für Arbeitgeber");
        buttonFuerArbeitgeber.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerArbeitgeber.addStyleName(TOPPANELBUTTON);
        buttonFuerArbeitgeber.addClickListener(clickEvent -> {
            if (user.getRole().equals("Arbeitgeber") || user.getRole().equals("admin")) {
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);
                UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDA);
            } else {
                Notification.show("Fehler", "Seite ist nur für Arbeitgeber verfügbar", Notification.Type.ERROR_MESSAGE);

            }
        });
        gridTop.addComponent(buttonFuerArbeitgeber, 6, 0);


        Button buttonAbmelden = new Button("Abmelden");
        buttonAbmelden.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAbmelden.addStyleName(TOPPANELBUTTON);
        buttonAbmelden.addClickListener(event -> LoginControl.logoutUser());
        gridTop.addComponent(buttonAbmelden, 7, 0);
        gridTop.setComponentAlignment(buttonFuerStudent, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonFuerArbeitgeber, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonAbmelden, Alignment.MIDDLE_RIGHT);

        this.addComponent(gridTop);


    }

}
