/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.views;

import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.stealthyalda.gui.windows.ConfirmReg;
import com.stealthyalda.services.util.Views;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author WINDOWS
 */
public class RegWeiterStudent  extends VerticalLayout implements View {

    public void setUp(){
        TopPanelStartSeite panel = new TopPanelStartSeite();
        panel.setHeight("50px");
        this.addComponent(panel);
        setMargin(true);
        Label label = new Label("<b> Richten Sie Ihr Konto ein! </b>", ContentMode.HTML);
        label.addStyleName("mytitle");
        label.addStyleName ( ValoTheme.LABEL_H1 );
        this.addComponent(label);
        this.setComponentAlignment(label,Alignment.MIDDLE_CENTER);
        final NativeSelect<String> userAnrede = new NativeSelect<>();
//
// Add some items
        userAnrede.setItems("Herr", "Frau");
        userAnrede.setCaption("Anrede");
        userAnrede.setWidth("500px");
        this.addComponent(userAnrede);
        this.setComponentAlignment(userAnrede,Alignment.MIDDLE_CENTER);

        
        HorizontalLayout hl1 = new HorizontalLayout();
        hl1.setWidth("500px");
        final TextField vorname = new TextField();
        vorname.setPlaceholder("Vorname");
        vorname.setWidth("250px");
        hl1.addComponent(vorname);
        hl1.setComponentAlignment(vorname, Alignment.MIDDLE_LEFT);
        
        final TextField nachname = new TextField();
        nachname.setPlaceholder("Nachname");
        nachname.setWidth("230px");
        hl1.addComponent(nachname);
        hl1.setComponentAlignment(nachname, Alignment.MIDDLE_RIGHT);
        
        this.addComponent(hl1);
        this.setComponentAlignment(hl1,Alignment.MIDDLE_CENTER);
        
         HorizontalLayout hl2 = new HorizontalLayout();
        hl2.setWidth("500px");
        final TextField email = new TextField();
        email.setPlaceholder("E-Mail");
        email.setWidth("250px");
        hl2.addComponent(email);
        hl2.setComponentAlignment(email, Alignment.MIDDLE_LEFT);
        
        final TextField passwort = new TextField();
        passwort.setPlaceholder("Passwort");
        passwort.setWidth("230px");
        hl2.addComponent(passwort);
        hl2.setComponentAlignment(passwort, Alignment.MIDDLE_RIGHT);
        
        this.addComponent(hl2);
        this.setComponentAlignment(hl2,Alignment.MIDDLE_CENTER);
        
        HorizontalLayout hl3 = new HorizontalLayout();
        hl3.setWidth("500px");
        final TextField strasse = new TextField();
        strasse.setPlaceholder("Straße");
        strasse.setWidth("250px");
        hl3.addComponent(strasse);
        hl3.setComponentAlignment(strasse, Alignment.MIDDLE_LEFT);
        
        final TextField plz = new TextField();
        plz.setPlaceholder("PLZ");
        plz.setWidth("230px");
        hl3.addComponent(plz);
        hl3.setComponentAlignment(plz, Alignment.MIDDLE_RIGHT);
        
        this.addComponent(hl3);
        this.setComponentAlignment(hl3,Alignment.MIDDLE_CENTER);
        
        final TextField ort = new TextField();
        ort.setPlaceholder("Ort");
        ort.setWidth("500px");
        this.addComponent(ort);
        this.setComponentAlignment(ort,Alignment.MIDDLE_CENTER);
        
        final TextField telefon = new TextField();
        telefon.setPlaceholder("Telefon");
        telefon.setWidth("500px");
        this.addComponent(telefon);
        this.setComponentAlignment(telefon,Alignment.MIDDLE_CENTER);
        
        final Label label2 = new Label( "&nbsp;", ContentMode.HTML);
        this.addComponent(label2);
        
        final Button ubermitteln = new Button();
        ubermitteln.setCaption("Übermitteln");
        ubermitteln.setWidth("500px");
        
        
        ubermitteln.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
            ConfirmReg window = new ConfirmReg("Registrierung abgeschlossen! Zurück zur Loginseite!");
                UI.getCurrent().addWindow(window);           }
        });
                
        this.addComponent(ubermitteln);
        this.setComponentAlignment(ubermitteln,Alignment.MIDDLE_CENTER);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
           this.setUp();
    }
}