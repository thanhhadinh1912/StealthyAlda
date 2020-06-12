package com.stealthyalda.gui.views;


import com.stealthyalda.ai.model.entities.Benutzer;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

public class Register extends VerticalLayout implements View {
    static final String STUDENT = "Student";
    static final String ARBEITGEBER = "Arbeitgeber";
    static final String FEHLER = "Fehler";
    static final String HORIZONTAL_WIDTH = "400px";
    final Binder<Benutzer> binder = new Binder<>();
}
