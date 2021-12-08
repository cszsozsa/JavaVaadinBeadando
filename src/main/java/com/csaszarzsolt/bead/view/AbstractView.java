package com.csaszarzsolt.bead.view;

import com.csaszarzsolt.bead.component.CustomMenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = Lumo.class, variant = Lumo.DARK)
abstract class AbstractView extends VerticalLayout {
    void addMenuBar(){
        //setWidth("50%");
        //setAlignSelf(Alignment.CENTER);
        //setAlignItems(Alignment.CENTER);

        setWidth("40%");
        add(new CustomMenuBar());
    }
}
