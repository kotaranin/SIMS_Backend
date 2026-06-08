/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.enums.Grade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 * @author kotar
 */
public class InternshipDTO {

    private Long idInternship;
    @NotNull(message = "Datum početka je obavezno polje!")
    @Valid
    private LocalDate startDate;
    @NotNull(message = "Datum kraja je obavezno polje!")
    private LocalDate endDate;
    @NotNull(message = "Datum odbrane je obavezno polje!")
    private LocalDate defenseDate;
    @NotNull(message = "Ocena je obavezno polje!")
    private Grade grade;
    @NotNull(message = "Nastavnik je obavezno polje!")
    @Valid
    private TeacherDTO teacher;
    @NotNull(message = "Ispitni rok je obavezno polje!")
    @Valid
    private ExamPeriodDTO examPeriod;
    @NotNull(message = "Dokument je obavezno polje!")
    @Valid
    private ReportDTO report;
    @NotNull(message = "Službenik studentske službe je obavezno polje!")
    @Valid
    private StudentOfficerDTO studentOfficer;
    @NotNull(message = "Kompanija je obavezno polje!")
    @Valid
    private CompanyDTO company;
    @NotNull(message = "Student je obavezno polje!")
    @Valid
    private StudentDTO student;

    public InternshipDTO() {
    }

    public InternshipDTO(Long idInternship, LocalDate startDate, LocalDate endDate, LocalDate defenseDate, Grade grade, TeacherDTO teacher, ExamPeriodDTO examPeriod, ReportDTO report, StudentOfficerDTO studentOfficer, CompanyDTO company, StudentDTO student) {
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

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public ExamPeriodDTO getExamPeriod() {
        return examPeriod;
    }

    public void setExamPeriod(ExamPeriodDTO examPeriod) {
        this.examPeriod = examPeriod;
    }

    public ReportDTO getReport() {
        return report;
    }

    public void setReport(ReportDTO report) {
        this.report = report;
    }

    public StudentOfficerDTO getStudentOfficer() {
        return studentOfficer;
    }

    public void setStudentOfficer(StudentOfficerDTO studentOfficer) {
        this.studentOfficer = studentOfficer;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

}
