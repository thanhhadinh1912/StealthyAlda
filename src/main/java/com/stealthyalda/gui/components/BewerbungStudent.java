package com.stealthyalda.gui.components;

import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.ai.model.factories.BewerbungCollAtHBRSFactory;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class BewerbungStudent extends VerticalLayout {
    public BewerbungStudent(Student s) {
        VerticalLayout content = new VerticalLayout();
        Label news = new Label("<b> News </b>", ContentMode.HTML);

        content.addComponent(news);

        List<BewerbungCollAtHBRSDTO> list = new BewerbungCollAtHBRSFactory().getListBewerbungStudent(s);
        int n;
        if (list.size() < 3) n = list.size();
        else n = 3;
        for (int i = 0; i < n; i++) {
            BewerbungCollAtHBRSDTO b = list.get(i);
            Label a = new Label("Ihre Bewerbung auf die Stellenanzeige '" + b.getStellenanzeige().getTitel() +
                    "' wurde " + b.getStatus());
            content.addComponent(a);
        }
        Label bewerbung = new Label("<b> Aktueller Stand deiner Bewerbungen </b>", ContentMode.HTML);
        content.addComponent(bewerbung);
        for (int i = 0; i < list.size(); i++) {
            BewerbungCollAtHBRSDTO b = list.get(i);
            Label a = new Label((i + 1) + ". " +
                    b.getStellenanzeige().getTitel() + " - " +
                    b.getArbeitgeber().getUnternehmen() + " - " +
                    b.getStatus());
            content.addComponent(a);
        }
        this.addComponent(content);
        this.setComponentAlignment(content, Alignment.MIDDLE_CENTER);


    }
}
