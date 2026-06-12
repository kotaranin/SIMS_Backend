/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.ModuleDTO;
import fon.sims_backend.dto.impl.StudyLevelDTO;
import fon.sims_backend.dto.impl.StudyProgramDTO;
import fon.sims_backend.entity.impl.StudyLevel;
import fon.sims_backend.entity.impl.StudyProgram;
import fon.sims_backend.mapper.DTOEntityMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fon.sims_backend.entity.impl.Module;

/**
 *
 * @author kotar
 */
@Component
public class StudyProgramMapper implements DTOEntityMapper<StudyProgramDTO, StudyProgram> {

    private final ModuleMapper moduleMapper;

    @Autowired
    public StudyProgramMapper(ModuleMapper moduleMapper) {
        this.moduleMapper = moduleMapper;
    }

    @Override
    public StudyProgram toEntity(StudyProgramDTO t) {
        StudyLevel sl = null;
        if (t.getStudyLevel() != null) {
            sl = new StudyLevel();
            sl.setIdStudyLevel(t.getStudyLevel().getIdStudyLevel());
            sl.setName(t.getStudyLevel().getName());
        }
        List<Module> modules = (t.getModules() != null)
                ? t.getModules().stream().map(moduleMapper::toEntity).toList()
                : List.of();
        return new StudyProgram(t.getIdStudyProgram(), t.getName(), sl, modules);
    }

    @Override
    public StudyProgramDTO toDTO(StudyProgram e) {
        StudyLevelDTO slDTO = new StudyLevelDTO(
                e.getStudyLevel().getIdStudyLevel(),
                e.getStudyLevel().getName(),
                null
        );
        List<ModuleDTO> modules = (e.getModules() != null)
                ? e.getModules().stream().map(moduleMapper::toDTO).toList()
                : List.of();
        return new StudyProgramDTO(e.getIdStudyProgram(), e.getName(), slDTO, modules);
    }

}
