package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.Views;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;


public class TopPanelStartSeite extends HorizontalLayout {
    public static final String CLASSNAME = "TopPanelSTART";
    static final String TOPPANELBUTTON = "toppanelbutton";


    public TopPanelStartSeite() {

        this.setSizeFull();
        Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda.png"));
        Image logo = new Image("", resource);
        GridLayout gridTop = new GridLayout(8, 1);
        gridTop.setSizeFull();
        logo.setPrimaryStyleName(CLASSNAME + "-logo");


        gridTop.addComponent(logo, 0, 0);
        Button buttonFuerStudent = new Button("Für Studenten");
        buttonFuerStudent.addStyleName(TOPPANELBUTTON);

        buttonFuerStudent.addStyleName(ValoTheme.BUTTON_LINK);

        buttonFuerStudent.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTERFUERSTUDENT));
        gridTop.addComponent(buttonFuerStudent, 5, 0);

        Button buttonFuerArbeitgeber = new Button("Für Unternehmen");
        buttonFuerArbeitgeber.addStyleName(TOPPANELBUTTON);
        buttonFuerArbeitgeber.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerArbeitgeber.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTERFUERARBEITGEBER));
        gridTop.addComponent(buttonFuerArbeitgeber, 6, 0);


        Button buttonAnmelden = new Button("Anmelden");
        buttonAnmelden.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAnmelden.addStyleName(TOPPANELBUTTON);

        buttonAnmelden.addClickListener(event -> {
            if (user != null) {
                LoginControl.logoutUser();
            } else {
                UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
            }
        });
        gridTop.addComponent(buttonAnmelden, 7, 0);
        gridTop.setComponentAlignment(buttonFuerStudent, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonFuerArbeitgeber, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonAnmelden, Alignment.MIDDLE_RIGHT);


        this.addComponent(gridTop);


    }

}