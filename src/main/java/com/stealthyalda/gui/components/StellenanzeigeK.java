package com.stealthyalda.gui.components;


import com.stealthyalda.ai.control.StellenanzeigeControl;
import com.stealthyalda.ai.model.dtos.StellenanzeigeDTO;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class StellenanzeigeK extends Window {
    public StellenanzeigeK(int stellenanzeige_id){
        center();
        StellenanzeigeDTO jobangebot = new StellenanzeigeControl().get(stellenanzeige_id);


        VerticalLayout content = new VerticalLayout();
        HorizontalLayout top = new HorizontalLayout();

        VerticalLayout titel = new VerticalLayout();

    }
}
