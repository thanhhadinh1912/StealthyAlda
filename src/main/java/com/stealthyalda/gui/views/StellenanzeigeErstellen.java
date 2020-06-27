/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.views;

import com.stealthyalda.ai.model.dtos.Anforderung;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.gui.windows.ConfirmStellenanzeige;
import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.Views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author WINDOWS
 */
public class StellenanzeigeErstellen extends VerticalLayout implements View {
    final transient Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
    final private String WIDTH = "750px";
    final private String WIDTHB = "150px";

    public void setUp() {

        this.addComponent(new TopPanel(user));
        Panel main = new Panel();
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("1000px");

        VerticalLayout mainlayout = new VerticalLayout();
        mainlayout.setWidth("800px");
        Label label = new Label("<b> Stellenanzeige hochladen </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        label.addStyleName(ValoTheme.LABEL_H1);
        mainlayout.addComponent(label);
        TextField titel = new TextField("Titel");
        titel.setWidth(WIDTH);
        mainlayout.addComponent(titel);


        TextArea beschreibung = new TextArea("Beschreibung");
        beschreibung.setWidth(WIDTH);
        mainlayout.addComponent(beschreibung);

        TextField ort = new TextField("Ort");
        ort.setWidth(WIDTH);
        mainlayout.addComponent(ort);

        TextArea anforderung = new TextArea("Anforderung");
        anforderung.setWidth(WIDTH);
        mainlayout.addComponent(anforderung);

        HorizontalLayout datestatus = new HorizontalLayout();
        datestatus.setWidth(WIDTH);

        // Create a DateField with the default style
        DateField date = new DateField("Datum");
        date.setWidth("350px");
        datestatus.addComponent(date);

        ComboBox status = new ComboBox("Status");
        status.setItems("Offen", "Pausiert", "Geschlossen");
        status.setWidth("350px");
        datestatus.addComponent(status);
        datestatus.setComponentAlignment(status, Alignment.MIDDLE_RIGHT);
        mainlayout.addComponent(datestatus);

        HorizontalLayout bottom = new HorizontalLayout();
        bottom.setWidth("900px");
        Button zuruck = new Button("Zurück");
        zuruck.addClickListener(clickEvent -> UI.getCurrent().getNavigator().navigateTo(Views.DASHBOARDA));
        zuruck.setWidth(WIDTHB);
        bottom.addComponent(zuruck);
        bottom.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);

        Button abschicken = new Button("Abschicken");
        abschicken.addClickListener(clickEvent -> {
            Stellenanzeige a = new Stellenanzeige();
            a.setTitel(titel.getValue());
            a.setBeschreibung(beschreibung.getValue());
            a.setDatum(date.getValue());
            a.setStatus((String) status.getValue());
            a.setOrt(ort.getValue());

            List<String> listStr = new ArrayList<>();
            List<Anforderung> list = new ArrayList<>();
            listStr.addAll(Arrays.asList(anforderung.getValue().split("\n")));
            for (int i = 0; i < listStr.size(); i++) {
                Anforderung anforderung1 = new Anforderung();
                anforderung1.setAnforderung(listStr.get(i));
                list.add(anforderung1);
            }
            a.setAnforderungs(list);
            ConfirmStellenanzeige confirm = new ConfirmStellenanzeige(a);

            UI.getCurrent().addWindow(confirm);

        });
        abschicken.setWidth(WIDTHB);
        bottom.addComponent(abschicken);
        bottom.setComponentAlignment(abschicken, Alignment.MIDDLE_RIGHT);

        layout.addComponent(mainlayout);
        layout.setComponentAlignment(mainlayout, Alignment.MIDDLE_CENTER);
        layout.addComponent(bottom);
        layout.setComponentAlignment(bottom, Alignment.BOTTOM_CENTER);

        main.setContent(layout);
        main.setSizeUndefined();
        this.addComponent(main);
        this.setComponentAlignment(main, Alignment.MIDDLE_CENTER);

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo(Views.STARTSEITE);

        } else {
            this.setUp();
        }
    }
}
