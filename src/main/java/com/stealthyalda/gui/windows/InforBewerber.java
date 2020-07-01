package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class InforBewerber extends Window {
    private static final String WIDTH_950_PX = "950px";

    public InforBewerber(BewerbungCollAtHBRSDTO b) {
        center();
        VerticalLayout content = new VerticalLayout();
        Label titel = new Label("Information zur Bewerbung");

        TextArea an = new TextArea("Anschreiben");
        an.setReadOnly(true);
        an.setWidth(WIDTH_950_PX);
        an.setValue(b.getAnschreiben());

        TextArea er = new TextArea("Erfahrung");
        er.setReadOnly(true);
        er.setWidth(WIDTH_950_PX);
        er.setValue(b.getErfahrung());

        TextArea ze = new TextArea("Letzter Schulabschluss, Zertifikate, etc.");
        ze.setValue(b.getZertifikat());
        ze.setWidth(WIDTH_950_PX);
        ze.setReadOnly(true);

        content.addComponent(an);
        content.addComponent(er);
        content.addComponent(ze);

        this.setContent(content);

    }
}
