/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author kotar
 */
public class TeacherDTO implements DTO {

    private Long idTeacher;
    @NotNull(message = "Ime je obavezno polje!")
    @NotEmpty(message = "Ime je obavezno polje!")
    private String firstName;
    @NotNull(message = "Prezime je obavezno polje!")
    @NotEmpty(message = "Prezime je obavezno polje!")
    private String lastName;

    public TeacherDTO() {
    }

    public TeacherDTO(Long idTeacher, String firstName, String lastName) {
        this.idTeacher = idTeacher;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
