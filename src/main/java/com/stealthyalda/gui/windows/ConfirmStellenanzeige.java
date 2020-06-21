package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.control.StellenanzeigeControl;
import com.stealthyalda.ai.model.entities.Stellenanzeige;
import com.stealthyalda.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

public class ConfirmStellenanzeige extends Window {
    public ConfirmStellenanzeige(Stellenanzeige a) {
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("Sind Sie sicher, dass Sie die Stellenanzeige \t" +
                "jetzt hochladen möchten?"));
        content.setMargin(true);
        setContent(content);

        HorizontalLayout janein = new HorizontalLayout();
        janein.setWidth("600px");

        Button nein = new Button("Nein");
        janein.addComponent(nein);
        nein.addClickListener(clickEvent -> close());
        janein.setComponentAlignment(nein, Alignment.MIDDLE_LEFT);

        Button ja = new Button("Ja");
        janein.addComponent(ja);
        ja.addClickListener(clickEvent -> {
            StellenanzeigeControl c = new StellenanzeigeControl();
            c.erstellen(a);
            Window confirm = new Window("Super! Die Stellenanzeige ist jetzt Online");
            confirm.center();
            confirm.setWidth("600px");
            UI.getCurrent().addWindow(confirm);
            close();
        });
        janein.setComponentAlignment(ja, Alignment.MIDDLE_RIGHT);

        content.addComponent(janein);
        content.setComponentAlignment(janein, Alignment.BOTTOM_CENTER);
    }
}

