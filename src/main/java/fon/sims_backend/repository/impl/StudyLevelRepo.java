/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.StudyLevel;
import fon.sims_backend.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author kotar
 */
public class StudyLevelRepo implements MyRepository<StudyLevel, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<StudyLevel> findAll() {
        return entityManager.createQuery("SELECT s FROM StudyLevel s", StudyLevel.class).getResultList();
    }

    @Override
    public StudyLevel findByID(Long id) throws Exception {
        StudyLevel studyLevel = entityManager.find(StudyLevel.class, id);
        if (studyLevel == null) {
            throw new Exception("Nivo studija nije pronađen!");
        }
        return studyLevel;
    }

    @Override
    public void save(StudyLevel entity) {
        if (entity.getIdStudyLevel() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        StudyLevel studyLevel = entityManager.find(StudyLevel.class, id);
        if (studyLevel != null) {
            entityManager.remove(studyLevel);
        }
    }
}
