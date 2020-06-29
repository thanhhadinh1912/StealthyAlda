package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.control.ProfilStudentControl;
import com.stealthyalda.ai.control.exceptions.DatabaseException;
import com.stealthyalda.ai.model.dao.StudentDAO;
import com.stealthyalda.ai.model.dtos.HardskillDTO;
import com.stealthyalda.ai.model.dtos.HobbyDTO;
import com.stealthyalda.ai.model.dtos.SoftskillDTO;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.ai.model.entities.Student;
import com.stealthyalda.services.util.ImageUploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

public class ProfilStudent extends Window {
    static final String PX_700 = "700px";

    public ProfilStudent(Student s) throws DatabaseException {
        center();
        VerticalLayout content = new VerticalLayout();
        ProfilStudentControl c = new ProfilStudentControl();
        HorizontalLayout horizon1 = new HorizontalLayout();
        VerticalLayout vartical1 = new VerticalLayout();
        TextField name = new TextField();
        name.setReadOnly(true);
        name.setPlaceholder("Vorname Nachname");
            String studentname = s.getVorname() + " " + s.getNachname();
            if (studentname.length() != 0) {
                name.setValue(studentname);
            }

        name.setWidth(PX_700);
        name.setHeight("40px");
        vartical1.addComponent(name);
        vartical1.setComponentAlignment(name, Alignment.MIDDLE_CENTER);

        TextArea jobExperience = new TextArea("Job Erfahrungen:");
        jobExperience.setWidth(PX_700);
        jobExperience.setHeight("200px");
        jobExperience.setValue(c.printJoberfahrung(s));
        jobExperience.setReadOnly(true);
        vartical1.addComponent(jobExperience);
        vartical1.setComponentAlignment(jobExperience, Alignment.MIDDLE_CENTER);

        HorizontalLayout horizon2 = new HorizontalLayout();
        horizon2.setHeight("75px");
        horizon2.setWidth(PX_700);

        Benutzer user = new Benutzer();
        user.setId(s.getId());
        TextArea hobby = new TextArea("Hobbys");
        hobby.setReadOnly(true);
        hobby.setWidth("325px");
        c.printHobby(user);
        horizon2.addComponent(hobby);
        horizon2.setComponentAlignment(hobby, Alignment.MIDDLE_LEFT);

        TextArea hardskill = new TextArea("Hardskills");
        hardskill.setWidth("325px");
        hardskill.setReadOnly(true);
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

        TextArea softskill = new TextArea("Softskills");
        softskill.setReadOnly(true);
        softskill.setWidth("275px");
        softskill.setHeight("117px");
        softskill.setValue(c.printSoftskill(user));
        vertical2.addComponent(softskill);
        vertical2.setComponentAlignment(softskill, Alignment.BOTTOM_RIGHT);

        horizon1.addComponent(vertical2);
        horizon1.setComponentAlignment(vertical2, Alignment.TOP_RIGHT);
        horizon1.setWidth("1100px");
        content.addComponent(horizon1);
        content.setComponentAlignment(horizon1, Alignment.TOP_CENTER);

        HorizontalLayout button = new HorizontalLayout();
        button.setWidth("950px");
        Button ablehnen = new Button("Ablehnen!");
        ablehnen.setWidth("200px");
        button.addComponent(ablehnen);
        button.setComponentAlignment(ablehnen, Alignment.MIDDLE_LEFT);

        Button zusage = new Button ("Zusagen!");
        zusage.setWidth("200px");
        button.addComponent(zusage);
        button.setComponentAlignment(zusage, Alignment.MIDDLE_RIGHT);

        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        this.setContent(content);

    }
}
