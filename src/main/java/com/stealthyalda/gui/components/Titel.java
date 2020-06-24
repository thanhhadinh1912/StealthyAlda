package com.stealthyalda.gui.components;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Titel extends Label {
    public Titel(String caption, ContentMode html){
        this.addStyleName("mytitle");
        this.addStyleName(ValoTheme.LABEL_H1);
    }
}
