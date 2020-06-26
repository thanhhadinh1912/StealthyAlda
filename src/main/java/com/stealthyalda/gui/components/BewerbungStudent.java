package com.stealthyalda.gui.components;

import com.stealthyalda.ai.model.entities.Bewerbung;
import com.stealthyalda.ai.model.entities.Student;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class BewerbungStudent extends VerticalLayout {
    public BewerbungStudent(Student s){
        VerticalLayout content = new VerticalLayout();
        Label news = new Label("News");
        content.addComponent(news);

        Label bewerbung = new Label("Aktueller Stand deiner Bewerbungen");
        content.addComponent(bewerbung);


    }
}
