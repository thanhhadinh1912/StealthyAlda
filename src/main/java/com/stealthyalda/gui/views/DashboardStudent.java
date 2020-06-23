package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.SucheEinfach;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.SearchService;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.KontoVerwaltung;
import com.stealthyalda.gui.components.ProfilVerwaltenStudent;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.Views;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardStudent extends Studis {
    public void setUp() throws DatabaseException {
        Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        this.addComponent(new TopPanel(user));

        HorizontalLayout horizon = new HorizontalLayout();
        final ComboBox<String> jobsearch = new ComboBox<>();
        jobsearch.setPlaceholder("(Stellen, Projekte, Kompetenz oder Firmenname) ");
        jobsearch.setWidth("800px");
        SearchService searchService = new SearchService();
        jobsearch.setDataProvider(searchService::fetch, searchService::count);
        horizon.addComponent(jobsearch);
        horizon.setComponentAlignment(jobsearch, Alignment.MIDDLE_LEFT);

        final Button buttonsearch = new Button("Jobs finden!");
        buttonsearch.setWidth("200px");
        buttonsearch.addClickListener(clickEvent ->  {
            List<StellenanzeigeDTO> liste = SucheEinfach.getInstance().getStellenanzeigeByLocationOrJobTitelOrUnternehment(jobsearch.getValue(), "");
            VerticalLayout ergebnisse = new Suchseite().printergebnis(liste);
            this.addComponent(ergebnisse);
        });
        horizon.addComponent(buttonsearch);
        horizon.setComponentAlignment(buttonsearch, Alignment.MIDDLE_RIGHT);
        if(jobsearch.getValue() == null) {
            this.addComponent(horizon);
            this.setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
            // Create the accordion
            final Accordion accordion = new Accordion();
            final Layout tab1 = new VerticalLayout();
            Label news = new Label("News");
            Label bewerber = new Label("Zuletzt angesehene Stellenanzeigen");
            tab1.addComponent(news);
            tab1.addComponent(bewerber);
            accordion.addTab(tab1, "Dashboard");
            final Layout tab2 = new ProfilVerwaltenStudent(user);
            accordion.addTab(tab2, "Profil verwalten");

            final Layout tab3 = new VerticalLayout();
            accordion.addTab(tab3, "Bewerbungen");

            final Layout tab4 = new KontoVerwaltung(user);
            accordion.addTab(tab4, "Konto");

            accordion.setWidth("1200px");
            this.addComponent(accordion);
            this.setComponentAlignment(accordion, Alignment.MIDDLE_CENTER);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (isLoggedIn()) {
            try {
                this.setUp();
            } catch (DatabaseException ex) {
                Logger.getLogger(DashboardStudent.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }


    }
}
