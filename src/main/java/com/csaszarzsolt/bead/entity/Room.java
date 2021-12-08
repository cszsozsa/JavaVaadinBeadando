package com.csaszarzsolt.bead.entity;

import javax.persistence.*;

@NamedQuery(name = Room.FIND_ALL, query = "select n from Room n")
@Table
@Entity
public class Room {
    public static final String FIND_ALL = "Room.findALl";

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer level;

    public Room() {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}