package com.stealthyalda.gui.windows;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class StellenanzeigeWindow extends Window {
    public StellenanzeigeWindow(){
        center();
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button zuruck = new Button("Zurück");
        Button bewerben = new Button("Bewerben");
        horizontalLayout.addComponent(zuruck);
        horizontalLayout.addComponent(bewerben);
        content.addComponent(horizontalLayout);
        setContent(content);

    }

}
