/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.entity.impl;

import fon.sims_backend.entity.MyEntity;
import fon.sims_backend.enums.Grade;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author kotar
 */
@Entity
@Table(name = "internship")
public class Internship implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInternship;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate defenseDate;
    private Grade grade;
    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "id_exam_period")
    private ExamPeriod examPeriod;
    @ManyToOne
    @JoinColumn(name = "id_report")
    private Report report;
    @ManyToOne
    @JoinColumn(name = "id_student_officer")
    private StudentOfficer studentOfficer;
    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    public Internship() {
    }

    public Internship(Long idInternship, LocalDate startDate, LocalDate endDate, LocalDate defenseDate, Grade grade, Teacher teacher, ExamPeriod examPeriod, Report report, StudentOfficer studentOfficer, Company company, Student student) {
        this.idInternship = idInternship;
        this.startDate = startDate;
        this.endDate = endDate;
        this.defenseDate = defenseDate;
        this.grade = grade;
        this.teacher = teacher;
        this.examPeriod = examPeriod;
        this.report = report;
        this.studentOfficer = studentOfficer;
        this.company = company;
        this.student = student;
    }

    public Long getIdInternship() {
        return idInternship;
    }

    public void setIdInternship(Long idInternship) {
        this.idInternship = idInternship;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDefenseDate() {
        return defenseDate;
    }

    public void setDefenseDate(LocalDate defenseDate) {
        this.defenseDate = defenseDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ExamPeriod getExamPeriod() {
        return examPeriod;
    }

    public void setExamPeriod(ExamPeriod examPeriod) {
        this.examPeriod = examPeriod;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public StudentOfficer getStudentOfficer() {
        return studentOfficer;
    }

    public void setStudentOfficer(StudentOfficer studentOfficer) {
        this.studentOfficer = studentOfficer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
