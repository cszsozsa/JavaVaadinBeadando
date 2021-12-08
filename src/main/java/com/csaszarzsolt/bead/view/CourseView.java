package com.csaszarzsolt.bead.view;


import com.csaszarzsolt.bead.component.CourseForm;
import com.csaszarzsolt.bead.entity.Course;
import com.csaszarzsolt.bead.entity.Room;
import com.csaszarzsolt.bead.repository.CourseRepository;
import com.csaszarzsolt.bead.util.RefreshAware;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.SortOrderProvider;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@PageTitle("Course View")
@Route
public class CourseView extends AbstractView implements RefreshAware {

    @Autowired
    private CourseRepository repository;
    @Autowired
    private CourseForm courseForm;
    private Grid<Course> grid;

    @PostConstruct
    private void init(){
        try{
            if(repository.findAll().isEmpty()){
                Course course = new Course();
                course.setName("A");
                repository.save(course);
            }

            grid = new Grid<>();
            grid.setItems(repository.findAll());
            grid.addColumn(Course::getId).setWidth("6%").setHeader("ID").setKey("1");
            grid.addColumn(Course::getName).setHeader("Name").setWidth("94%");
            //grid.addColumn(Course::getRoom).setHeader("Room name");
            grid.asSingleSelect().addValueChangeListener(e -> {
               if(e.getValue() != null){
                   courseForm.initEdit(e.getValue().getId());
               }
            });

            Button newBtn = new Button("New", VaadinIcon.PLUS.create());
            newBtn.addClickListener(e -> courseForm.initSave());

            addMenuBar();
            add(newBtn);
            add(grid);
            add(courseForm);
            courseForm.setRefreshAware(this);
        } catch (Exception e){
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
