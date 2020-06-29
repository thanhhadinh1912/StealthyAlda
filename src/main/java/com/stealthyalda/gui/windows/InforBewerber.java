package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class InforBewerber extends Window {
    private String WIDTH = "950px";
    public InforBewerber(BewerbungCollAtHBRSDTO b){
        center();
        VerticalLayout content = new VerticalLayout();
        Label titel = new Label("Information zur Bewerbung");

        TextArea an = new TextArea("Anschreiben");
        an.setReadOnly(true);
        an.setWidth(WIDTH);
        an.setValue(b.getAnschreiben());

        TextArea er = new TextArea("Erfahrung");
        er.setReadOnly(true);
        er.setWidth(WIDTH);
        er.setValue(b.getErfahrung());

        TextArea ze = new TextArea("Letzter Schulabschluss, Zertifikate, etc.");
        ze.setValue(b.getZertifikat());
        ze.setWidth(WIDTH);
        ze.setReadOnly(true);

        content.addComponent(an);
        content.addComponent(er);
        content.addComponent(ze);

        this.setContent(content);

    }
}
