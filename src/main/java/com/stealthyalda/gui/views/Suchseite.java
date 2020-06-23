package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.SucheEinfach;
import com.stealthyalda.ai.model.dao.SearchService;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.DataSource;
import com.stealthyalda.services.util.OrtService;
import com.stealthyalda.services.util.Roles;
import com.stealthyalda.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;


import java.io.File;
import java.util.List;

import com.vaadin.shared.ui.ContentMode;


public class Suchseite extends VerticalLayout implements View {

    private transient StellenanzeigeDTO selected = null;
    private List<StellenanzeigeDTO> liste = null;

    public void setUp() {
        Benutzer user  = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        this.addComponent(new TopPanel(user));

        setMargin(true);
        HorizontalLayout horizon = new HorizontalLayout();

        Button button = new Button("Jobs finden", FontAwesome.SEARCH);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);


        ComboBox<String> search = new ComboBox<>();
        search.setPlaceholder("Jobtitel, Unternehmen, ... ");
        search.setWidth("500px");
        /*search.addValueChangeListener(event -> {
            liste = stellenSuchenOnFly(search.getValue(), "");
        });*/
        SearchService service = new SearchService();
        search.setDataProvider(service::fetch, service::count);

        ComboBox<String> searchort = new ComboBox<>();
        searchort.setPlaceholder("Ort, Umkreis ");
        searchort.setWidth("500px");
        OrtService ortService = new OrtService();
        searchort.setDataProvider(ortService::fetch, ortService::count);

        /*final TextField jobsearch = new TextField();
        jobsearch.setPlaceholder("Jobtitel, Unternehmen, ... ");
        jobsearch.setWidth("500px");*/

        /*final TextField jobsearchOrt = new TextField();
        jobsearchOrt.setPlaceholder("Ort, Umkreis ");
        jobsearchOrt.setWidth("500px");*/


        horizon.addComponents(search, searchort, button);
        addComponent(horizon);


        setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
        horizon.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);

        // Event Listener für den Suchen Button
        button.addClickListener(e -> {

            String ort = search.getValue();
            String titel = search.getValue();

            liste = SucheEinfach.getInstance().getStellenanzeigeByLocationOrJobTitelOrUnternehment(titel, ort);
            VerticalLayout scrollableLayout = new VerticalLayout();
            if (ort.equals("") && titel.equals("")) {
                Notification.show(null, "Bitte Ort oder Jobtitel/Unternehmen eingeben!", Notification.Type.WARNING_MESSAGE);
            } else {
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
                    Label stitel = new Label(suche.getTitel(), ContentMode.TEXT);
                    stitel.setWidth("400px");
                    info.addComponent(stitel);


                    Label sunternehmen = new Label(suche.getArbeitgeber(), ContentMode.PREFORMATTED);
                    info.addComponent(sunternehmen);
                    sunternehmen.setWidth("250px");

                    Label sdatum = new Label(suche.getDatum().toString(),  ContentMode.PREFORMATTED);
                    info.addComponent(sdatum);
                    sdatum.setWidth("150px");

                    Label sort = new Label(suche.getOrt(), ContentMode.PREFORMATTED);
                    info.addComponent(sort);
                    sort.setWidth("125px");

                    Label sstatus = new Label(suche.getStatus(), ContentMode.PREFORMATTED);
                    info.addComponent(sstatus);
                    sstatus.setWidth("100px");

                    info.setHeight("60px");

                    titelbeschreibung.addComponent(info);

                    Label sbeschreibung = new Label(suche.getBeschreibung(), ContentMode.PREFORMATTED);
                    sbeschreibung.setHeight("80px");
                    sbeschreibung.setWidth("875px");
                    titelbeschreibung.addComponent(sbeschreibung);

                    titelbeschreibung.setWidth("570px");
                    titelbeschreibung.setHeight("140px");
                    article.addComponent(titelbeschreibung);
                    article.setComponentAlignment(titelbeschreibung, Alignment.MIDDLE_CENTER);

                    scrollableLayout.addComponent(article);
                    article.addLayoutClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.STELLENANZEIGE) );

                }
                addComponent(scrollableLayout);
                setComponentAlignment(scrollableLayout, Alignment.MIDDLE_CENTER);

            }
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
    /*public static List<StellenanzeigeDTO> stellenSuchenOnFly(String titelorarbeitgeber, String ort) {
        List<StellenanzeigeDTO> data = container.getListe().stream().peek(c -> {
            if(c.getArbeitgeber() == null) c.setArbeitgeber("");
            if(c.getTitel() == null) c.setTitel("");
            if(c.getOrt() == null) c.setOrt("");
        }).filter( suche -> suche.getTitel().equals(titelorarbeitgeber) || suche.getArbeitgeber().equals(titelorarbeitgeber)
                || suche.getOrt().equals(ort)
        ).collect(Collectors.toList());

        return data;
    }*/

}
