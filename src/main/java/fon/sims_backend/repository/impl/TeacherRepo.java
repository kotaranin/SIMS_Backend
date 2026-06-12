/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.Teacher;
import fon.sims_backend.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kotar
 */
@Repository
public class TeacherRepo implements MyRepository<Teacher, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Teacher> findAll() {
        return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
    }

    @Override
    @Transactional
    public Teacher findByID(Long id) throws Exception {
        Teacher teacher = entityManager.find(Teacher.class, id);
        if (teacher == null) {
            throw new Exception("Nastavnik nije pronađen!");
        }
        return teacher;
    }

    @Override
    @Transactional
    public void save(Teacher entity) {
        if (entity.getIdTeacher() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Transactional
    public List<Teacher> findByTeacher(String firstName, String lastName) {
        return entityManager.createQuery(
                "SELECT t "
                + "FROM Teacher t WHERE "
                + "LOWER(t.firstName) LIKE LOWER(:firstName) AND "
                + "LOWER(t.lastName) LIKE LOWER(:lastName)", Teacher.class)
                .setParameter("firstName", "%" + firstName + "%")
                .setParameter("lastName", "%" + lastName + "%")
                .getResultList();
    }

}
