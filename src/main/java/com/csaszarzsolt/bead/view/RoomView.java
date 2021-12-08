package com.csaszarzsolt.bead.view;

import com.csaszarzsolt.bead.component.RoomForm;
import com.csaszarzsolt.bead.entity.Room;
import com.csaszarzsolt.bead.repository.RoomRepository;
import com.csaszarzsolt.bead.util.RefreshAware;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@PageTitle("Room View")
@Route
public class RoomView extends AbstractView implements RefreshAware {

    @Autowired
    private RoomRepository repository;
    @Autowired
    private RoomForm roomForm;
    private Grid<Room> grid;

    @PostConstruct
    private void init() {
        try {
            if (repository.findAll().isEmpty()) {
                for (int i = 0; i < 5; i++) {
                    Room room = new Room();
                    room.setName("F");
                    room.setLevel(1);
                    repository.save(room);
                }
            }

            HorizontalLayout menuBar = new HorizontalLayout();

            Button mainBtn = new Button("Main page", VaadinIcon.ARROW_LEFT.create());
            mainBtn.addClickListener(event -> {
                mainBtn.getUI().ifPresent(ui -> ui.navigate(""));
            });

            grid = new Grid<>();
            grid.setItems(repository.findAll());
            grid.addColumn(Room::getId).setHeader("ID").setWidth("5%");
            grid.addColumn(Room::getName).setHeader("Name").setWidth("75%");
            grid.addColumn(Room::getLevel).setHeader("Level").setWidth("20%");
            grid.asSingleSelect().addValueChangeListener(e -> {
                if(e.getValue() != null){
                    roomForm.initEdit(e.getValue().getId());
                }
            });

            Button newBtn = new Button("New", VaadinIcon.PLUS.create());
            newBtn.addClickListener(event -> roomForm.initSave());

            menuBar.add(/*mainBtn ,*/newBtn);
            addMenuBar();
            add(menuBar);
            add(grid);
            add(roomForm);
            roomForm.setRefreshAware(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processRefresh(){
        try{
            grid.setItems(repository.findAll());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
