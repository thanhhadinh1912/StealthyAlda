package com.stealthyalda.gui.components;

import com.stealthyalda.services.util.ImageUploader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.io.File;

public class ProfilVerwaltenStudent extends VerticalLayout {
    public ProfilVerwaltenStudent(){
        HorizontalLayout horizon1 = new HorizontalLayout();
        VerticalLayout vartical1 = new VerticalLayout();
        TextField name = new TextField();
        name.setPlaceholder("Vorname Nachname");
        name.setWidth("700px");
        name.setHeight("40px");
        vartical1.addComponent(name);
        vartical1.setComponentAlignment(name, Alignment.MIDDLE_CENTER);

        TextArea area = new TextArea("Job Erfahrungen:");
        area.setWidth("700px");
        area.setHeight("200px");
        vartical1.addComponent(area);
        vartical1.setComponentAlignment(area, Alignment.MIDDLE_CENTER);

        HorizontalLayout horizon2 = new HorizontalLayout();
        horizon2.setHeight("75px");
        horizon2.setWidth("700px");

        TextArea hobby = new TextArea("Hobbys");
        hobby.setWidth("325px");
        horizon2.addComponent(hobby);
        horizon2.setComponentAlignment(hobby, Alignment.MIDDLE_LEFT);

        TextArea hardskill = new TextArea("Hardskills");
        hardskill.setWidth("325px");
        horizon2.addComponent(hardskill);
        horizon2.setComponentAlignment(hardskill, Alignment.MIDDLE_RIGHT);
        vartical1.addComponent(horizon2);
        vartical1.setComponentAlignment(horizon2, Alignment.BOTTOM_CENTER);


        horizon1.addComponent(vartical1);
        horizon1.setComponentAlignment(vartical1, Alignment.TOP_LEFT);

        VerticalLayout vertical2 = new VerticalLayout();
        vertical2.setHeight("525px");
        ImageUploader receiver = new ImageUploader();

        // Create the upload with a caption and set receiver later
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Unknown.png"));
        Image profilbild = new Image("", resource);
        vertical2.addComponent(profilbild);
        vertical2.setComponentAlignment(profilbild, Alignment.MIDDLE_RIGHT);
        /*Label label = new Label("&nbsp;", ContentMode.HTML);
        vertical2.addComponent(label);*/
        Upload upload = new Upload("", receiver);
        upload.addSucceededListener(receiver);
        upload.setButtonCaption("Profilbild hochladen");
        upload.setImmediateMode(false);
        vertical2.addComponent(upload);
        vertical2.setComponentAlignment(upload, Alignment.MIDDLE_RIGHT);
        TextArea softskill = new TextArea("Softskills");
        softskill.setWidth("275px");
        softskill.setHeight("117px");
        vertical2.addComponent(softskill);
        vertical2.setComponentAlignment(softskill, Alignment.BOTTOM_RIGHT);

        horizon1.addComponent(vertical2);
        horizon1.setComponentAlignment(vertical2, Alignment.TOP_RIGHT);
        horizon1.setWidth("1100px");
        this.addComponent(horizon1);
        this.setComponentAlignment(horizon1, Alignment.TOP_CENTER);

        Button speichern = new Button("Speichern");
        this.addComponent(speichern);
        this.setComponentAlignment(speichern, Alignment.BOTTOM_CENTER);

        this.setWidth("1150px");
        this.setHeight("600px");




//        String name = benutzer.getName();
//        if (name != null) {
//            tfname.setValue(name);
//        }
    }
}
