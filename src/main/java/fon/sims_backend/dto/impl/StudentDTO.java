/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

/**
 *
 * @author kotar
 */
public class StudentDTO implements DTO {

    private Long idStudent;
    @NotNull(message = "Broj indeksa je obavezno polje!")
    @Pattern(
            regexp = "^\\d{4}/\\d{4}$",
            message = "Broj indeksa mora biti u formatu GGGG/BBBB (npr. 2026/0015)")
    private String indexNumber;
    @NotNull(message = "Ime je obavezno polje!")
    @NotEmpty(message = "Ime je obavezno polje!")
    private String firstName;
    @NotNull(message = "Prezime je obavezno polje!")
    @NotEmpty(message = "Prezime je obavezno polje!")
    private String lastName;
    @NotNull(message = "Datum rođenja je obavezno polje!")
    private LocalDate dateOfBirth;
    @NotNull(message = "Godina studija je obavezno polje!")
    @Positive(message = "Godina studija mora biti pozitivan ceo broj!")
    private Integer yearOfStudy;
    @NotNull(message = "Grad je obavezno polje!")
    @Valid
    private CityDTO City;
    @NotNull(message = "Studijski program je obavezno polje!")
    @Valid
    private StudyProgramDTO studyProgram;
    @Valid
    private ModuleDTO module;

    public StudentDTO() {
    }

    public StudentDTO(Long idStudent, String indexNumber, String firstName, String lastName, LocalDate dateOfBirth, Integer yearOfStudy, CityDTO City, StudyProgramDTO studyProgram, ModuleDTO module) {
        this.idStudent = idStudent;
        this.indexNumber = indexNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.yearOfStudy = yearOfStudy;
        this.City = City;
        this.studyProgram = studyProgram;
        this.module = module;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public CityDTO getCity() {
        return City;
    }

    public void setCity(CityDTO City) {
        this.City = City;
    }

    public StudyProgramDTO getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgramDTO studyProgram) {
        this.studyProgram = studyProgram;
    }

    public ModuleDTO getModule() {
        return module;
    }

    public void setModule(ModuleDTO module) {
        this.module = module;
    }

}
