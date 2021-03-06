/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.views;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author WINDOWS
 */
public class Startseite extends VerticalLayout implements View {
    public static final String CLASSNAME = "STARTSEITE";

    public void setUp() {

//Gesamtgröße des Bildschirms auf komplette Größe beziehen
        this.setSizeFull();
        TopPanelStartSeite panel = new TopPanelStartSeite();
        panel.setHeight("50px");
        this.addComponent(panel);
        setMargin(true);
        GridLayout layout = new GridLayout(2, 2);
        layout.setSizeFull();

        //PROJEKT-NAME
        Label label = new Label("<b> Stealthy_Alda </b>", ContentMode.HTML);
        label.setPrimaryStyleName(CLASSNAME + "-label");
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(label, 0, 0);
        layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        Label label2 = new Label("Sie suchen nach einem Job, der zu Ihnen passt? <br>" +
                "Sie sind ein Unternehmer und suchen nach <br>" + "einem Mitarbeiter?<br>" +
                "<br>" +
                "Wir finden!", ContentMode.HTML);

        label2.setPrimaryStyleName(CLASSNAME + "-label2");

        label2.setStyleName("startseite");
        layout.addComponent(label2, 1, 0);
        layout.setComponentAlignment(label2, Alignment.BOTTOM_CENTER);
        //BUTTON-LOS
        Button button = new Button("Los");
        button.setPrimaryStyleName(CLASSNAME + "-los");

        button.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.LOGIN));
        layout.addComponent(button, 1, 1);
        layout.setComponentAlignment(button, Alignment.TOP_CENTER);

        this.addComponent(layout);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();
        if (user != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);

        } else {
            this.setUp();
        }
    }
}