package com.csaszarzsolt.bead.repository;

import com.csaszarzsolt.bead.entity.Room;

import java.util.List;

public interface RoomRepository {

    List<Room> findAll() throws Exception;

    void save(Room room) throws Exception;

    void update(Room room) throws Exception;

    void delete(Long id) throws Exception;

    Room findById(Long id) throws Exception;
}