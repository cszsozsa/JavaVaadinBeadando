package com.csaszarzsolt.bead.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Course.FIND_ALL, query = "select n from Course n"),
        @NamedQuery(name = Course.FIND_BY_ROOM_ID, query = "select n from Course n where n.room.id=:roomId")
})
@Table
@Entity
public class Course {
    public static final String FIND_ALL = "Course.FIND_ALL";
    public static final String FIND_BY_ROOM_ID = "Course.findAllByRoomId";

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //private Integer roomId;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }*/

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
