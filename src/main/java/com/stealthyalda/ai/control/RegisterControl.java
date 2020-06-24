package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.control.exceptions.UserExistsException;
import com.stealthyalda.ai.model.dao.AdresseDAO;
import com.stealthyalda.ai.model.dao.ArbeitgeberDAO;
import com.stealthyalda.ai.model.dao.BenutzerDAO;
import com.stealthyalda.ai.model.dao.StudentDAO;
import com.stealthyalda.ai.model.dtos.StudentDTO;
import com.stealthyalda.ai.model.dtos.UnternehmenDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.vaadin.ui.UI;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterControl {
    Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

    public boolean checkUserExists(String email) throws UserExistsException, DatabaseException {
        return BenutzerDAO.getInstance().checkUserExists(email);
    }

    /**
     * @param email    - String
     * @param password - String of the password entered in the form
     * @return boolean
     * @throws DatabaseException When murphy is around
     */
    public boolean registerUser(String email, String password, String role) throws DatabaseException {
        boolean erg= BenutzerDAO.getInstance().createBenutzer(email, password, role);
        sendConfirmationEmail(email);
        return  erg;
    }

    public void registerArbeitgeber(UnternehmenDTO u, String anrede) {
        ArbeitgeberDAO.getInstance().insertArbeitgeber(u);
        BenutzerDAO.getInstance().updateStammdaten(u, anrede, user);
        AdresseDAO.getInstance().addAdresse(u.getAdresse(), user.getId());
    }

    /**
     * register a student user
     *
     * @param studi - A DTO of type Student
     */
    public void registerStudent(StudentDTO studi) {

        try {
            StudentDAO.getInstance().newStudent(studi);
            AdresseDAO.getInstance().addAdresse(studi.getAdresse(), user.getId());
            BenutzerDAO.getInstance().updateStammdaten(studi, studi.getAnrede(), user);
        } catch (Exception e) {
            Logger.getLogger(RegisterControl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void sendConfirmationEmail(String email) {
        HtmlEmail mail = new HtmlEmail();

        mail.setHostName("smtp.mailtrap.io");
        mail.setSmtpPort(587);
        mail.setSSLOnConnect(false);
        mail.setAuthentication("fc96262875e64b", "556759cf419b54");

        try {
            mail.setFrom("stealthy.alda@test.com");
            mail.addTo(email);
            mail.setSubject("Willkommen in Stealthy_Alda Portal");
            mail.setHtmlMsg("Hallo! Sie haben Ihr Konto erfolgreich erstellt!<br>Ab jetzt steht Ihnen das Portal zur Verfügung.");
            mail.send();
        } catch (EmailException e) {
            Logger.getLogger(RegisterControl.class.getName()).log(Level.SEVERE, "Failed to send an email", e);
        }
    }

}

