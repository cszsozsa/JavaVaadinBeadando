package com.csaszarzsolt.bead.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CustomMenuBar extends HorizontalLayout {

    public CustomMenuBar(){
        setWidth("80%");
        //setVerticalComponentAlignment(Alignment.CENTER,this);

        addNavButton("Main view", VaadinIcon.HOME.create(), "");
        addNavButton("Sub view", VaadinIcon.SUITCASE.create(),"sub");
        addNavButton("Course view", VaadinIcon.DATABASE.create(),"course");
        addNavButton("Room view", VaadinIcon.DATABASE.create(), "room");
    }

    private void addNavButton(String name, Icon icon, String route){
        Button button = new Button(name, icon);
        button.addClickListener(e ->
            button.getUI().ifPresent(ui -> ui.navigate(route))
        );
        add(button);
    }

}
