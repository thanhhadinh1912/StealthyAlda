package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.ProfilArbeitgeberControl;
import com.stealthyalda.ai.model.dao.ArbeitgeberDAO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class BenachrichtigungArbeitgeber extends VerticalLayout {
    public BenachrichtigungArbeitgeber(Benutzer user) {
        VerticalLayout content = new VerticalLayout();
        Label titelbewerbung = new Label("<b> Ihre eingegangenen Bewerbungen </b>", ContentMode.HTML);
        content.addComponent(titelbewerbung);

        Label titelstellenanzeige = new Label("<b> Ihre Stellenanzeigen </b>", ContentMode.HTML);
        content.addComponent(titelstellenanzeige);

        if(!user.getRole().equals("admin")) {

            List<StellenanzeigeDTO> jobangebot = new ProfilArbeitgeberControl().getStellenanzeige(ArbeitgeberDAO.getInstance().getArbeitgeber(user.getEmail()).getUnternehmen());
            for (int i = 0; i < jobangebot.size(); i++) {
                HorizontalLayout joblayout = new HorizontalLayout();
                Label job = new Label(Integer.toString(i + 1) + "." + jobangebot.get(i).getTitel() + " - " + jobangebot.get(i).getStatus());
                Button jobangebotbearbeiten = new Button(VaadinIcons.PENCIL);
                //jobangebotbearbeiten.addClickListener(clickEvent -> {});
                joblayout.addComponent(job);
                joblayout.addComponent(jobangebotbearbeiten);
                content.addComponent(joblayout);
            }
        }
        this.addComponent(content);
    }
}
