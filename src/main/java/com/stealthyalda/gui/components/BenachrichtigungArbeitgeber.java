package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.ProfilArbeitgeberControl;
import com.stealthyalda.ai.control.ToogleRouter;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.ArbeitgeberDAO;
import com.stealthyalda.ai.model.dtos.BewerbungCollAtHBRSDTO;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.factories.BewerbungCollAtHBRSFactory;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class BenachrichtigungArbeitgeber extends VerticalLayout {
    public BenachrichtigungArbeitgeber(Benutzer user) {
        VerticalLayout content = new VerticalLayout();
        try {
            if (ToogleRouter.isEnabled("bewerbung")) {
                Label titelbewerbung = new Label("<b> Ihre eingegangenen Bewerbungen </b>", ContentMode.HTML);
                content.addComponent(titelbewerbung);
                Arbeitgeber a = ArbeitgeberDAO.getInstance().getArbeitgeber(user.getEmail());
                List<BewerbungCollAtHBRSDTO> bewerbungs = BewerbungCollAtHBRSFactory.getInstance().getListBewerbungForArbeitgeber(a);

                for (int i = 0; i < bewerbungs.size(); i++) {
                    HorizontalLayout layout = new HorizontalLayout();
                    BewerbungCollAtHBRSDTO bewerbung = bewerbungs.get(i);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                    String formattedString = bewerbung.getDatum().format(formatter);
                    Label label = new Label((i + 1) + ". " +
                            bewerbung.getStudent().getNachname() + ", " +
                            bewerbung.getStudent().getVorname() + " - " +
                            bewerbung.getStellenanzeige().getTitel() + " - " +
                            formattedString
                    );
                    layout.addComponent(label);
                    Button infor = new Button(VaadinIcons.INFO);
                    layout.addComponent(infor);
                    content.addComponent(layout);
                }
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


        Label titelstellenanzeige = new Label("<b> Ihre Stellenanzeigen </b>", ContentMode.HTML);
        content.addComponent(titelstellenanzeige);

        if (!user.getRole().equals("admin")) {

            List<StellenanzeigeDTO> jobangebot = new ProfilArbeitgeberControl().getStellenanzeige(ArbeitgeberDAO.getInstance().getArbeitgeber(user.getEmail()).getUnternehmen());
            for (int i = 0; i < jobangebot.size(); i++) {
                HorizontalLayout joblayout = new HorizontalLayout();
                Label job = new Label((i + 1) + "." + jobangebot.get(i).getTitel() + " - " + jobangebot.get(i).getStatus());
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
