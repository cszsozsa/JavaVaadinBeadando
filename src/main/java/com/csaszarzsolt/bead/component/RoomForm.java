package com.csaszarzsolt.bead.component;

import com.csaszarzsolt.bead.entity.Room;
import com.csaszarzsolt.bead.util.RefreshAware;
import com.csaszarzsolt.bead.repository.RoomRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class RoomForm extends VerticalLayout {

    private Room room;

    @Autowired
    private RoomRepository repository;

    private Binder<Room> binder;

    private TextField name;
    private ListBox<Integer> level;
    private RefreshAware refreshAware;

    @PostConstruct
    private void init() {
        binder = new Binder<>(Room.class);

        name = new TextField("Name");
       // level = new TextField("level");
        level = new ListBox<>();
        Text levelLabel = new Text("Level");
        level.setItems(0, 1, 2, 3);
        add(name, /*level,*/ levelLabel , level/* doorNumber*/);

        HorizontalLayout buttonBar = new HorizontalLayout();

        Button saveBtn = new Button("Save", VaadinIcon.PENCIL.create());
        saveBtn.addClickListener(event -> {
            try {
                if (room.getId() == null) {
                    repository.save(room);
                } else {
                    repository.update(room);
                }
                setVisible(false);
                refreshAware.processRefresh();
                Notification.show("Success!",3000, Notification.Position.TOP_CENTER);
            } catch (Exception e) {
                Notification.show(":(");
                e.printStackTrace();
            }
        });

        Button deleteBtn = new Button("Delete", VaadinIcon.TRASH.create());
        deleteBtn.addClickListener(event -> {
            try {
                repository.delete(room.getId());
                setVisible(false);
                refreshAware.processRefresh();
                Notification.show("Success!",3000, Notification.Position.TOP_CENTER);
            } catch (Exception e) {
                Notification.show(":(");
                e.printStackTrace();
            }
        });

        Button cancelBtn = new Button("Cancel", VaadinIcon.CLOSE.create());
        cancelBtn.addClickListener(event -> {
            room = null;
            setVisible(false);
        });

        Button viewPage = new Button("About", VaadinIcon.QUESTION.create());
        viewPage.addClickListener(e -> {
            if(room.getId() != null){
                UI.getCurrent().navigate("room/view/"+room.getId());
            } else {
                Notification.show("Select something!", 3000, Notification.Position.TOP_CENTER);
            }
        });

        buttonBar.add(saveBtn, deleteBtn, cancelBtn,viewPage);

        add(buttonBar);
        setVisible(false);
        binder.bindInstanceFields(this);
        name.focus();
    }

    public void initSave() {
        room = new Room();
        binder.setBean(room);
        setVisible(true);
    }

    public void initEdit(Long id) {

        try {
            this.room = repository.findById(id);
            binder.setBean(room);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRefreshAware(RefreshAware refreshAware){
        this.refreshAware = refreshAware;
    }
}
