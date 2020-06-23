package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.exceptions.ProfilUnternehmenControl;
import com.stealthyalda.ai.model.dao.AdresseDAO;
import com.stealthyalda.ai.model.dao.ArbeitgeberDAO;
import com.stealthyalda.ai.model.dtos.Adresse;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.util.ImageUploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;
import java.time.LocalDate;

public class ProfilVerwaltenArbeitgeber extends VerticalLayout {
    private static final String PX1000 = "1000px";

    public ProfilVerwaltenArbeitgeber(Benutzer user) {
        Arbeitgeber current = ArbeitgeberDAO.getInstance().getArbeitgeber(user.getEmail());
        VerticalLayout main = new VerticalLayout();
        HorizontalLayout logoandname = new HorizontalLayout();
        ImageUploader receiver = new ImageUploader();
        Upload upload = new Upload("", receiver);
        upload.addSucceededListener(receiver);
        upload.setButtonCaption("Logo hochladen");
        upload.setImmediateMode(true);
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_profil.png"));
        Image logo = new Image("", resource);
        logoandname.addComponent(logo);

        TextField name = new TextField();
        String nameUnternehmen = current.getUnternehmen();
        name.setPlaceholder("Name des Unternehmens");
        if (nameUnternehmen.length() != 0) name.setValue(nameUnternehmen);
        name.setWidth("580px");
        name.setHeight("60px");
        logoandname.addComponent(name);
        logoandname.setComponentAlignment(name, Alignment.BOTTOM_CENTER);
        logoandname.setComponentAlignment(logo, Alignment.TOP_LEFT);


        logoandname.addComponent(upload);
        logoandname.setComponentAlignment(upload, Alignment.BOTTOM_RIGHT);

        main.addComponent(logoandname);

        HorizontalLayout beschreibungandkontakt = new HorizontalLayout();
        beschreibungandkontakt.setWidth(PX1000);

        // Create the beschreibungDesUnternehmens
        TextArea beschreibungDesUnternehmens = new TextArea("Kurze Beschreibung des Unternehmens");
        String beschreibung = current.getBeschreibung();
        if (beschreibung != null) {
            beschreibungDesUnternehmens.setValue(current.getBeschreibung());
        }
        beschreibungDesUnternehmens.setHeight("75px");
        beschreibungDesUnternehmens.setWidth("650px");
        beschreibungandkontakt.addComponent(beschreibungDesUnternehmens);
        beschreibungandkontakt.setComponentAlignment(beschreibungDesUnternehmens, Alignment.TOP_LEFT);

        TextArea kontakte = new TextArea("Shortlink mit Icons");
        kontakte.setValue(current.getTelefonnummer());
        kontakte.setHeight("75px");
        kontakte.setWidth("300px");
        beschreibungandkontakt.addComponent(kontakte);
        beschreibungandkontakt.setComponentAlignment(kontakte, Alignment.TOP_RIGHT);
        main.addComponent(beschreibungandkontakt);

        TextArea stellenanzeige = new TextArea("Stellenanzeige");
        stellenanzeige.setWidth(PX1000);
        stellenanzeige.setHeight("200px");
        main.addComponent(stellenanzeige);

        // TODO: split address in str, nr & plz
        TextArea anfahrt = new TextArea("Adresse");
        Adresse a = AdresseDAO.getInstance().getAdresse(current.getId());
        if (a != null) {
            anfahrt.setValue(a.toString());
        }
        anfahrt.setWidth(PX1000);
        anfahrt.setHeight("100px");
        main.addComponent(anfahrt);

        Button speichern = new Button("Speichern");
        main.addComponent(speichern);
        main.setComponentAlignment(speichern, Alignment.BOTTOM_CENTER);

        this.addComponent(main);
        this.setHeight("700px");
        this.setComponentAlignment(main, Alignment.TOP_CENTER);
        // TODO add validators/binders
        speichern.addClickListener(event -> {
            ProfilUnternehmenControl pc = new ProfilUnternehmenControl();
            String unternehmensName = name.getValue();
            String beschreibungKurz = beschreibungDesUnternehmens.getValue();
            String telefonNummer = kontakte.getValue();
            String stellenAnzeige = stellenanzeige.getValue();
            // TODO save address too
            UnternehmenDTO company = new UnternehmenDTO();
            StellenanzeigeDTO jobOffer = new StellenanzeigeDTO();
            jobOffer.setArbeitgeber(unternehmensName);
            jobOffer.setBeschreibung(stellenAnzeige);
            jobOffer.setDatum(LocalDate.now());

            company.setBeschreibung(beschreibungKurz);
            company.setUnternehmen(unternehmensName);
            company.setTelefonnummer(telefonNummer);
            company.setArbeitgeberId(current.getArbeitgeberId());
            pc.updateProfileUnternehmen(company, new Adresse());
        });

    }
}
