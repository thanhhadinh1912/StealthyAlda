package com.stealthyalda.gui.components;

import com.vaadin.ui.ComboBox;

public class AnredeField extends ComboBox {
    public AnredeField() {
        this.setItems("Herr", "Frau");
        this.setPlaceholder("Anrede");
        this.setWidth("500px");
    }
}
