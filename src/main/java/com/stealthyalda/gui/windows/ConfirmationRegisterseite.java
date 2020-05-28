/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.windows;

/**
 *
 * @author WINDOWS
 */

import com.stealthyalda.services.util.Views;
import com.vaadin.ui.*;

public class ConfirmationRegisterseite extends Window {
    public ConfirmationRegisterseite(String text) {
        super("Success!"); //Set Window caption
        center();

        //Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        content.setMargin(true);
        setContent(content);

        Button button = new Button("OK");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
                UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);

            }
        });
        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }
}

