package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.SucheEinfach;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;

import java.io.File;
import java.util.List;
import com.vaadin.shared.ui.ContentMode;

public class Suchseite extends VerticalLayout implements View {

    private int anzahl = 0;
    private transient StellenanzeigeDTO selected = null;

    public void setUp() {
        this.addComponent(new TopPanel());

        setMargin(true);
        HorizontalLayout horizon = new HorizontalLayout();

        Button button = new Button("Jobs finden", FontAwesome.SEARCH);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        Button bewerben = new Button("Einfach bewerben", FontAwesome.BOOK);





        //Textfeld Jobtitel, Unternehmen
        final TextField jobsearch = new TextField();
        jobsearch.setPlaceholder("Jobtitel, Unternehmen, ... ");
        jobsearch.setWidth("500px");


//Textfelt Passwort
        final TextField jobsearchOrt = new TextField();
        jobsearchOrt.setPlaceholder("Ort, Umkreis ");
        jobsearchOrt.setWidth("500px");


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
        grid.addSelectionListener(event ->

            // Speichert den aktuell angewählten Wert bei klick auf die Zeile in der Var. "selected"
            this.selected = selection.getValue());


        // Event Listener für den Suchen Button
        button.addClickListener(e -> {

            String ort = jobsearchOrt.getValue();
            String titel = jobsearch.getValue();

            List<StellenanzeigeDTO> liste = SucheEinfach.getInstance().getStellenanzeigeByLocationOrJobTitelOrUnternehment(titel, ort);
            VerticalLayout scrollableLayout = new VerticalLayout();
            /*for(int i = 0; i< 100; i++){
                scrollableLayout.add(createCard());
            }*/
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
                for(int i=0; i<liste.size();i++){
                    StellenanzeigeDTO suche= liste.get(i);
                    HorizontalLayout article = new HorizontalLayout();
                    String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
                    FileResource resource = new FileResource(new File(basepath +
                            "/Image/Unknown_profil.png"));
                    Image profilbild = new Image("", resource);
                    article.addComponent(profilbild);
                    article.setComponentAlignment(profilbild, Alignment.MIDDLE_LEFT);
                    VerticalLayout titelbeschreibung = new VerticalLayout();
                    HorizontalLayout info = new HorizontalLayout();
                    Label stitel = new Label(suche.getTitel(), ContentMode.PREFORMATTED);
                    info.addComponent(stitel);
                    Label sunternehmen = new Label(suche.getArbeitgeber(), ContentMode.TEXT);
                    info.addComponent(sunternehmen);

                    Label sdatum = new Label(suche.getDatum().toString(),  ContentMode.TEXT);
                    info.addComponent(sdatum);

                    Label sort = new Label(suche.getOrt(), ContentMode.TEXT);
                    info.addComponent(sort);

                    Label sstatus = new Label(suche.getStatus(), ContentMode.TEXT);
                    info.addComponent(sstatus);

                    titelbeschreibung.addComponent(info);
                    titelbeschreibung.setComponentAlignment(info, Alignment.TOP_LEFT);

                    Label sbeschreibung = new Label(suche.getBeschreibung(), ContentMode.TEXT);
                    titelbeschreibung.addComponent(sbeschreibung);
                    titelbeschreibung.setComponentAlignment(sbeschreibung, Alignment.BOTTOM_LEFT);
                    article.addComponent(titelbeschreibung);
                    article.setStyleName("layout-with-border");

                    scrollableLayout.addComponent(article);


                }
                addComponent(scrollableLayout);

            }
        });


        bewerben.addClickListener(e -> {

        });

        // Grid und Buchen Button richtig anordnen


    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {



        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

        if (user == null) {
            UI.getCurrent().getNavigator().navigateTo((Views.LOGIN));
        } else {
            this.setUp();

        }
    }
}
