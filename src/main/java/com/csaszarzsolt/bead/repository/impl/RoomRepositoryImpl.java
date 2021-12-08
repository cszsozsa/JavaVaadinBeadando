package com.csaszarzsolt.bead.repository.impl;

import com.csaszarzsolt.bead.entity.Room;
import com.csaszarzsolt.bead.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class RoomRepositoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findAll() throws Exception{
        //return em.createQuery("select n from " + Room.class.getSimpleName() + " n").getResultList();
        return em.createNamedQuery(Room.FIND_ALL).getResultList();
    }

    @Override
    public void save(Room room) throws Exception{
        em.persist(room);
    }

    @Override
    public void delete(Long id) throws Exception{
        em.remove(findById(id));
    }

    @Override
    public void update(Room room) throws Exception{
        em.merge(room);
    }

    @Override
    public Room findById(Long id) throws Exception{
        return em.find(Room.class, id);
    }
}