package com.stealthyalda.ai.control;

import com.stealthyalda.ai.model.dao.AdresseDAO;
import com.stealthyalda.ai.model.dao.ArbeitgeberDAO;
import com.stealthyalda.ai.model.dao.BenutzerDAO;
import com.stealthyalda.ai.model.dao.StellenanzeigeDAO;
import com.stealthyalda.ai.model.dtos.Adresse;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.ai.model.entities.Arbeitgeber;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.services.util.Roles;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;

import java.util.List;

public class ProfilArbeitgeberControl {
    Binder<Adresse> adresseBinder = new Binder<>();
    Binder<Arbeitgeber> arbeitgeberBinder = new Binder<>();
    Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);

    public List<StellenanzeigeDTO> getStellenanzeige(String arbeitgeber) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeByArbeitgeber(arbeitgeber);
    }

    public void updateArbeitgeberprofil(UnternehmenDTO u) {
        ArbeitgeberDAO.getInstance().updateArbeitgeber(u);
        AdresseDAO.getInstance().updateAdresse(u.getAdresse());
        BenutzerDAO.getInstance().updateStammdaten(u, user.getAnrede(), user);
    }
}
