package com.stealthyalda.gui.views;

import com.stealthyalda.gui.components.RegisterseiteMainComponent;
import com.stealthyalda.gui.components.TopPanelStartSeite;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.themes.ValoTheme;

public class RegisterseiteFuerArbeitgeber extends Register {

    public void setUp() {
        this.addComponent(new TopPanelStartSeite());
        RadioButtonGroup<String> single = new RadioButtonGroup<>();
        single.setItems(ARBEITGEBER, STUDENT);
        single.setValue(ARBEITGEBER);
        single.setItemEnabledProvider(role -> !STUDENT.equals(role));
        single.setValue(ARBEITGEBER);
        single.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        Panel panel = new RegisterseiteMainComponent(single);
        setMargin(true);
        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        this.setUp();

    }
}