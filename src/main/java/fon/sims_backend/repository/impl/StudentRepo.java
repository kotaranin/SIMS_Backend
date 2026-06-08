/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.Student;
import fon.sims_backend.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author kotar
 */
public class StudentRepo implements MyRepository<Student, Long>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    @Override
    public Student findByID(Long id) throws Exception {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            throw new Exception("Student nije pronađen!");
        }
        return student;
    }

    @Override
    public void save(Student entity) {
        if (entity.getIdStudent() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
    }
}
