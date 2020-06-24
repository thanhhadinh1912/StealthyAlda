package com.stealthyalda.gui.components;


import com.vaadin.ui.Button;

public class ButtonComponent extends Button {
    public ButtonComponent(String caption, String width){
        this.setCaption(caption);
        this.setWidth(width);
    }
}
