/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.ExamPeriod;
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
public class ExamPeriodRepo implements MyRepository<ExamPeriod, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ExamPeriod> findAll() {
        return entityManager.createQuery("SELECT e FROM ExamPeriod e", ExamPeriod.class).getResultList();
    }

    @Override
    public ExamPeriod findByID(Long id) throws Exception {
        ExamPeriod examPeriod = entityManager.find(ExamPeriod.class, id);
        if (examPeriod == null) {
            throw new Exception("Ispitni rok nije pronađen!");
        }
        return examPeriod;
    }

    @Override
    @Transactional
    public void save(ExamPeriod entity) {
        if (entity.getIdExamPeriod() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        ExamPeriod examPeriod = entityManager.find(ExamPeriod.class, id);
        if (examPeriod != null) {
            entityManager.remove(examPeriod);
        }
    }
}
