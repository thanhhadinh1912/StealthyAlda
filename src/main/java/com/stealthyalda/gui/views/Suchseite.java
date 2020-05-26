package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.SucheEinfach;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;


import java.util.List;

public class Suchseite extends VerticalLayout implements View{

    private  int anzahl = 0;
    private Stellenanzeige selected = null;

    public void setUp() {

        this.addComponent(new TopPanel());

        setMargin(true);
        HorizontalLayout horizon = new HorizontalLayout();

        Button button = new Button("Jobs finden", FontAwesome.SEARCH);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        Button bewerben = new Button("Einfach bewerben", FontAwesome.BOOK);
        final TextField textinput = new TextField();


//        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        Benutzer benutzer = ((MyUI) UI.getCurrent()).getBenutzer();

        String vorname;
        if (benutzer != null) {
            vorname = benutzer.getVorname();
        }

        GridLayout layout = new GridLayout(4, 1);
        //Textfeld Jobtitel, Unternehmen
        final TextField jobsearch = new TextField();
        jobsearch.setCaption("Jobtitel, Unternehmen: ");
        jobsearch.setWidth("500px");


//Textfelt Passwort
        final TextField jobsearchOrt = new TextField();
        jobsearchOrt.setCaption("Ort: ");
        jobsearchOrt.setWidth("500px");

//        Label label = new Label(benutzer.getVorname() + ", gebe den Ort ein: ");
        horizon.addComponents(jobsearch, jobsearchOrt, button);
        addComponent(horizon);


        setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
        horizon.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);

        Grid<Stellenanzeige> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        grid.setStyleName("gridSuche");


        //grid bottom #############################
        GridLayout gridBottom = new GridLayout(4, 1);
        gridBottom.setWidth("40%");

        Link link = new Link("Stealthy_Alda",
                new ExternalResource("http://vaadin.com/"));

        Link link1 = new Link("© 2020",
                new ExternalResource("http://vaadin.com/"));

        Link link2 = new Link("Info",
                new ExternalResource("http://vaadin.com/"));

        Link link3 = new Link("Hilfe",
                new ExternalResource("http://vaadin.com/"));


        //LogoBottom.setWidth("50%");
        //LogoBottom.setHeight("50%");

        gridBottom.addComponent(link, 0, 0);
        gridBottom.addComponent(link1, 1, 0);
        gridBottom.addComponent(link2, 2, 0);
        gridBottom.addComponent(link3, 3, 0);

        GridLayout MainFutter = new GridLayout(6, 6);
        MainFutter.setSizeFull();
        MainFutter.addComponent(gridBottom, 3, 5, 4, 5);

        //this.addComponent(MainFutter);
        //this.setComponentAlignment(MainFutter,Alignment.BOTTOM_CENTER);


        // Die aktuell angewählte Zeile in der Tabelle (aka dem Grid)
        SingleSelect<Stellenanzeige> selection = grid.asSingleSelect();

        // Der Event Listener für den Grid
        grid.addSelectionListener(event -> {

            // Speichert den aktuell angewählten Wert bei klick auf die Zeile in der Var. "selected"
            this.selected = selection.getValue();

        });


        // Event Listener für den Suchen Button
        button.addClickListener(e -> {

            String ort = textinput.getValue();

            List<Stellenanzeige> liste = SucheEinfach.getInstance().getStellenanzeigeByOrt(ort);

            if (ort.equals("")) {
                Notification.show(null, "Bitte Ort eingeben!", Notification.Type.WARNING_MESSAGE);
            } else {
                anzahl += 1;

                //erstmal alles löschen
                grid.removeAllColumns();
                grid.setCaption("Treffer für " + ort + " (Anzahl der Suchen: " + anzahl + " " + ((Benutzer) ((MyUI) UI.getCurrent()).getBenutzer()).getVorname() + ")");

                // neue Items hinzufügen
                grid.setItems(liste);

                // Columns definieren
                grid.addColumn(Stellenanzeige::getTitel).setCaption("Titel");
                grid.addColumn(Stellenanzeige::getBeschreibung).setCaption("Beschreibung");
                grid.addColumn(Stellenanzeige::getOrt).setCaption("Ort");
                grid.addColumn(Stellenanzeige::getDatum).setCaption("Datum");

                addComponent(grid);
                addComponent(bewerben);
                setComponentAlignment(bewerben, Alignment.MIDDLE_CENTER);
            }
        });


        bewerben.addClickListener(e -> {
            // Testweise Ausgabe der derzeitigen Selektion in einer Notification

            //Notification.show(null, "Auswahl: " + this.selected.toString(), Notification.Type.WARNING_MESSAGE);

//            BookingWindow window = new BookingWindow(MainView.this.selected);
//            UI.getCurrent().addWindow(window);
        });

        // Grid und Buchen Button richtig anordnen


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {


//        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo((Views.LOGIN));
        } else {

            this.setUp();

        }
    }
}
