/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.Internship;
import fon.sims_backend.enums.Grade;
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
public class InternshipRepo implements MyRepository<Internship, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Internship> findAll() {
        return entityManager.createQuery(
                "SELECT DISTINCT i FROM Internship i "
                + "JOIN FETCH i.teacher "
                + "JOIN FETCH i.examPeriod "
                + "JOIN FETCH i.report "
                + "JOIN FETCH i.studentOfficer so "
                + "JOIN FETCH so.studyLevel "
                + "JOIN FETCH i.company c "
                + "JOIN FETCH c.city ci "
                + "JOIN FETCH ci.country "
                + "JOIN FETCH i.student s "
                + "JOIN FETCH s.City sc "
                + "JOIN FETCH sc.country "
                + "JOIN FETCH s.studyProgram sp "
                + "JOIN FETCH sp.studyLevel "
                + "LEFT JOIN FETCH s.module", Internship.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Internship findByID(Long id) throws Exception {
        List<Internship> result = entityManager.createQuery(
                "SELECT i FROM Internship i "
                + "JOIN FETCH i.teacher "
                + "JOIN FETCH i.examPeriod "
                + "JOIN FETCH i.report "
                + "JOIN FETCH i.studentOfficer so "
                + "JOIN FETCH so.studyLevel "
                + "JOIN FETCH i.company c "
                + "JOIN FETCH c.city ci "
                + "JOIN FETCH ci.country "
                + "JOIN FETCH i.student s "
                + "JOIN FETCH s.City sc "
                + "JOIN FETCH sc.country "
                + "JOIN FETCH s.studyProgram sp "
                + "JOIN FETCH sp.studyLevel "
                + "LEFT JOIN FETCH s.module "
                + "WHERE i.idInternship = :id", Internship.class)
                .setParameter("id", id)
                .getResultList();
        if (result.isEmpty()) {
            throw new Exception("Studentska praksa nije pronađena!");
        }
        return result.get(0);
    }

    @Transactional
    public List<Internship> findByInternship(LocalDate startDate, LocalDate endDate, LocalDate defenseDate, Grade grade, Long idTeacher, Long idExamPeriod, Long idStudentOfficer, Long idCompany, Long idStudent) {
        String query
                = "SELECT i FROM Internship i "
                + "JOIN FETCH i.teacher "
                + "JOIN FETCH i.examPeriod "
                + "JOIN FETCH i.studentOfficer so "
                + "JOIN FETCH i.company c "
                + "LEFT JOIN FETCH i.student s WHERE 1=1 ";
        if (startDate != null) {
            query += "AND i.startDate = :startDate ";
        }
        if (endDate != null) {
            query += "AND i.endDate = :endDate ";
        }
        if (defenseDate != null) {
            query += "AND i.defenseDate = :defenseDate ";
        }
        if (grade != null) {
            query += "AND i.grade = :grade ";
        }
        if (idTeacher != null) {
            query += "AND i.teacher.idTeacher = :idTeacher ";
        }
        if (idExamPeriod != null) {
            query += "AND i.examPeriod.idExamPeriod = :idExamPeriod ";
        }
        if (idStudentOfficer != null) {
            query += "AND i.studentOfficer.idStudentOfficer = :idStudentOfficer ";
        }
        if (idCompany != null) {
            query += "AND i.company.idCompany = :idCompany ";
        }
        if (idStudent != null) {
            query += "AND i.student.idStudent = :idStudent ";
        }
        var q = entityManager.createQuery(query, Internship.class);
        if (startDate != null) {
            q.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            q.setParameter("endDate", endDate);
        }
        if (defenseDate != null) {
            q.setParameter("defenseDate", defenseDate);
        }
        if (grade != null) {
            q.setParameter("grade", grade);
        }
        if (idTeacher != null) {
            q.setParameter("idTeacher", idTeacher);
        }
        if (idExamPeriod != null) {
            q.setParameter("idExamPeriod", idExamPeriod);
        }
        if (idStudentOfficer != null) {
            q.setParameter("idStudentOfficer", idStudentOfficer);
        }
        if (idCompany != null) {
            q.setParameter("idCompany", idCompany);
        }
        if (idStudent != null) {
            q.setParameter("idStudent", idStudent);
        }
        return q.getResultList();
    }

    @Override
    @Transactional
    public void save(Internship entity) {
        if (entity.getIdInternship() == null) {
            entityManager.persist(entity);
            entityManager.flush();
        } else {
            entityManager.merge(entity);
        }
    }

    @Transactional
    public void deleteByID(Long id) {
        Internship internship = entityManager.find(Internship.class, id);
        if (internship != null) {
            entityManager.remove(internship);
        }
    }
}
