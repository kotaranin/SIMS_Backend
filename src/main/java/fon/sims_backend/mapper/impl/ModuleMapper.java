/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.ModuleDTO;
import fon.sims_backend.dto.impl.StudyProgramDTO;
import fon.sims_backend.entity.impl.Module;
import fon.sims_backend.entity.impl.StudyProgram;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class ModuleMapper implements DTOEntityMapper<ModuleDTO, fon.sims_backend.entity.impl.Module> {

    @Override
    public Module toEntity(ModuleDTO t) {
        StudyProgram sp = null;
        if (t.getStudyProgram() != null) {
            sp = new StudyProgram();
            sp.setIdStudyProgram(t.getStudyProgram().getIdStudyProgram());
            sp.setName(t.getStudyProgram().getName());
        }
        return new Module(t.getIdModule(), t.getName(), sp);
    }

    @Override
    public ModuleDTO toDTO(Module e) {
        StudyProgramDTO spDTO = new StudyProgramDTO(
                e.getStudyProgram().getIdStudyProgram(),
                e.getStudyProgram().getName(),
                null, null);
        return new ModuleDTO(e.getIdModule(), e.getName(), spDTO);
    }

}
