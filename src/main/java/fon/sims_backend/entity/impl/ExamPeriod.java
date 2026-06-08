/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.entity.impl;

import fon.sims_backend.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author kotar
 */
@Entity
@Table(name = "exam_period")
public class ExamPeriod implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExamPeriod;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public ExamPeriod() {
    }

    public ExamPeriod(Long idExamPeriod, String name, LocalDate startDate, LocalDate endDate) {
        this.idExamPeriod = idExamPeriod;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getIdExamPeriod() {
        return idExamPeriod;
    }

    public void setIdExamPeriod(Long idExamPeriod) {
        this.idExamPeriod = idExamPeriod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
