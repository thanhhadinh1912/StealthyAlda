package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.ProfilArbeitgeberControl;
import com.stealthyalda.ai.model.dao.AdresseDAO;
import com.stealthyalda.ai.model.dao.ArbeitgeberDAO;
import com.stealthyalda.ai.model.dtos.Adresse;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.util.Uploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import org.vaadin.textfieldformatter.NumeralFieldFormatter;

import java.io.File;
import java.util.List;

public class ProfilVerwaltenArbeitgeber extends ProfilVerwalten {
    private static final String PX1000 = "1000px";

    public ProfilVerwaltenArbeitgeber(Benutzer user) {
        super(user);
        Arbeitgeber current = ArbeitgeberDAO.getInstance().getArbeitgeber(user.getEmail());
        VerticalLayout main = new VerticalLayout();
        HorizontalLayout logoandname = new HorizontalLayout();

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown_profil.png"));
        Image logo = new Image("", resource);
        logoandname.addComponent(logo);
        Uploader x = new Uploader();
        x.init("basic");
        TextField name = new TextField();
        name.setPlaceholder("Name des Unternehmens");
        if (!isAdmin) {
            String nameUnternehmen = current.getUnternehmen();
            if (nameUnternehmen.length() != 0) name.setValue(nameUnternehmen);
        }
        name.setWidth("580px");
        name.setHeight("50px");
        logoandname.addComponent(name);
        logoandname.setComponentAlignment(name, Alignment.MIDDLE_CENTER);


        logoandname.addComponent(x);
        logoandname.setComponentAlignment(x, Alignment.MIDDLE_RIGHT);

        main.addComponent(logoandname);

        HorizontalLayout beschreibungandkontakt = new HorizontalLayout();
        beschreibungandkontakt.setWidth(PX1000);

        // Create the beschreibungDesUnternehmens
        TextArea beschreibungDesUnternehmens = new TextArea("Kurze Beschreibung des Unternehmens");
        if (!isAdmin) {
            String beschreibung = current.getBeschreibung();
            if (beschreibung != null) {
                beschreibungDesUnternehmens.setValue(current.getBeschreibung());
            }
        }
        beschreibungDesUnternehmens.setHeight("90px");
        beschreibungDesUnternehmens.setWidth(PX1000);
        main.addComponent(beschreibungDesUnternehmens);
        main.setComponentAlignment(beschreibungDesUnternehmens, Alignment.TOP_LEFT);


        HorizontalLayout bottom = new HorizontalLayout();


        TextArea stellenanzeige = new TextArea("Stellenanzeige");
        stellenanzeige.setPlaceholder("Stellenanzeige");
        if (!isAdmin) {
            StringBuilder print = new StringBuilder();
            ProfilArbeitgeberControl profilArbeitgeberControl = new ProfilArbeitgeberControl();
            List<StellenanzeigeDTO> stellenanzeigen = profilArbeitgeberControl.getStellenanzeige(current.getUnternehmen());
            if (stellenanzeigen != null) {
                for (int i = 0; i < stellenanzeigen.size(); i++) {
                    StellenanzeigeDTO s = stellenanzeigen.get(i);
                    print.append(i + 1).append(". ").append(s.getTitel()).append("\n");
                }
            }
            stellenanzeige.setValue(String.valueOf(print));
        }
        stellenanzeige.setReadOnly(true);
        stellenanzeige.setWidth("645px");
        stellenanzeige.setHeight("250px");
        bottom.addComponent(stellenanzeige);
        bottom.setComponentAlignment(stellenanzeige, Alignment.MIDDLE_LEFT);


        VerticalLayout kontaktandadresse = new VerticalLayout();

        TextArea kontakte = new TextArea("Kontakt");
        if (!isAdmin) kontakte.setValue("Tel " + current.getTelefonnummer());
        kontakte.setHeight("50px");
        kontakte.setWidth("300px");
        kontaktandadresse.addComponent(kontakte);

        Label ad = new Label("Adresse");
        kontaktandadresse.addComponent(ad);


        TextField strasse = new TextField();
        TextField hausNummer = new TextField();
        TextField plz = new TextField();
        new NumeralFieldFormatter("", "", 1).extend(plz);
        TextField ort = new TextField();


        HorizontalLayout strassehnr = new HorizontalLayout();

        strasse.setPlaceholder("Strasse");

        if (!isAdmin) {
            Adresse addrUnternehmen = AdresseDAO.getInstance().getAdresse(current.getId());
            String strasseUnternehmen = addrUnternehmen.getStrasse();
            if (strasseUnternehmen.length() != 0) strasse.setValue(strasseUnternehmen);
            String strNr = addrUnternehmen.getHausnummer();
            String ortUnt = addrUnternehmen.getOrt();
            if (strNr.length() != 0) hausNummer.setValue(strNr);
            int plzUnt = addrUnternehmen.getPlz();
            if (plzUnt != 0) plz.setValue(String.valueOf(plzUnt));
            if (ortUnt.length() != 0) ort.setValue(ortUnt);
            else ort.setValue("Bitte eingeben!");

        }
        strasse.setWidth("150px");
        strasse.setHeight("60px");
        strassehnr.addComponent(strasse);


        hausNummer.setPlaceholder("Hausnummer");
        hausNummer.setCaption("Hausnummer");
        hausNummer.setWidth("130px");
        hausNummer.setHeight("60px");
        strassehnr.addComponent(hausNummer);


        HorizontalLayout plzort = new HorizontalLayout();
        plz.setCaption("Postleitzahl");
        plz.setPlaceholder("Postleitzahl");
        plz.setWidth("150px");
        plz.setHeight("60px");

        ort.setPlaceholder("Ort ");

        ort.setWidth("130px");
        ort.setHeight("60px");

        plzort.addComponent(plz);
        plzort.addComponent(ort);

        kontaktandadresse.addComponent(strassehnr);
        kontaktandadresse.addComponent(plzort);
        // end split


        bottom.addComponent(kontaktandadresse);
        bottom.setComponentAlignment(kontaktandadresse, Alignment.MIDDLE_RIGHT);

        main.addComponent(bottom);


        Button speichern = new Button("Speichern");
        main.addComponent(speichern);
        main.setComponentAlignment(speichern, Alignment.TOP_CENTER);

        this.addComponent(main);
        this.setHeight("800px");
        this.setComponentAlignment(main, Alignment.TOP_CENTER);
        // TODO add validators/binders
        speichern.addClickListener(event -> {
            ProfilArbeitgeberControl pc = new ProfilArbeitgeberControl();
            String unternehmensName = name.getValue();
            String beschreibungKurz = beschreibungDesUnternehmens.getValue();
            String telefonNummer = kontakte.getValue();


            UnternehmenDTO company = new UnternehmenDTO();
            company.setBeschreibung(beschreibungKurz);
            company.setUnternehmen(unternehmensName);
            company.setTelefonnummer(telefonNummer);
            company.setArbeitgeberId(current.getArbeitgeberId());
            company.setAdresse(new Adresse(strasse.getValue(), Integer.parseInt(plz.getValue()), hausNummer.getValue(), ort.getValue()));
            company.getAdresse().setAdresseID(user.getAdresseId());


            // TODO: implement unnecessary updates when nothing has changed
            pc.updateArbeitgeberprofil(company);
        });

    }
}
