package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.exceptions.BewerbungControl;
import com.stealthyalda.ai.model.dao.StudentDAO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Bewerbung;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.util.Roles;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

public class BewerbungWindow extends Window {
    private String WIDTH = "800px";
    private String HEIGHT = "120px";
    private Benutzer user  = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);


    public BewerbungWindow(Stellenanzeige a){
        center();
        VerticalLayout content = new VerticalLayout();
        Label titel = new Label("<b> Bewerbung </b>", ContentMode.HTML);
        titel.setWidth(WIDTH);

        content.addComponent(titel);
        content.setComponentAlignment(titel, Alignment.MIDDLE_CENTER);
        TextArea anschreiben = new TextArea("Anschreiben");
        anschreiben.setWidth(WIDTH);
        anschreiben.setHeight(HEIGHT);
        content.addComponent(anschreiben);
        content.setComponentAlignment(anschreiben,Alignment.MIDDLE_CENTER);


        TextArea erfahrung = new TextArea("Erfahrung");
        erfahrung.setHeight(HEIGHT);
        erfahrung.setWidth(WIDTH);
        content.addComponent(erfahrung);
        content.setComponentAlignment(erfahrung,Alignment.MIDDLE_CENTER);


        TextArea zertifikat = new TextArea("Letzter Schulabschluss, Zertifikate, etc.");
        zertifikat.setWidth(WIDTH);
        zertifikat.setHeight(HEIGHT);
        content.addComponent(zertifikat);
        content.setComponentAlignment(zertifikat,Alignment.MIDDLE_CENTER);


        HorizontalLayout button = new HorizontalLayout();
        button.setWidth(WIDTH);

        Button zuruck = new Button("Zurück");
        zuruck.setWidth("150px");
        button.addComponent(zuruck);
        button.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);
        zuruck.addClickListener(clickEvent -> close());

        Button abschicken = new Button("Abschicken");
        abschicken.addClickListener(clickEvent -> {
            Student s = StudentDAO.getInstance().getStudent(user.getEmail());
            Bewerbung bewerbung = new Bewerbung();
            bewerbung.setMatrikelnr(s.getStudentId());
            bewerbung.setStellenanzeigeid(a.getStellenanzeigeID());
            bewerbung.setAnschreiben(anschreiben.getValue());
            bewerbung.setErfahrung(erfahrung.getValue());
            bewerbung.setZertifikat(zertifikat.getValue());
            new BewerbungControl().createbewerbung(a,bewerbung,s);
        });
        abschicken.setWidth("150px");
        button.addComponent(abschicken);
        button.setComponentAlignment(abschicken,Alignment.MIDDLE_RIGHT);

        content.addComponent(button);
        content.setComponentAlignment(button,Alignment.MIDDLE_CENTER);

        VerticalLayout main = new VerticalLayout();
        main.setWidth("1000px");

        main.addComponent(content);
        main.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        this.setContent(main);


    }
}
