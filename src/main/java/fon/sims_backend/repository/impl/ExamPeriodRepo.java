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
import java.time.LocalDate;
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
    @Transactional
    public List<ExamPeriod> findAll() {
        return entityManager.createQuery("SELECT e FROM ExamPeriod e", ExamPeriod.class).getResultList();
    }

    @Override
    @Transactional
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

    @Transactional
    public List<ExamPeriod> findByExamPeriod(String name, LocalDate startDate, LocalDate endDate) {
        String query = "SELECT e FROM ExamPeriod e WHERE 1=1 ";
        if (name != null) {
            query += "AND LOWER(e.name) LIKE LOWER(:name) ";
        }
        if (startDate != null) {
            query += "AND e.startDate = :startDate ";
        }
        if (endDate != null) {
            query += "AND e.endDate = :endDate";
        }
        var q = entityManager.createQuery(query, ExamPeriod.class);
        if (name != null) {
            q.setParameter("name", "%" + name + "%");
        }
        if (startDate != null) {
            q.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            q.setParameter("endDate", endDate);
        }
        return q.getResultList();
    }

}
