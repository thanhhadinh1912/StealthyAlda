/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.windows;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

/**
 * @author WINDOWS
 */
public class ConfirmReg extends Window {

    public ConfirmReg(String text, String view) {
        super("Success!"); //Set Window caption
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        content.setMargin(true);
        setContent(content);

        Button button = new Button("OK");
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        button.addClickListener(clickEvent -> {
            close();
            UI.getCurrent().getNavigator().navigateTo(view);

        });
        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }
}
