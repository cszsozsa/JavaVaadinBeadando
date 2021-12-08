package com.csaszarzsolt.bead.view;

import com.csaszarzsolt.bead.entity.Course;
import com.csaszarzsolt.bead.entity.Room;
import com.csaszarzsolt.bead.repository.CourseRepository;
import com.csaszarzsolt.bead.repository.RoomRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "room/view")
public class RoomPageView extends AbstractView implements HasUrlParameter<String> {

    @Autowired
    private RoomRepository repository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        try {
            addMenuBar();
            Room room = repository.findById(Long.parseLong(s));

            add(new Label("Room: " + room.getName()));

            Grid<Course> grid=new Grid<>();
            grid.setItems(courseRepository.findAllByRoomId(room.getId()));
            grid.addColumn(Course::getId).setHeader("ID").setWidth("6%");
            grid.addColumn(Course::getName).setHeader("Course name").setWidth("94%");

            add(grid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
