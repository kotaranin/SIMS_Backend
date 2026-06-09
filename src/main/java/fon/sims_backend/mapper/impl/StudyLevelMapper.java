/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.StudyLevelDTO;
import fon.sims_backend.dto.impl.StudyProgramDTO;
import fon.sims_backend.entity.impl.StudyLevel;
import fon.sims_backend.entity.impl.StudyProgram;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class StudyLevelMapper implements DTOEntityMapper<StudyLevelDTO, StudyLevel> {

    @Override
    public StudyLevel toEntity(StudyLevelDTO t) {
        StudyProgramMapper studyProgramMapper = new StudyProgramMapper();
        return new StudyLevel(
                t.getIdStudyLevel(),
                t.getName(),
                t.getStudyPrograms().stream().map(studyProgramDTO -> (StudyProgram) studyProgramMapper.toEntity(studyProgramDTO)).toList());
    }

    @Override
    public StudyLevelDTO toDTO(StudyLevel e) {
        StudyProgramMapper studyProgramMapper = new StudyProgramMapper();
        return new StudyLevelDTO(
                e.getIdStudyLevel(),
                e.getName(),
                e.getStudyPrograms().stream().map(studyProgram -> (StudyProgramDTO) studyProgramMapper.toDTO(studyProgram)).toList());
    }

}
