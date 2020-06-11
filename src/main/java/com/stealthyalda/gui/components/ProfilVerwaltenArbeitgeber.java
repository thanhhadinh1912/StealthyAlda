package com.stealthyalda.gui.components;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

public class ProfilVerwaltenArbeitgeber  extends VerticalLayout {
    public ProfilVerwaltenArbeitgeber(){
        VerticalLayout main = new VerticalLayout();
        HorizontalLayout logoandname = new HorizontalLayout();
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_logo.png"));
        Image logo = new Image("", resource);
        logoandname.addComponent(logo);
        TextField name = new TextField();
        name.setPlaceholder("Vorname Nachname");
        name.setWidth("500px");
        name.setHeight("60px");
        logoandname.addComponent(name);
        logoandname.setComponentAlignment(name,Alignment.BOTTOM_CENTER);
        logoandname.setComponentAlignment(logo,Alignment.MIDDLE_LEFT);
        main.addComponent(logoandname);
        //this.setComponentAlignment(logoandname, Alignment.TOP_LEFT);

        HorizontalLayout beschreibungandkontakt = new HorizontalLayout();
        beschreibungandkontakt.setWidth("1000px");
        // Create the area
        TextArea area = new TextArea("Kurze Beschreibung des Unternehmens");
        area.setHeight("75px");
        area.setWidth("650px");
        beschreibungandkontakt.addComponent(area);
        beschreibungandkontakt.setComponentAlignment(area,Alignment.TOP_LEFT);
        TextArea kontakte = new TextArea("Shortlink mit Icons");
        kontakte.setHeight("75px");
        kontakte.setWidth("300px");
        beschreibungandkontakt.addComponent(kontakte);
        beschreibungandkontakt.setComponentAlignment(kontakte, Alignment.TOP_RIGHT);
        main.addComponent(beschreibungandkontakt);

        TextArea stellenanzeige = new TextArea("Stellenanzeige");
        stellenanzeige.setWidth("1000px");
        stellenanzeige.setHeight("200px");
        main.addComponent(stellenanzeige);

        TextArea anfahrt = new TextArea("Anfahrt");
        anfahrt.setWidth("600px");
        anfahrt.setHeight("100px");
        main.addComponent(anfahrt);

        this.addComponent(main);
        this.setComponentAlignment(main, Alignment.TOP_CENTER);


    }
}
