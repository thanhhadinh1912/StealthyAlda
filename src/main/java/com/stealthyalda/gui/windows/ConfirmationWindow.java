/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.windows;

/**
 * @author WINDOWS
 */

import com.vaadin.ui.*;

public class ConfirmationWindow extends Window {
    public ConfirmationWindow(String text) {
        super("Confirmation"); //Set Window caption
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        content.setMargin(true);
        setContent(content);

        Button buchungsButton = new Button("OK");
        buchungsButton.addClickListener(clickEvent -> close());
        content.addComponent(buchungsButton);
        content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER);
    }
}
