/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.StudyProgram;
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
public class StudyProgramRepo implements MyRepository<StudyProgram, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<StudyProgram> findAll() {
        return entityManager.createQuery("SELECT s FROM StudyProgram s", StudyProgram.class).getResultList();
    }

    @Override
    @Transactional
    public StudyProgram findByID(Long id) throws Exception {
        StudyProgram studyProgram = entityManager.find(StudyProgram.class, id);
        if (studyProgram == null) {
            throw new Exception("Studijski program nije pronađen!");
        }
        return studyProgram;
    }

    @Transactional
    public List<StudyProgram> findByStudyLevel(Long idStudyLevel) {
        return entityManager
                .createQuery(
                        "SELECT s "
                        + "FROM StudyProgram s "
                        + "JOIN FETCH s.studyLevel "
                        + "WHERE s.studyLevel.idStudyLevel = :idStudyLevel ", StudyProgram.class)
                .setParameter("idStudyLevel", idStudyLevel)
                .getResultList();
    }

    @Override
    @Transactional
    public void save(StudyProgram entity) {
        if (entity.getIdStudyProgram() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }
}
