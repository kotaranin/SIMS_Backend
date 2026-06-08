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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author kotar
 */
@Entity
@Table(name = "study_level")
public class StudyLevel implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudyLevel;
    private String name;
    @OneToMany(mappedBy = "studyLevel")
    private List<StudyProgram> studyPrograms;

    public StudyLevel() {
    }

    public StudyLevel(Long idStudyLevel, String name, List<StudyProgram> studyPrograms) {
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

    public List<StudyProgram> getStudyPrograms() {
        return studyPrograms;
    }

    public void setStudyPrograms(List<StudyProgram> studyPrograms) {
        this.studyPrograms = studyPrograms;
    }

}
