package com.stealthyalda.gui.views;

import com.stealthyalda.gui.components.TopPanel;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

public class Stellenanzeige extends VerticalLayout implements View {
    public void setup(){
        this.addComponent(new TopPanel());
        setMargin(true);

        Panel panel = new Panel();
        panel.addStyleName("login");
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("800px");
        Button zuruck = new Button("Zurück");
        Button bewerben = new Button("Bewerben");
        horizontalLayout.addComponent(zuruck);
        horizontalLayout.setComponentAlignment(zuruck, Alignment.MIDDLE_LEFT);
        horizontalLayout.addComponent(bewerben);
        content.addComponent(horizontalLayout);
        horizontalLayout.setComponentAlignment(bewerben, Alignment.MIDDLE_RIGHT);
        content.setComponentAlignment(horizontalLayout,Alignment.BOTTOM_CENTER);

        panel.setContent(content);

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setup();
    }

}
