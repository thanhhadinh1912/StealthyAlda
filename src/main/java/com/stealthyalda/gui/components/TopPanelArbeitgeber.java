package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.gui.windows.ProfilVerwaltenArbeitgeber;
import com.stealthyalda.gui.windows.ProfilVerwaltenStudent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;


public class TopPanelArbeitgeber extends HorizontalLayout {
    private final Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();


    public TopPanelArbeitgeber() {
        this.setSizeFull();

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda1.png"));
        Image logo = new Image("", resource);

        GridLayout gridTop = new GridLayout(8, 1);
        gridTop.setSizeFull();

        gridTop.addComponent(logo, 0, 0);
        Button buttonFuerStudent = new Button("Fuer Studenten");

        buttonFuerStudent.addStyleName(ValoTheme.BUTTON_LINK);

        buttonFuerStudent.addClickListener(event -> {
            ProfilVerwaltenStudent window = new ProfilVerwaltenStudent(user);
            UI.getCurrent().addWindow(window);
        });
        gridTop.addComponent(buttonFuerStudent, 5, 0);

        Button buttonFuerArbeitgeber = new Button("Fuer Arbeitgeber");
        buttonFuerArbeitgeber.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFuerArbeitgeber.addClickListener(event -> {
            ProfilVerwaltenArbeitgeber window2 = new ProfilVerwaltenArbeitgeber(user);
            UI.getCurrent().addWindow(window2);
        });
        gridTop.addComponent(buttonFuerArbeitgeber, 6, 0);


        Button buttonAbmelden = new Button("Abmelden");
        buttonAbmelden.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAbmelden.addClickListener(event -> LoginControl.logoutUser());
        gridTop.addComponent(buttonAbmelden, 7, 0);
        gridTop.setComponentAlignment(buttonFuerStudent, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonFuerArbeitgeber, Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonAbmelden, Alignment.MIDDLE_RIGHT);

        this.addComponent(gridTop);


    }

}