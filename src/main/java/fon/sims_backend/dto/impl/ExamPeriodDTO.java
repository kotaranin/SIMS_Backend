/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 * @author kotar
 */
public class ExamPeriodDTO implements DTO {

    private Long idExamPeriod;
    @NotNull(message = "Naziv je obavezno polje!")
    @NotEmpty(message = "Naziv je obavezno polje!")
    private String name;
    @NotNull(message = "Datum početka je obavezno polje!")
    private LocalDate startDate;
    @NotNull(message = "Datum kraja je obavezno polje!")
    private LocalDate endDate;

    public ExamPeriodDTO() {
    }

    public ExamPeriodDTO(Long idExamPeriod, String name, LocalDate startDate, LocalDate endDate) {
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
