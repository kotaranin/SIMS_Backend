/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.StudentOfficer;
import fon.sims_backend.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kotar
 */
@Repository
public class StudentOfficerRepo implements MyRepository<StudentOfficer, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<StudentOfficer> findAll() {
        return entityManager.createQuery("SELECT s FROM StudentOfficer s", StudentOfficer.class).getResultList();
    }

    @Override
    @Transactional
    public StudentOfficer findByID(Long id) throws Exception {
        StudentOfficer studentOfficer = entityManager.find(StudentOfficer.class, id);
        if (studentOfficer == null) {
            throw new Exception("Službenik studentske službe nije pronađen!");
        }
        return studentOfficer;
    }

    @Override
    @Transactional
    public void save(StudentOfficer entity) {
        if (entity.getIdStudentOfficer() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    public Optional<StudentOfficer> findByEmail(String email) {
        try {
            StudentOfficer officer = entityManager.createQuery(
                "SELECT s FROM StudentOfficer s WHERE s.email = :email", StudentOfficer.class)
                .setParameter("email", email)
                .getSingleResult();
            return Optional.of(officer);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
