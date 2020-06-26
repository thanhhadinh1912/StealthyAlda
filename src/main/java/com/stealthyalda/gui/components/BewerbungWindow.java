package com.stealthyalda.gui.components;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

public class BewerbungWindow extends Window {
    private String WIDTH = "800px";
    private String HEIGHT = "120px";
    public BewerbungWindow(){
        center();
        VerticalLayout content = new VerticalLayout();
        Label titel = new Label("<b> Bewerbung </b>", ContentMode.HTML);
        content.addComponent(titel);
        TextArea anschreiben = new TextArea();
        anschreiben.setWidth(WIDTH);
        anschreiben.setHeight(HEIGHT);
        content.addComponent(anschreiben);

        TextArea erfahrung = new TextArea("Erfahrung");
        erfahrung.setHeight(HEIGHT);
        erfahrung.setWidth(WIDTH);
        content.addComponent(erfahrung);

        TextArea zertifikat = new TextArea("Letzter Schulabschluss, Zertifikate, etc.");
        zertifikat.setWidth(WIDTH);
        zertifikat.setHeight(HEIGHT);
        content.addComponent(zertifikat);

        HorizontalLayout button = new HorizontalLayout();
        button.setWidth(WIDTH);

        Button zuruck = new Button("Zurück");
        zuruck.setWidth("150px");
        button.addComponent(zuruck);

        Button abschicken = new Button("Abschicken");
        abschicken.setWidth("150px");
        button.addComponent(abschicken);

        content.addComponent(button);
        content.setComponentAlignment(button,Alignment.MIDDLE_CENTER);


        this.setContent(content);


    }
}
