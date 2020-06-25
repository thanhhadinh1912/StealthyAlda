package com.stealthyalda.gui.views;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.components.TopPanel;
import com.stealthyalda.services.util.Roles;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

public class Stellenanzeige extends VerticalLayout implements View {
    public void setup() {
        Benutzer user = (Benutzer) VaadinSession.getCurrent().getAttribute(Roles.CURRENTUSER);
        this.addComponent(new TopPanel(user));
        setMargin(true);

        Panel panel = new Panel();
        panel.addStyleName("login");
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("800px");
        Button zuruck = new Button("Zurück");
        /*ToogleRouter toogleRouter = new ToogleRouter();
        if(toogleRouter.isEnabled())*/
        Button bewerben = new Button("Bewerben");
        horizontalLayout.addComponent(zuruck);
        horizontalLayout.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);
        horizontalLayout.addComponent(bewerben);
        content.addComponent(horizontalLayout);
        horizontalLayout.setComponentAlignment(bewerben, Alignment.MIDDLE_RIGHT);
        content.setComponentAlignment(horizontalLayout, Alignment.BOTTOM_CENTER);

        panel.setContent(content);

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setup();
    }

}
