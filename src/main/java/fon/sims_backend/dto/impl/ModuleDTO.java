/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author kotar
 */
public class ModuleDTO implements DTO {

    private Long idModule;
    @NotNull(message = "Naziv je obavezno polje!")
    @NotEmpty(message = "Naziv je obavezno polje!")
    private String name;
    @Valid
    private StudyProgramDTO studyProgram;

    public ModuleDTO() {
    }

    public ModuleDTO(Long idModule, String name, StudyProgramDTO studyProgram) {
        this.idModule = idModule;
        this.name = name;
        this.studyProgram = studyProgram;
    }

    public Long getIdModule() {
        return idModule;
    }

    public void setIdModule(Long idModule) {
        this.idModule = idModule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudyProgramDTO getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgramDTO studyProgram) {
        this.studyProgram = studyProgram;
    }

}
