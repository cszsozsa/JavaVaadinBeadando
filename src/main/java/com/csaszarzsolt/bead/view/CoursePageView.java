package com.csaszarzsolt.bead.view;

import com.csaszarzsolt.bead.entity.Course;
import com.csaszarzsolt.bead.repository.CourseRepository;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "course/view")
public class CoursePageView extends AbstractView implements HasUrlParameter<String> {

    @Autowired
    private CourseRepository repository;

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        try {
            addMenuBar();
            Course course = repository.findById(Long.parseLong(s));
            if(course.getRoom() !=null){
                add(new Label("Room name: "+course.getRoom().getName()));
            }
            add(new Label("Name: "+course.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
