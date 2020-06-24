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
import com.stealthyalda.services.util.Uploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.textfieldformatter.NumeralFieldFormatter;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class ProfilVerwaltenArbeitgeber extends VerticalLayout {
    private static final String PX1000 = "1000px";

    public ProfilVerwaltenArbeitgeber(Benutzer user) {
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
        String nameUnternehmen = current.getUnternehmen();
        name.setPlaceholder("Name des Unternehmens");
        if (nameUnternehmen.length() != 0) name.setValue(nameUnternehmen);
        name.setWidth("580px");
        name.setHeight("60px");
        logoandname.addComponent(name);
        logoandname.setComponentAlignment(name, Alignment.BOTTOM_CENTER);
        logoandname.setComponentAlignment(logo, Alignment.TOP_LEFT);


        logoandname.addComponent(x);
        logoandname.setComponentAlignment(x, Alignment.BOTTOM_RIGHT);

        main.addComponent(logoandname);

        HorizontalLayout beschreibungandkontakt = new HorizontalLayout();
        beschreibungandkontakt.setWidth(PX1000);

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
                print.append((i + 1) + ". " + s.getTitel() + "\n");
            }
        }
        stellenanzeige.setValue(String.valueOf(print));
        stellenanzeige.setReadOnly(true);
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
        kontaktandadresse.setComponentAlignment(kontakte, Alignment.TOP_RIGHT);

        Label platz = new Label("&nbsp;", ContentMode.HTML);
        kontaktandadresse.addComponent(platz);


        // split
        HorizontalLayout anfahrt = new HorizontalLayout();
        TextField strasse = new TextField();
        Adresse addrUnternehmen = AdresseDAO.getInstance().getAdresse(current.getId());
        String strasseUnternehmen = addrUnternehmen.getStrasse();
        strasse.setPlaceholder("Strassenname des Unternehmens");
        if (strasseUnternehmen.length() != 0) strasse.setValue(strasseUnternehmen);
        strasse.setWidth("580px");
        strasse.setHeight("60px");


        TextField hausNummer = new TextField();
        String strNr = addrUnternehmen.getHausnummer();
        hausNummer.setPlaceholder("Hausnummer des Unternehmens");
        if (strNr.length() != 0) hausNummer.setValue(strNr);
        hausNummer.setWidth("580px");
        hausNummer.setHeight("60px");


        TextField plz = new TextField();
        new NumeralFieldFormatter("", "", 1).extend(plz);
        int plzUnt = addrUnternehmen.getPlz();
        plz.setPlaceholder("Postleitzahl des Unternehmens");
        if (plzUnt != 0) plz.setValue(String.valueOf(plzUnt));
        plz.setWidth("580px");
        plz.setHeight("60px");

        TextField ort = new TextField();
        String ortUnt = addrUnternehmen.getOrt();
        plz.setPlaceholder("Ort des Unternehmens");
        if (ortUnt.length() != 0) ort.setValue("Bitte eingeben!");
        plz.setWidth("580px");
        plz.setHeight("60px");

        anfahrt.addComponents(strasse, hausNummer, plz, ort);
        // end split

        kontaktandadresse.addComponent(anfahrt);

        bottom.addComponent(kontaktandadresse);
        bottom.setComponentAlignment(kontaktandadresse, Alignment.TOP_RIGHT);
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
            company.setAdresse(new Adresse(strasse.getValue(), Integer.parseInt(plz.getValue()), hausNummer.getValue(), ort.getValue()));
            // TODO: implement unnecessary updates when nothing has changed
            pc.updateProfileUnternehmen(company, new Adresse());
        });

    }
}
