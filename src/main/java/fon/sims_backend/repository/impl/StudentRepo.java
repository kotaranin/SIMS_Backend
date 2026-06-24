/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.Student;
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
public class StudentRepo implements MyRepository<Student, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Student> findAll() {
        return entityManager.createQuery(
                "SELECT DISTINCT s FROM Student s "
                + "JOIN FETCH s.City c "
                + "JOIN FETCH c.country "
                + "JOIN FETCH s.studyProgram sp "
                + "JOIN FETCH sp.studyLevel "
                + "LEFT JOIN FETCH s.module", Student.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Student findByID(Long id) throws Exception {
        List<Student> result = entityManager.createQuery(
                "SELECT s FROM Student s "
                + "JOIN FETCH s.City c "
                + "JOIN FETCH c.country "
                + "JOIN FETCH s.studyProgram sp "
                + "JOIN FETCH sp.studyLevel "
                + "LEFT JOIN FETCH s.module "
                + "WHERE s.idStudent = :id", Student.class)
                .setParameter("id", id)
                .getResultList();
        if (result.isEmpty()) {
            throw new Exception("Student nije pronađen!");
        }
        return result.get(0);
    }

    @Transactional
    public List<Student> findByStudent(String indexNumber, String firstName, String lastName, LocalDate dateOfBirth, Integer yearOfStudy, Long idCity, Long idStudyProgram, Long idModule) {
        String query
                = "SELECT s FROM Student s "
                + "JOIN FETCH s.City c "
                + "JOIN FETCH c.country "
                + "JOIN FETCH s.studyProgram sp "
                + "JOIN FETCH sp.studyLevel "
                + "LEFT JOIN FETCH s.module WHERE 1=1 ";
        if (indexNumber != null) {
            query += "AND s.indexNumber = :indexNumber ";
        }
        if (firstName != null) {
            query += "AND LOWER(s.firstName) LIKE LOWER(:firstName) ";
        }
        if (lastName != null) {
            query += "AND LOWER(s.lastName) LIKE LOWER(:lastName) ";
        }
        if (dateOfBirth != null) {
            query += "AND s.dateOfBirth = :dateOfBirth ";
        }
        if (yearOfStudy != null) {
            query += "AND s.yearOfStudy = :yearOfStudy ";
        }
        if (idCity != null) {
            query += "AND s.City.idCity = :idCity ";
        }
        if (idStudyProgram != null) {
            query += "AND s.studyProgram.idStudyProgram = :idStudyProgram ";
        }
        if (idModule != null) {
            query += "AND s.module.idModule = :idModule ";
        }
        var q = entityManager.createQuery(query, Student.class);
        if (indexNumber != null) {
            q.setParameter("indexNumber", indexNumber);
        }
        if (firstName != null) {
            q.setParameter("firstName", "%" + firstName + "%");
        }
        if (lastName != null) {
            q.setParameter("lastName", "%" + lastName + "%");
        }
        if (dateOfBirth != null) {
            q.setParameter("dateOfBirth", dateOfBirth);
        }
        if (yearOfStudy != null) {
            q.setParameter("yearOfStudy", yearOfStudy);
        }
        if (idCity != null) {
            q.setParameter("idCity", idCity);
        }
        if (idStudyProgram != null) {
            q.setParameter("idStudyProgram", idStudyProgram);
        }
        if (idModule != null) {
            q.setParameter("idModule", idModule);
        }
        return q.getResultList();
    }

    @Override
    @Transactional
    public void save(Student entity) {
        if (entity.getIdStudent() == null) {
            entityManager.persist(entity);
            entityManager.flush();
        } else {
            entityManager.merge(entity);
        }
    }

    @Transactional
    public Long countAll() {
        return entityManager.createQuery(
                "SELECT COUNT(s) FROM Student s", Long.class)
                .getSingleResult();
    }
}
