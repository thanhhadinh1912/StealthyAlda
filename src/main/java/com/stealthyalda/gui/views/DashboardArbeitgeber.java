package com.stealthyalda.gui.views;


import com.stealthyalda.ai.control.ToogleRouter;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.ArbeitgeberDAO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.BenachrichtigungArbeitgeber;
import com.stealthyalda.gui.components.KontoVerwaltung;
import com.stealthyalda.gui.components.ProfilVerwaltenArbeitgeber;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.SearchArbeitgeberServiceMitBewerbung;
import com.stealthyalda.services.util.SearchArbeitgeberServiceOhneBewerbung;
import com.stealthyalda.services.util.Views;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

public class DashboardArbeitgeber extends VerticalLayout implements View {
    public void setUp() {

        Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        this.addComponent(new TopPanel(user));

        HorizontalLayout horizon = new HorizontalLayout();
        Arbeitgeber a = ArbeitgeberDAO.getInstance().getArbeitgeber(user.getEmail());
        SearchArbeitgeberServiceOhneBewerbung service = new SearchArbeitgeberServiceOhneBewerbung(a);
        ComboBox<String> search = new ComboBox<>();
        try {
            if (ToogleRouter.isEnabled("bewerbung")) {
                search.setPlaceholder("Bewerber ");
                search.setWidth("400px");
                SearchArbeitgeberServiceMitBewerbung servicea = new SearchArbeitgeberServiceMitBewerbung(a);
                search.setDataProvider(servicea::fetch, servicea::count);
                horizon.addComponent(search);


                ComboBox<String> bewerber = new ComboBox<>();
                bewerber.setPlaceholder("Stellenanzeige");
                bewerber.setWidth("400px");
                bewerber.setDataProvider(service::fetch, service::count);
                horizon.addComponent(bewerber);
            } else {
                search.setPlaceholder("(Stellenanzeige) ");
                search.setWidth("800px");
                search.setDataProvider(service::fetch, service::count);
                horizon.addComponent(search);

            }

            horizon.setComponentAlignment(search, Alignment.MIDDLE_LEFT);

            final Button buttonsearch = new Button("Suche");
            buttonsearch.setWidth("200px");
            horizon.addComponent(buttonsearch);
            horizon.setComponentAlignment(buttonsearch, Alignment.MIDDLE_RIGHT);

            this.addComponent(horizon);
            this.setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
            // Create the accordion
            final Accordion accordion = new Accordion();
            final Layout tab1 = new VerticalLayout();
            final HorizontalLayout top = new HorizontalLayout();
            Label news = new Label("News");
            top.addComponent(news);
            news.setWidth("1100px");


            Button add = new Button(VaadinIcons.PLUS);
            top.addComponent(add);
            add.addClickListener(clickEvent -> {
                ((MyUI) UI.getCurrent()).setBenutzer(user);
                UI.getCurrent().getSession().setAttribute(Roles.CURRENTUSER, user);
                UI.getCurrent().getNavigator().navigateTo(Views.STELLENANZEIGEERSTELLEN);
            });
            tab1.addComponent(top);
            if (ToogleRouter.isEnabled("bewerbung")) {

                Label n = new Label("Neue Bewerbungen sind eingegangen");
                tab1.addComponent(n);

            }

            accordion.addTab(tab1, "Dashboard");


            final Layout tab2 = new ProfilVerwaltenArbeitgeber(user);
            accordion.addTab(tab2, "Profil verwalten");

            final Layout tab3 = new BenachrichtigungArbeitgeber(user);
            accordion.addTab(tab3, "Benachrichtigungen");

            final Layout tab4 = new KontoVerwaltung(user);
            accordion.addTab(tab4, "Konto");

            accordion.setWidth("1200px");
            this.addComponent(accordion);
            this.setComponentAlignment(accordion, Alignment.MIDDLE_CENTER);
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();

    }
}
