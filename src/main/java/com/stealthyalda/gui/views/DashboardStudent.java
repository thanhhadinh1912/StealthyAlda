package com.stealthyalda.gui.views;

import com.stealthyalda.gui.components.ProfilVerwaltenStudent;
import com.stealthyalda.gui.components.TopPanel;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

public class DashboardStudent extends VerticalLayout implements View {
    public void setUp() {

        this.addComponent(new TopPanel());

        HorizontalLayout horizon = new HorizontalLayout();
        final TextField jobsearch = new TextField();
        jobsearch.setPlaceholder("(Stellen, Projekte, Kompetenz oder Firmenname) ");
        jobsearch.setWidth("800px");
        horizon.addComponent(jobsearch);
        horizon.setComponentAlignment(jobsearch, Alignment.MIDDLE_LEFT);

        final Button buttonsearch = new Button("Jobs finden!");
        buttonsearch.setWidth("200px");
        horizon.addComponent(buttonsearch);
        horizon.setComponentAlignment(buttonsearch, Alignment.MIDDLE_RIGHT);

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

        final Layout tab2 = new ProfilVerwaltenStudent();
        accordion.addTab(tab2, "Profil verwalten");

        final Layout tab3 = new VerticalLayout();
        accordion.addTab(tab3, "Bewerbungen");

        final Layout tab4 = new VerticalLayout();
        accordion.addTab(tab4, "Konto");

        accordion.setWidth("1200px");
        this.addComponent(accordion);
        this.setComponentAlignment(accordion, Alignment.MIDDLE_CENTER);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();

    }
}
