/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.StudentOfficer;
import fon.sims_backend.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author kotar
 */
public class StudentOfficerRepo implements MyRepository<StudentOfficer, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudentOfficer> findAll() {
        return entityManager.createQuery("SELECT s FROM StudentOfficer s", StudentOfficer.class).getResultList();
    }

    @Override
    public StudentOfficer findByID(Long id) throws Exception {
        StudentOfficer studentOfficer = entityManager.find(StudentOfficer.class, id);
        if (studentOfficer == null) {
            throw new Exception("Službenik studentske službe nije pronađen!");
        }
        return studentOfficer;
    }

    @Override
    public void save(StudentOfficer entity) {
        if (entity.getIdStudentOfficer() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        StudentOfficer studentOfficer = entityManager.find(StudentOfficer.class, id);
        if (studentOfficer != null) {
            entityManager.remove(studentOfficer);
        }
    }
}
