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
import fon.sims_backend.entity.impl.Module;
import fon.sims_backend.mapper.DTOEntityMapper;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class StudyLevelMapper implements DTOEntityMapper<StudyLevelDTO, StudyLevel> {

    @Override
    public StudyLevel toEntity(StudyLevelDTO t) {
        StudyLevel studyLevel = new StudyLevel(t.getIdStudyLevel(), t.getName(), null);
        if (t.getStudyPrograms() != null) {
            List<StudyProgram> programs = t.getStudyPrograms().stream().map(spDTO -> {
                StudyProgram sp = new StudyProgram(spDTO.getIdStudyProgram(), spDTO.getName(), studyLevel, null);
                if (spDTO.getModules() != null) {
                    List<Module> modules = spDTO.getModules().stream().map(mDTO
                            -> new Module(mDTO.getIdModule(), mDTO.getName(), sp)
                    ).toList();
                    sp.setModules(modules);
                }
                return sp;
            }).toList();
            studyLevel.setStudyPrograms(programs);
        }
        return studyLevel;
    }

    @Override
    public StudyLevelDTO toDTO(StudyLevel e) {
        List<StudyProgramDTO> programDTOs = (e.getStudyPrograms() != null)
                ? e.getStudyPrograms().stream().map(sp -> {
                    List<ModuleDTO> moduleDTOs = (sp.getModules() != null)
                            ? sp.getModules().stream().map(m
                                    -> new ModuleDTO(m.getIdModule(), m.getName(), null)
                            ).toList()
                            : List.of();
                    return new StudyProgramDTO(sp.getIdStudyProgram(), sp.getName(), null, moduleDTOs);
                }).toList()
                : List.of();
        return new StudyLevelDTO(e.getIdStudyLevel(), e.getName(), programDTOs);
    }

}
