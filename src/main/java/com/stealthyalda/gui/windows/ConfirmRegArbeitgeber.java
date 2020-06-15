/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.stealthyalda.gui.ui.MyUI;
import com.stealthyalda.services.util.Views;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

/**
 * @author WINDOWS
 */
public class ConfirmRegArbeitgeber extends Window {


    public ConfirmRegArbeitgeber(String text) {
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
            UI.getCurrent().getNavigator().navigateTo(Views.REGWEITERA);
         

        });
        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }
}
