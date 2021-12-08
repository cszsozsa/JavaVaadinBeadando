package com.csaszarzsolt.bead.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Course management")
@Route
public class MainView extends AbstractView {

    public MainView(){
        //Label lbl = new Label("Course manager");
        /*
        Button subBtn = new Button("Sub View");
        subBtn.addClickListener(event -> {
            subBtn.getUI().ifPresent(ui -> ui.navigate("sub"));
        });

        Button roomBtn = new Button("Rooms");
        roomBtn.addClickListener(event -> {
            roomBtn.getUI().ifPresent(ui -> ui.navigate("room"));
        });

        Button courseBtn = new Button("Courses");
        courseBtn.addClickListener(event -> {
            courseBtn.getUI().ifPresent(ui -> ui.navigate("course"));
        });

        // Elements to menuBar
        HorizontalLayout menuBar = new HorizontalLayout();
        menuBar.add(subBtn, roomBtn, courseBtn);*/

        //MenuBar menu = new MenuBar();
        //menu.initMenu();
        // Elements to page
        //add(lbl);
        addMenuBar();
        Button button = new Button("Szia");
        button.addClickListener(e -> Notification.show("Hellllooo"));
        add(button);

        //add(menu);
        /*add(menuBar);*/
    }
}
