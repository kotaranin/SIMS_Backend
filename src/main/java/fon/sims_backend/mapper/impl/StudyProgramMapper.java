/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.ModuleDTO;
import fon.sims_backend.dto.impl.StudyProgramDTO;
import fon.sims_backend.entity.impl.StudyProgram;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class StudyProgramMapper implements DTOEntityMapper<StudyProgramDTO, StudyProgram> {

    @Override
    public StudyProgram toEntity(StudyProgramDTO t) {
        ModuleMapper moduleMapper = new ModuleMapper();
        return new StudyProgram(
                t.getIdStudyProgram(),
                t.getName(),
                new StudyLevelMapper().toEntity(t.getStudyLevel()),
                t.getModules().stream().map(moduleDTO -> (fon.sims_backend.entity.impl.Module) moduleMapper.toEntity(moduleDTO)).toList());
    }

    @Override
    public StudyProgramDTO toDTO(StudyProgram e) {
        ModuleMapper moduleMapper = new ModuleMapper();
        return new StudyProgramDTO(
                e.getIdStudyProgram(),
                e.getName(),
                new StudyLevelMapper().toDTO(e.getStudyLevel()),
                e.getModules().stream().map(module -> (ModuleDTO) moduleMapper.toDTO(module)).toList());
    }

}
