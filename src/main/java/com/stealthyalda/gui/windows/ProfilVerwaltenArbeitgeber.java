/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stealthyalda.gui.windows;

import com.stealthyalda.ai.model.entities.Benutzer;
import com.vaadin.ui.Window;

/**
 *
 * @author WINDOWS
 */
public class ProfilVerwaltenArbeitgeber extends Window {
     public ProfilVerwaltenArbeitgeber(Benutzer benutzer) {
        super("Profil Verwalten"); //set window caption
        center();
        this.setHeight("600px");
        this.setWidth("1000px");
     }
    
}
