package com.stealthyalda.gui.components;

import com.stealthyalda.ai.control.ProfilStudentControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.StudentDAO;
import com.stealthyalda.ai.model.dtos.HardskillDTO;
import com.stealthyalda.ai.model.dtos.HobbyDTO;
import com.stealthyalda.ai.model.dtos.JoberfahrungDTO;
import com.stealthyalda.ai.model.dtos.SoftskillDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.util.ImageUploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

public class ProfilVerwaltenStudent extends ProfilVerwalten {
    static final String PX_700 = "700px";
    // public static final String CLASSNAME = "ProfilVerwaltenStudent";

    public ProfilVerwaltenStudent(Benutzer user) throws DatabaseException {
        super(user);
        boolean isadmin = user.getRole().equals("admin");
        Student current = StudentDAO.getInstance().getStudent(user.getEmail());
        ProfilStudentControl c = new ProfilStudentControl();
        HorizontalLayout horizon1 = new HorizontalLayout();
        VerticalLayout vartical1 = new VerticalLayout();
        TextField name = new TextField();
        name.setPlaceholder("Vorname Nachname");
        if (!isadmin) {
            String studentname = current.getVorname() + " " + current.getNachname();
            if (studentname.length() != 0) {
                name.setValue(studentname);
            }
        }
        name.setWidth(PX_700);
        name.setHeight("40px");
        vartical1.addComponent(name);
        vartical1.setComponentAlignment(name, Alignment.MIDDLE_CENTER);

        TextArea jobExperience = new TextArea("Job Erfahrungen:");
        jobExperience.setWidth(PX_700);
        jobExperience.setHeight("200px");
        jobExperience.setValue(c.printJoberfahrung(StudentDAO.getInstance().getStudent(user.getId())));
        vartical1.addComponent(jobExperience);
        vartical1.setComponentAlignment(jobExperience, Alignment.MIDDLE_CENTER);

        HorizontalLayout horizon2 = new HorizontalLayout();
        horizon2.setHeight("75px");
        horizon2.setWidth(PX_700);

        TextArea hobby = new TextArea("Hobbys");
        hobby.setWidth("325px");
        hobby.setValue(c.printHobby(user));
        horizon2.addComponent(hobby);
        horizon2.setComponentAlignment(hobby, Alignment.MIDDLE_LEFT);

        TextArea hardskill = new TextArea("Hardskills");
        hardskill.setWidth("325px");
        hardskill.setValue(c.printHardskill(user));
        horizon2.addComponent(hardskill);
        horizon2.setComponentAlignment(hardskill, Alignment.MIDDLE_RIGHT);
        vartical1.addComponent(horizon2);
        vartical1.setComponentAlignment(horizon2, Alignment.BOTTOM_CENTER);


        horizon1.addComponent(vartical1);
        horizon1.setComponentAlignment(vartical1, Alignment.TOP_LEFT);

        VerticalLayout vertical2 = new VerticalLayout();
        vertical2.setHeight("525px");


        // Create the upload with a caption and set receiver later
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown.png"));
        Image profilbild = new Image("", resource);
        vertical2.addComponent(profilbild);
        vertical2.setComponentAlignment(profilbild, Alignment.MIDDLE_RIGHT);
        ImageUploader receiver = new ImageUploader();
        Upload upload = new Upload("", receiver);
        upload.addSucceededListener(receiver);

        upload.setButtonCaption("Profilbild hochladen");
        ///---------////

        upload.setImmediateMode(true);
        vertical2.addComponent(upload);
        vertical2.setComponentAlignment(upload, Alignment.MIDDLE_RIGHT);
        TextArea softskill = new TextArea("Softskills");
        softskill.setWidth("275px");
        softskill.setHeight("117px");
        softskill.setValue(c.printSoftskill(user));
        vertical2.addComponent(softskill);
        vertical2.setComponentAlignment(softskill, Alignment.BOTTOM_RIGHT);

        horizon1.addComponent(vertical2);
        horizon1.setComponentAlignment(vertical2, Alignment.TOP_RIGHT);
        horizon1.setWidth("1100px");
        this.addComponent(horizon1);
        this.setComponentAlignment(horizon1, Alignment.TOP_CENTER);

        Button speichern = new Button("Speichern");
        speichern.addClickListener(clickEvent -> {
        });
        speichern.addClickListener(event -> {
            ProfilStudentControl psc = new ProfilStudentControl();
            String studentName = name.getValue(); // split into vor/nachname
            String jobExp = jobExperience.getValue();
            String hardSkills = hardskill.getValue();
            String softSkills = softskill.getValue();

            HobbyDTO hbby = new HobbyDTO();
            hbby.setDescription(hobby.getValue());

            HardskillDTO hskill = new HardskillDTO();
            SoftskillDTO skill = new SoftskillDTO();

            JoberfahrungDTO jobE = new JoberfahrungDTO();
            jobE.setJoberfahrung(jobExp);

            hskill.setHardSkillName(hardSkills);
            skill.setSetSoftSkillName(softSkills);
            psc.updateStudentProfile(hskill, skill, hbby, jobE, user);
        });
        this.addComponent(speichern);
        this.setComponentAlignment(speichern, Alignment.BOTTOM_CENTER);

        this.setWidth("1150px");
        this.setHeight("600px");

    }
}
