package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.model.dao.StudentDAO;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.ai.model.factories.BewerbungCollAtHBRSFactory;
import com.stealthyalda.services.util.Roles;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.time.LocalDate;

public class BewerbungWindow extends Window {
    private static final String WIDTH_800_PX = "800px";
    private static final String HEIGHT_120_PX = "120px";
    private final transient Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);


    public BewerbungWindow(Stellenanzeige a) {
        center();
        VerticalLayout content = new VerticalLayout();
        Label titel = new Label("<b> Bewerbung </b>", ContentMode.HTML);
        titel.setWidth(WIDTH_800_PX);

        content.addComponent(titel);
        content.setComponentAlignment(titel, Alignment.MIDDLE_CENTER);
        TextArea anschreiben = new TextArea("Anschreiben");
        anschreiben.setWidth(WIDTH_800_PX);
        anschreiben.setHeight(HEIGHT_120_PX);
        content.addComponent(anschreiben);
        content.setComponentAlignment(anschreiben, Alignment.MIDDLE_CENTER);


        TextArea erfahrung = new TextArea("Erfahrung");
        erfahrung.setHeight(HEIGHT_120_PX);
        erfahrung.setWidth(WIDTH_800_PX);
        content.addComponent(erfahrung);
        content.setComponentAlignment(erfahrung, Alignment.MIDDLE_CENTER);


        TextArea zertifikat = new TextArea("Letzter Schulabschluss, Zertifikate, etc.");
        zertifikat.setWidth(WIDTH_800_PX);
        zertifikat.setHeight(HEIGHT_120_PX);
        content.addComponent(zertifikat);
        content.setComponentAlignment(zertifikat, Alignment.MIDDLE_CENTER);


        HorizontalLayout button = new HorizontalLayout();
        button.setWidth(WIDTH_800_PX);

        Button zuruck = new Button("Zurück");
        zuruck.setWidth("150px");
        button.addComponent(zuruck);
        button.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);
        zuruck.addClickListener(clickEvent -> close());

        Button abschicken = new Button("Abschicken");
        abschicken.addClickListener(clickEvent -> {
            Student s = StudentDAO.getInstance().getStudent(user.getEmail());
            BewerbungCollAtHBRSDTO bewerbung = (BewerbungCollAtHBRSDTO) new BewerbungCollAtHBRSFactory().create();
            bewerbung.setStudent(s);
            bewerbung.setStellenanzeige((StellenanzeigeDTO) a);
            bewerbung.setAnschreiben(anschreiben.getValue());
            bewerbung.setErfahrung(erfahrung.getValue());
            bewerbung.setZertifikat(zertifikat.getValue());
            bewerbung.setDatum(LocalDate.now());
            ConfirmBewerbung window = new ConfirmBewerbung((StellenanzeigeDTO) a, bewerbung, s);
            UI.getCurrent().addWindow(window);
        });
        abschicken.setWidth("150px");
        button.addComponent(abschicken);
        button.setComponentAlignment(abschicken, Alignment.MIDDLE_RIGHT);

        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

        VerticalLayout main = new VerticalLayout();
        main.setWidth("1000px");

        main.addComponent(content);
        main.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

        this.setContent(main);


    }
}
