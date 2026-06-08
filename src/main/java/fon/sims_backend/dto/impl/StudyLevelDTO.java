/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author kotar
 */
public class StudyLevelDTO implements DTO {

    private Long idStudyLevel;
    @NotNull(message = "Naziv je obavezno polje!")
    private String name;
    @Valid
    private List<StudyProgramDTO> studyPrograms;

    public StudyLevelDTO() {
    }

    public StudyLevelDTO(Long idStudyLevel, String name, List<StudyProgramDTO> studyPrograms) {
        this.idStudyLevel = idStudyLevel;
        this.name = name;
        this.studyPrograms = studyPrograms;
    }

    public Long getIdStudyLevel() {
        return idStudyLevel;
    }

    public void setIdStudyLevel(Long idStudyLevel) {
        this.idStudyLevel = idStudyLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudyProgramDTO> getStudyPrograms() {
        return studyPrograms;
    }

    public void setStudyPrograms(List<StudyProgramDTO> studyPrograms) {
        this.studyPrograms = studyPrograms;
    }

}
