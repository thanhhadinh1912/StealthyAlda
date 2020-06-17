package com.stealthyalda.gui.components;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class KontoVerwaltung extends VerticalLayout {
    public KontoVerwaltung(){

        VerticalLayout top = new VerticalLayout();
        top.setWidth("1000px");
        top.setHeight("150px");
        Label label = new Label("<b> Kontoverwaltung </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        //label.addStyleName(ValoTheme.LABEL_H1);
        top.addComponent(label);

        Label label2 = new Label("Sie können hier Ihr Konto löschen oder Ihr Passwort ändern.");
        top.addComponent(label2);

        this.addComponent(top);
        this.setComponentAlignment(top, Alignment.TOP_CENTER);
        /*Label label3 = new Label("&nbsp;", ContentMode.HTML);
        this.addComponent(label3);*/

        HorizontalLayout main = new HorizontalLayout();
        main.setWidth("1000px");
        Label loschen = new Label("Konto löschen");

        VerticalLayout deletekonto = new VerticalLayout();

        deletekonto.addComponent(loschen);

        final TextField mail = new TextField();
        mail.setPlaceholder("E-Mail Adresse");
        mail.setWidth("300px");
        deletekonto.addComponent(mail);

        final TextField passwort = new TextField();
        passwort.setPlaceholder("Passwort");
        passwort.setWidth("300px");
        deletekonto.addComponent(passwort);


        final Button delete = new Button("Löschen");
        deletekonto.addComponent(delete);
        
        VerticalLayout changepassword = new VerticalLayout();
        Label change = new Label("Passwort ändern");

        changepassword.addComponent(change);

        final TextField mail2 = new TextField();
        mail2.setPlaceholder("E-Mail Adresse");
        mail2.setWidth("300px");
        changepassword.addComponent(mail2);

        final TextField alt = new TextField();
        alt.setPlaceholder("Altes Passwort");
        alt.setWidth("300px");
        changepassword.addComponent(alt);

        final TextField neu = new TextField();
        neu.setPlaceholder("Neues Passwort");
        neu.setWidth("300px");
        changepassword.addComponent(neu);

        final Button andern = new Button("Ändern");
        changepassword.addComponent(andern);

        main.addComponent(deletekonto);
        main.setComponentAlignment(deletekonto, Alignment.TOP_LEFT);
        main.addComponent(changepassword);
        main.setComponentAlignment(changepassword, Alignment.TOP_RIGHT);
        this.addComponent(main);
        this.setComponentAlignment(main, Alignment.TOP_CENTER);


    }
}
