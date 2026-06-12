/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.StudyLevel;
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
public class StudyLevelRepo implements MyRepository<StudyLevel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<StudyLevel> findAll() {
        return entityManager.createQuery("SELECT s FROM StudyLevel s", StudyLevel.class).getResultList();
    }

    @Override
    @Transactional
    public StudyLevel findByID(Long id) throws Exception {
        StudyLevel studyLevel = entityManager.find(StudyLevel.class, id);
        if (studyLevel == null) {
            throw new Exception("Nivo studija nije pronađen!");
        }
        return studyLevel;
    }

    @Transactional
    public List<StudyLevel> findByStudyLevel(String name) {
        return entityManager
                .createQuery("SELECT s FROM StudyLevel s WHERE LOWER(s.name) LIKE LOWER(:name)", StudyLevel.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    @Transactional
    public void save(StudyLevel entity) {
        if (entity.getIdStudyLevel() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

}
