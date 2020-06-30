/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.ai.control;

import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.*;
import com.stealthyalda.ai.model.dtos.HardskillDTO;
import com.stealthyalda.ai.model.dtos.HobbyDTO;
import com.stealthyalda.ai.model.dtos.JoberfahrungDTO;
import com.stealthyalda.ai.model.dtos.SoftskillDTO;
import com.stealthyalda.ai.model.entities.*;
import com.stealthyalda.gui.components.ProfilVerwaltenStudent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author WINDOWS
 */
public class ProfilStudentControl {
    public String printHardskill(Benutzer user) {
        StringBuilder print = new StringBuilder();
        List<Hardskill> liste = HardskillDAO.getInstance().getHardskillsForUser(user);
        for (Hardskill hardskill : liste) {
            print.append(hardskill.getHardskill());
            print.append("\n");
        }
        return print.toString();
    }

    public String printSoftskill(Benutzer user) {
        StringBuilder print = new StringBuilder();
        List<Softskill> liste = SoftskillDAO.getInstance().getSoftskillsForUser(user);
        for (int i = 0; i < liste.size(); ++i) {
            print.append(liste.get(i).getSoftskill());
            print.append("\n");
        }
        return print.toString();
    }

    public String printJoberfahrung(Student s) {
        StringBuilder print = new StringBuilder();
        List<JoberfahrungDTO> liste = JoberfahrungDAO.getInstance().getJoberfahrungsForStudent(s);
        for (int i = 0; i < liste.size(); ++i) {
            print.append(liste.get(i).getJoberfahrung());
            print.append("\n");
        }
        return print.toString();
    }

    public String printHobby(Benutzer user) {
        StringBuilder print = new StringBuilder();
        List<Hobby> liste = HobbyDAO.getInstance().getHobbysForUser(user);
        for (int i = 0; i < liste.size(); ++i) {
            print.append(liste.get(i).getHobby());
            print.append("\n");
        }
        return print.toString();
    }

    public boolean hardskillchange(Benutzer user, String input) throws DatabaseException {
        try {
            List<String> listStr = new ArrayList<>();
            listStr.addAll(Arrays.asList(input.split("\n")));
            List<Hardskill> liste = HardskillDAO.getInstance().getHardskillsForUser(user);
            Student s = StudentDAO.getInstance().getStudent(user.getId());
            for (int i = 0; i < liste.size(); ++i) {
                HardskillDAO.getInstance().deleteHardskillForUser(liste.get(i).getHardskillId(), s);
            }
            for (int i = 0; i < listStr.size(); ++i) {
                Hardskill hardskill = new Hardskill(listStr.get(i));
                HardskillDAO.getInstance().createHardskillForUser(hardskill, s);
            }
            return true;
        } catch (DatabaseException e) {
            Logger.getLogger(ProfilStudentControl.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return false;
        }

    }

    public void softskillchange(Benutzer user, String input) throws DatabaseException {
        List<String> softSkillsList = new ArrayList<>();
        // TODO: are our soft and hard skills separated by a tab? I prefer "\n"
        softSkillsList.addAll(Arrays.asList(input.split("\n")));
        List<Softskill> liste = SoftskillDAO.getInstance().getSoftskillsForUser(user);
        Student s = StudentDAO.getInstance().getStudent(user.getId());
        for (int i = 0; i < liste.size(); ++i) {
            SoftskillDAO.getInstance().deleteSoftskillsForUser(liste.get(i).getSoftskillId(), s);
        }
        for (int i = 0; i < softSkillsList.size(); ++i) {
            Softskill softskill = new Softskill();
            softskill.setSoftskill(softSkillsList.get(i));
            SoftskillDAO.getInstance().createSoftskillForUser(softskill, s);
        }

    }

    public void hobbiesChange(Benutzer user, String input) throws DatabaseException {
        List<String> hobbiesListe = new ArrayList<>();
        // TODO: are our hobbies also separated by a tab? I prefer "\n"
        hobbiesListe.addAll(Arrays.asList(input.split("\n")));
        List<Hobby> liste = HobbyDAO.getInstance().getHobbysForUser(user);
        Student s = StudentDAO.getInstance().getStudent(user.getId());
        for (int i = 0; i < liste.size(); ++i) {
            HobbyDAO.getInstance().deleteHobbysForUser(liste.get(i).getHobbyId(), s);
        }
        for (int i = 0; i < hobbiesListe.size(); ++i) {
            Hobby hobby = new Hobby();
            hobby.setHobby(hobbiesListe.get(i));
            HobbyDAO.getInstance().createHobbyForUser(hobby, s);
        }

    }


    public boolean updateStudentProfile(HardskillDTO hardskillDTO, SoftskillDTO softskillDTO, HobbyDTO hobbyDTO, JoberfahrungDTO jobExp, Benutzer user) {
        try {
            hardskillchange(user, hardskillDTO.getHardSkillName());
            softskillchange(user, softskillDTO.getSetSoftSkillName());
            hobbiesChange(user, hobbyDTO.getDescription());
            jobExperienceChange(user, jobExp.getJoberfahrung());
        } catch (DatabaseException ex) {
            Logger.getLogger(ProfilVerwaltenStudent.class.getName()).log(Level.SEVERE, ex.getReason(), ex);
        }
        return false;
    }

    private void jobExperienceChange(Benutzer user, String joberfahrung) throws DatabaseException {
        List<String> experience = new ArrayList<>();
        experience.addAll(Arrays.asList(joberfahrung.split("\n")));
        Student s = StudentDAO.getInstance().getStudent(user.getId());
        List<JoberfahrungDTO> liste = JoberfahrungDAO.getInstance().getJoberfahrungsForStudent(s);
        for (int i = 0; i < liste.size(); ++i) {
            JoberfahrungDAO.getInstance().deleteJoberfahrungForUser(liste.get(i).getJoberfahrungId(), s);
        }
        for (int i = 0; i < experience.size(); ++i) {
            JoberfahrungDTO je = new JoberfahrungDTO();
            je.setJoberfahrung(experience.get(i));
            JoberfahrungDAO.getInstance().createJoberfahrungForUser(je, s);
        }

    }
}

