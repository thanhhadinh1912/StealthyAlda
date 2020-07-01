package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.StellenanzeigeControl;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.gui.windows.ConfirmReg;
import com.stealthyalda.services.util.Views;
import com.vaadin.ui.*;

import static com.stealthyalda.gui.views.Register.FEHLER;

public class Jobangebotbearbeiten extends Window {
    public Jobangebotbearbeiten(StellenanzeigeDTO jobangebot) {
        center();
        VerticalLayout content = new ComponentJobAusschreibung().getJobAusschreibung(jobangebot);

        ComboBox status = new ComboBox();
        status.setPlaceholder("Neuer Status");
        status.setItems("Offen", "Pausiert", "Geschlossen");
        status.setWidth("350px");

        HorizontalLayout statusbe = new HorizontalLayout();
        statusbe.addComponent(status);

        Button bstatus = new Button("Status ändern");
        bstatus.addClickListener(clickEvent -> {
            jobangebot.setStatus((String) status.getValue());
            boolean updateStellenanzeigenStatus = new StellenanzeigeControl().updatestatus(jobangebot);
            if (updateStellenanzeigenStatus) {
                ConfirmReg confirm = new ConfirmReg("Status von Jobangebot wurde aktualisiert", Views.DASHBOARDA);
                UI.getCurrent().addWindow(confirm);
                close();
            } else {
                Notification.show(FEHLER, "Anzeige wurde nich aktualisiert. Support benachrichtigen", Notification.Type.ERROR_MESSAGE);
            }
        });

        statusbe.addComponent(bstatus);
        statusbe.setComponentAlignment(bstatus, Alignment.MIDDLE_RIGHT);

        content.addComponent(statusbe);
        content.setComponentAlignment(statusbe, Alignment.MIDDLE_CENTER);

        this.setContent(content);
    }
}
