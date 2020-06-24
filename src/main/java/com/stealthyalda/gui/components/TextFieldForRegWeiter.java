package com.stealthyalda.gui.components;


import com.vaadin.ui.TextField;

public class TextFieldForRegWeiter extends TextField {
    public TextFieldForRegWeiter(String caption, String WIDTH){
        this.setPlaceholder(caption);
        this.setWidth(WIDTH);
    }
}
