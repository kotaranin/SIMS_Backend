/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.Report;
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
public class ReportRepo implements MyRepository<Report, Long>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Report> findAll() {
        return entityManager.createQuery("SELECT r FROM Report r", Report.class).getResultList();
    }

    @Override
    @Transactional
    public Report findByID(Long id) throws Exception {
        Report report = entityManager.find(Report.class, id);
        if (report == null) {
            throw new Exception("Dokument nije pronađen!");
        }
        return report;
    }

    @Override
    @Transactional
    public void save(Report entity) {
        if (entity.getIdReport() == null) {
            entityManager.persist(entity);
            entityManager.flush();
        } else {
            entityManager.merge(entity);
        }
    }

    public void deleteByID(Long id) {
        Report report = entityManager.find(Report.class, id);
        if (report != null) {
            entityManager.remove(report);
        }
    }
}
