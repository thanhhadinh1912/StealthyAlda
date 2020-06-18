package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.services.util.Views;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;


public class TopPanel extends HorizontalLayout {
    private String t = "toppanelbutton";


    public TopPanel() {
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
        buttonFuerStudent.addStyleName(t);
        buttonFuerStudent.addClickListener(event ->
        UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDS)
        );
        gridTop.addComponent(buttonFuerStudent, 5, 0);

        Button buttonFuerArbeitgeber = new Button("Für Arbeitgeber");
        buttonFuerArbeitgeber.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerArbeitgeber.addStyleName(t);
        buttonFuerArbeitgeber.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDA));
        gridTop.addComponent(buttonFuerArbeitgeber, 6, 0);


        Button buttonAbmelden = new Button("Abmelden");
        buttonAbmelden.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAbmelden.addStyleName(t);
        buttonAbmelden.addClickListener(event -> LoginControl.logoutUser());
        gridTop.addComponent(buttonAbmelden, 7, 0);
        gridTop.setComponentAlignment(buttonFuerStudent, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonFuerArbeitgeber, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonAbmelden, Alignment.MIDDLE_RIGHT);

        this.addComponent(gridTop);


    }

}