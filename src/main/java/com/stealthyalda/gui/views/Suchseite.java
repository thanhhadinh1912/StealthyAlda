package com.stealthyalda.gui.views;

import com.stealthyalda.ai.control.SucheEinfach;
import com.stealthyalda.ai.model.dao.SearchService;
import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.gui.windows.StellenanzeigeK;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.gui.ui.MyUI;
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

    public static final String CLASSNAME ="SUCHSEITE";


    private transient StellenanzeigeDTO selected = null;
    private List<StellenanzeigeDTO> liste = null;

    public void setUp() {
        Benutzer user  = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        this.addComponent(new TopPanel(user));

        setMargin(true);
        HorizontalLayout horizon = new HorizontalLayout();

        Button button = new Button("Jobs finden", FontAwesome.SEARCH);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        button.setPrimaryStyleName(CLASSNAME + "-suchen");


        ComboBox<String> search = new ComboBox<>();
        search.setPlaceholder("Jobtitel, Unternehmen, ... ");
        search.setWidth("500px");
        SearchService service = new SearchService();
        search.setDataProvider(service::fetch, service::count);

        ComboBox<String> searchort = new ComboBox<>();
        searchort.setPlaceholder("Ort, Umkreis ");
        searchort.setWidth("500px");
        OrtService ortService = new OrtService();
        searchort.setDataProvider(ortService::fetch, ortService::count);



        horizon.addComponents(search, searchort, button);
        addComponent(horizon);


        setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
        horizon.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);

        VerticalLayout show = new VerticalLayout();
        // Event Listener für den Suchen Button
        button.addClickListener(e -> {
            Panel sucheLayout = new Panel();
            String ort = searchort.getValue();
            String titel = search.getValue();
            /*if (ort.equals("") && titel.equals("")) {
                Notification.show("FEHLER", "Bitte Ort oder Jobtitel/Unternehmen eingeben!", Notification.Type.WARNING_MESSAGE);
            }
            else {*/
            if(titel!=null){
                if(ort!=null) liste = SucheEinfach.getInstance().getStellenanzeigeByLocationAndJobTitelOrUnternehment(titel,ort);

                else  liste = SucheEinfach.getInstance().getStellenanzeigeByJob(titel);
                }
           else liste = SucheEinfach.getInstance().getStellenanzeigeByLocation(ort);


            show.removeAllComponents();
                sucheLayout = printergebnis(liste);
                show.addComponent(sucheLayout);
                addComponent(show);
                setComponentAlignment(show, Alignment.MIDDLE_CENTER);




        });



        // Grid und Buchen Button richtig anordnen


    }

    public Panel printergebnis(List<StellenanzeigeDTO>liste){
        VerticalLayout scrollableLayout = new VerticalLayout();
        Panel main = new Panel();
        if(liste.size()!=0){
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
            info.setComponentAlignment(sunternehmen, Alignment.TOP_CENTER);
            sunternehmen.setWidth("200px");

            Label sdatum = new Label(suche.getDatum().toString(),  ContentMode.PREFORMATTED);
            info.addComponent(sdatum);
            info.setComponentAlignment(sdatum, Alignment.TOP_CENTER);
            sdatum.setWidth("100px");

            Label sort = new Label(suche.getOrt(), ContentMode.PREFORMATTED);
            info.addComponent(sort);
            info.setComponentAlignment(sort, Alignment.TOP_CENTER);
            sort.setWidth("175px");

            Label sstatus = new Label(suche.getStatus(), ContentMode.PREFORMATTED);
            info.addComponent(sstatus);
            info.setComponentAlignment(sstatus, Alignment.TOP_CENTER);
            sstatus.setWidth("75px");

            info.setHeight("60px");

            titelbeschreibung.addComponent(info);

            /*Label sbeschreibung = new Label(suche.getBeschreibung(), ContentMode.PREFORMATTED);
            sbeschreibung.setHeight("80px");
            sbeschreibung.setWidth("875px");
            titelbeschreibung.addComponent(sbeschreibung);*/

            article.addComponent(titelbeschreibung);
            article.setComponentAlignment(titelbeschreibung, Alignment.TOP_CENTER);

            scrollableLayout.addComponent(article);
            StellenanzeigeDTO s = StellenanzeigeDAO.getInstance().getStellenanzeige(suche.getTitel(), suche.getBeschreibung(), suche.getOrt(), suche.getStatus());
            article.addLayoutClickListener(event -> {
                StellenanzeigeK window = new StellenanzeigeK(s);
                UI.getCurrent().addWindow(window);
            } );

        }
        }
        main.setContent(scrollableLayout);
        return main;
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
