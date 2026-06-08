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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author kotar
 */
@Entity
@Table(name = "study_program")
public class StudyProgram implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudyProgram;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_study_level")
    private StudyLevel studyLevel;
    @OneToMany(mappedBy = "studyProgram")
    private List<Module> modules;

    public StudyProgram() {
    }

    public StudyProgram(Long idStudyProgram, String name, StudyLevel studyLevel, List<Module> modules) {
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

    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

}
