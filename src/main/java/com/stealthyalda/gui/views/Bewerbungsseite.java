package com.stealthyalda.gui.views;

import com.stealthyalda.gui.components.TopPanel;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class Bewerbungsseite extends VerticalLayout implements View {
    public void setUp() {
        this.addComponent(new TopPanel());

        VerticalLayout main = new VerticalLayout();
        Label titel = new Label("<b> Bewerbungsunterlage </b>", ContentMode.HTML);
        titel.addStyleName(ValoTheme.LABEL_H1);
        main.addComponent(titel);
        main.setComponentAlignment(titel, Alignment.TOP_LEFT);

        Label platz = new Label("&nbsp;", ContentMode.HTML);
        main.addComponent(platz);

        HorizontalLayout anschreibenupload = new HorizontalLayout();
        TextArea anschreiben = new TextArea("Anschreiben");
        anschreibenupload.addComponent(anschreiben);
        anschreiben.setWidth("500px");
        anschreibenupload.setComponentAlignment(anschreiben, Alignment.TOP_LEFT);


    }
}
