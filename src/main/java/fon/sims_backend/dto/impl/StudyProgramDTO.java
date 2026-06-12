/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author kotar
 */
public class StudyProgramDTO implements DTO {

    private Long idStudyProgram;
    @NotNull(message = "Naziv je obavezno polje!")
    @NotEmpty(message = "Naziv je obavezno polje!")
    private String name;
    @Valid
    private StudyLevelDTO studyLevel;
    @Valid
    private List<ModuleDTO> modules;

    public StudyProgramDTO() {
    }

    public StudyProgramDTO(Long idStudyProgram, String name, StudyLevelDTO studyLevel, List<ModuleDTO> modules) {
        this.idStudyProgram = idStudyProgram;
        this.name = name;
        this.studyLevel = studyLevel;
        this.modules = modules;
    }

    public Long getIdStudyProgram() {
        return idStudyProgram;
    }

    public void setIdStudyProgram(Long idStudyProgram) {
        this.idStudyProgram = idStudyProgram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudyLevelDTO getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevelDTO studyLevel) {
        this.studyLevel = studyLevel;
    }

    public List<ModuleDTO> getModules() {
        return modules;
    }

    public void setModules(List<ModuleDTO> modules) {
        this.modules = modules;
    }

}
