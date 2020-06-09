package com.stealthyalda.gui.components;
import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.gui.windows.ProfilVerwaltenArbeitgeber;
import com.stealthyalda.gui.windows.ProfilVerwaltenStudent;
import com.stealthyalda.services.util.Views;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;


public class TopPanelStartSeite extends HorizontalLayout {
    private  Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();


    public TopPanelStartSeite() {
        this.setSizeFull();



        HorizontalLayout horizon = new HorizontalLayout();

//        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);

        String vorname = null;

        if(user != null) {
            vorname = user.getName();
        }

        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/stealthyalda1.png"));
        Image Logo = new Image("", resource);
        GridLayout gridTop = new GridLayout(8, 1);
        gridTop.setSizeFull();

         gridTop.addComponent(Logo,0,0);
        Button buttonFürStudent = new Button("Für Studenten");

        buttonFürStudent.addStyleName(ValoTheme.BUTTON_LINK);

        buttonFürStudent.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                 UI.getCurrent().getNavigator().navigateTo(Views.REGISTERFÜRSTUDENT);
            }
        });
        gridTop.addComponent(buttonFürStudent,5,0);

        Button buttonFürArbeitgeber = new Button("Für Arbeitgeber");
        buttonFürArbeitgeber.addStyleName(ValoTheme.BUTTON_LINK);
        buttonFürArbeitgeber.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                 UI.getCurrent().getNavigator().navigateTo(Views.REGISTERFÜRARBEITGEBER);
            }
        });
        gridTop.addComponent(buttonFürArbeitgeber,6,0);

                
        Button buttonAnmelden = new Button("Anmelden");
        buttonAnmelden.addStyleName(ValoTheme.BUTTON_LINK);
        buttonAnmelden.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                 UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
            }
        });
                gridTop.addComponent(buttonAnmelden,7,0);
                gridTop.setComponentAlignment(buttonFürStudent,Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonFürArbeitgeber,Alignment.MIDDLE_RIGHT);
        gridTop.setComponentAlignment(buttonAnmelden,Alignment.MIDDLE_RIGHT);
//        gridTop.setComponentAlignment(gridBar, Alignment.BOTTOM_RIGHT);
//        gridTop.setComponentAlignment(Logo, Alignment.TOP_LEFT);

        //gridTop.setComponentAlignment(loggedLabel2, Alignment.TOP_RIGHT);
        //grid.setWidthFull();
//        panel.addStyleName("login");
      

        this.addComponent(gridTop);




    }

}