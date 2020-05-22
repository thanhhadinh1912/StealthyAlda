package com.stealthyalda.gui.components;
import com.stealthyalda.ai.control.LoginControl;
import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;


public class TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();



        HorizontalLayout horizon = new HorizontalLayout();

//        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        Benutzer user = ((MyUI) UI.getCurrent()).getBenutzer();

        String vorname = null;

        if(user != null) {
            vorname = user.getVorname();
        }

        Label loggedLabel = new Label("Welcome: " + user.getVorname() + "!");
        loggedLabel.setSizeUndefined();
        loggedLabel.addStyleName("loggedLabel");

        Label loggedLabel2 = new Label("Welcome text: !");
        loggedLabel2.setSizeUndefined();
        loggedLabel2.addStyleName("loggedLabel");




        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menü", null);

        item1.addItem("Logout", FontAwesome.SIGN_OUT, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                LoginControl.logoutUser();
            }
        });

//        if(user.hasRole(Roles.POWER_USER)) {
//            item1.addItem("Cancel", FontAwesome.UNLINK, new MenuBar.Command() {
//                @Override
//                public void menuSelected(MenuBar.MenuItem menuItem) {
//
//                    ListBookingWindow window = new ListBookingWindow();
//                    UI.getCurrent().addWindow(window);
//                }
//            });
//        }


        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath +
                "/Image/Logo3.png"));
        Image Logo = new Image("", resource);

        GridLayout gridTop = new GridLayout(4, 1);
        gridTop.setSizeFull();

        GridLayout gridBar = new GridLayout(2, 1);
        gridTop.setSizeFull();


        gridBar.addComponent(bar,1,0);
        gridBar.addComponent(loggedLabel,0,0);


        gridTop.addComponent(gridBar,3,0);
        gridTop.addComponent(Logo,0,0);

        gridTop.setComponentAlignment(gridBar, Alignment.BOTTOM_RIGHT);
        gridTop.setComponentAlignment(Logo, Alignment.TOP_LEFT);

        //gridTop.setComponentAlignment(loggedLabel2, Alignment.TOP_RIGHT);
        //grid.setWidthFull();
        Panel panel2 = new Panel( "Mein Job-Portal");
        panel2.addStyleName("login");
        panel2.setContent(gridTop);

        this.addComponent(panel2);
        this.setComponentAlignment(panel2,Alignment.TOP_RIGHT);




    }

}