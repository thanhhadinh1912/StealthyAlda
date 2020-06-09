/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.windows;

import com.stealthyalda.services.util.Views;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author WINDOWS
 */
public class ConfirmRegStudent extends Window {
   
    public ConfirmRegStudent(String text) {
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
                    UI.getCurrent().getNavigator().navigateTo(Views.REGWEITERS);

            }
        });
        content.addComponent(button);
        content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }
}
