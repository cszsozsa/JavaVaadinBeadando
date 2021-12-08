package com.csaszarzsolt.bead.repository;

import com.csaszarzsolt.bead.entity.Course;

import java.util.List;

public interface CourseRepository {

    List<Course> findAll() throws Exception;

    List<Course> findAllByRoomId(Long id) throws Exception;

    void save(Course course) throws Exception;

    void update(Course course) throws Exception;

    void delete(Long id) throws Exception;

    Course findById(Long id) throws Exception;
}
