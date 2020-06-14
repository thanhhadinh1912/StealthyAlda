package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.SucheEinfach;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;

import java.util.List;

public class Suchseite extends VerticalLayout implements View {

    private int anzahl = 0;
    private StellenanzeigeDTO selected = null;

    public void setUp() {

        this.addComponent(new TopPanel());

        setMargin(true);
        HorizontalLayout horizon = new HorizontalLayout();

        Button button = new Button("Jobs finden", FontAwesome.SEARCH);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        Button bewerben = new Button("Einfach bewerben", FontAwesome.BOOK);


//        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);


        GridLayout layout = new GridLayout(4, 1);
        //Textfeld Jobtitel, Unternehmen
        final TextField jobsearch = new TextField();
        jobsearch.setPlaceholder("Jobtitel, Unternehmen, ... ");
        jobsearch.setWidth("500px");


//Textfelt Passwort
        final TextField jobsearchOrt = new TextField();
        jobsearchOrt.setPlaceholder("Ort, Umkreis ");
        jobsearchOrt.setWidth("500px");

//        Label label = new Label(benutzer.getVorname() + ", gebe den Ort ein: ");
        horizon.addComponents(jobsearch, jobsearchOrt, button);
        addComponent(horizon);


        setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
        horizon.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);

        Grid<StellenanzeigeDTO> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        grid.setStyleName("gridSuche");


        GridLayout MainFutter = new GridLayout(6, 6);
        MainFutter.setSizeFull();

        SingleSelect<StellenanzeigeDTO> selection = grid.asSingleSelect();

        // Der Event Listener für den Grid
        grid.addSelectionListener(event -> {

            // Speichert den aktuell angewählten Wert bei klick auf die Zeile in der Var. "selected"
            this.selected = selection.getValue();

        });


        // Event Listener für den Suchen Button
        button.addClickListener(e -> {

            String ort = jobsearchOrt.getValue();
            String titel = jobsearch.getValue();

            List<StellenanzeigeDTO> liste = SucheEinfach.getInstance().getStellenanzeigeByOrt(titel, ort);

            if (ort.equals("") && titel.equals("")) {
                Notification.show(null, "Bitte Ort oder Jobtitel/Unternehmen eingeben!", Notification.Type.WARNING_MESSAGE);
            } else {
                anzahl += 1;

                //erstmal alles löschen
                grid.removeAllColumns();
                grid.setCaption("Treffer für " + titel + " " + ort + " (Anzahl der Suchen: " + anzahl + ")");
                grid.setItems(liste);
                // neue Items hinzufügen
                grid.setItems(liste);

                // Columns definieren
                grid.addColumn(StellenanzeigeDTO::getTitel).setCaption("Titel");
                grid.addColumn(StellenanzeigeDTO::getBeschreibung).setCaption("Beschreibung");
                grid.addColumn(StellenanzeigeDTO::getArbeitgeber).setCaption("Unternehmen");
                grid.addColumn(StellenanzeigeDTO::getDatum).setCaption("Datum");
                grid.addColumn(StellenanzeigeDTO::getOrt).setCaption("Ort");
                grid.addColumn(StellenanzeigeDTO::getStatus).setCaption("Status");

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
