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
    public List<Teacher> findAll() {
        return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
    }

    @Override
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

    @Override
    public void deleteByID(Long id) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        if (teacher != null) {
            entityManager.remove(teacher);
        }
    }

}
