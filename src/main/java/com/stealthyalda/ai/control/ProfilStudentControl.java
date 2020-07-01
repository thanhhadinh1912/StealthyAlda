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
        for (Softskill softskill : liste) {
            print.append(softskill.getSoftskill());
            print.append("\n");
        }
        return print.toString();
    }

    public String printJoberfahrung(Student s) {
        StringBuilder print = new StringBuilder();
        List<JoberfahrungDTO> liste = JoberfahrungDAO.getInstance().getJoberfahrungsForStudent(s);
        for (JoberfahrungDTO joberfahrungDTO : liste) {
            print.append(joberfahrungDTO.getJoberfahrung());
            print.append("\n");
        }
        return print.toString();
    }

    public String printHobby(Benutzer user) {
        StringBuilder print = new StringBuilder();
        List<Hobby> liste = HobbyDAO.getInstance().getHobbysForUser(user);
        for (Hobby hobby : liste) {
            print.append(hobby.getHobby());
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
            for (Hardskill item : liste) {
                HardskillDAO.getInstance().deleteHardskillForUser(item.getHardskillId(), s);
            }
            for (String value : listStr) {
                Hardskill hardskill = new Hardskill(value);
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
        softSkillsList.addAll(Arrays.asList(input.split("\n")));
        List<Softskill> liste = SoftskillDAO.getInstance().getSoftskillsForUser(user);
        Student s = StudentDAO.getInstance().getStudent(user.getId());
        for (Softskill value : liste) {
            SoftskillDAO.getInstance().deleteSoftskillsForUser(value.getSoftskillId(), s);
        }
        for (String value : softSkillsList) {
            Softskill softskill = new Softskill();
            softskill.setSoftskill(value);
            SoftskillDAO.getInstance().createSoftskillForUser(softskill, s);
        }

    }

    public void hobbiesChange(Benutzer user, String input) throws DatabaseException {
        List<String> hobbiesListe = new ArrayList<>();
        hobbiesListe.addAll(Arrays.asList(input.split("\n")));
        List<Hobby> liste = HobbyDAO.getInstance().getHobbysForUser(user);
        Student s = StudentDAO.getInstance().getStudent(user.getId());
        for (Hobby value : liste) {
            HobbyDAO.getInstance().deleteHobbysForUser(value.getHobbyId(), s);
        }
        for (String value : hobbiesListe) {
            Hobby hobby = new Hobby();
            hobby.setHobby(value);
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
        for (JoberfahrungDTO joberfahrungDTO : liste) {
            JoberfahrungDAO.getInstance().deleteJoberfahrungForUser(joberfahrungDTO.getJoberfahrungId(), s);
        }
        for (String value : experience) {
            JoberfahrungDTO je = new JoberfahrungDTO();
            je.setJoberfahrung(value);
            JoberfahrungDAO.getInstance().createJoberfahrungForUser(je, s);
        }

    }
}

