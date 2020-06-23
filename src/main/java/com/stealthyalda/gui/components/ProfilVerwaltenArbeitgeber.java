package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.ProfilArbeitgeberControl;
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
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

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

        // Create the beschreibungDesUnternehmens
        TextArea beschreibungDesUnternehmens = new TextArea("Kurze Beschreibung des Unternehmens");
        String beschreibung = current.getBeschreibung();
        if (beschreibung != null) {
            beschreibungDesUnternehmens.setValue(current.getBeschreibung());
        }
        beschreibungDesUnternehmens.setHeight("90px");
        beschreibungDesUnternehmens.setWidth(PX1000);
        main.addComponent(beschreibungDesUnternehmens);
        main.setComponentAlignment(beschreibungDesUnternehmens, Alignment.TOP_LEFT);

        HorizontalLayout bottom = new HorizontalLayout();
        bottom.setHeight("240px");


        TextArea stellenanzeige = new TextArea("Stellenanzeige");
        StringBuilder print = new StringBuilder();
        ProfilArbeitgeberControl profilArbeitgeberControl = new ProfilArbeitgeberControl();
        List<StellenanzeigeDTO> stellenanzeigen = profilArbeitgeberControl.getStellenanzeige(nameUnternehmen);
        if(stellenanzeigen!=null){
            for(int i=0; i<stellenanzeigen.size();i++){
                StellenanzeigeDTO s = stellenanzeigen.get(i);
                print.append(Integer.toString(i+1) + ". " + s.getTitel() + "\n");
            }
        }
        stellenanzeige.setValue(String.valueOf(print));
        stellenanzeige.setWidth("650px");
        stellenanzeige.setHeight("200px");
        bottom.addComponent(stellenanzeige);
        bottom.setComponentAlignment(stellenanzeige,Alignment.BOTTOM_LEFT);


        VerticalLayout kontaktandadresse = new VerticalLayout();
        kontaktandadresse.setWidth("300px");
        kontaktandadresse.setHeight("200px");

        TextArea kontakte = new TextArea();
        kontakte.setValue("Tel " + current.getTelefonnummer());
        kontakte.setHeight("75px");
        kontakte.setWidth("300px");
        kontaktandadresse.addComponent(kontakte);
        kontaktandadresse.setComponentAlignment(kontakte,Alignment.TOP_RIGHT);

        Label platz = new Label("&nbsp;", ContentMode.HTML);
        kontaktandadresse.addComponent(platz);

        TextArea anfahrt = new TextArea("Adresse");
        Adresse a = AdresseDAO.getInstance().getAdresse(user.getId());
        if (a != null) {
            anfahrt.setValue(a.toString());
        }
        anfahrt.setWidth("300px");
        anfahrt.setHeight("80px");
        kontaktandadresse.addComponent(anfahrt);

        bottom.addComponent(kontaktandadresse);
        bottom.setComponentAlignment(kontaktandadresse,Alignment.TOP_RIGHT);
        main.addComponent(bottom);


        Button speichern = new Button("Speichern");
        main.addComponent(speichern);
        main.setComponentAlignment(speichern, Alignment.BOTTOM_CENTER);
        main.setHeight("700px");

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
