/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.ModuleDTO;
import fon.sims_backend.entity.impl.Module;
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
        return new Module(
                t.getIdModule(),
                t.getName(),
                new StudyProgramMapper().toEntity(t.getStudyProgram()));
    }

    @Override
    public ModuleDTO toDTO(Module e) {
        return new ModuleDTO(
                e.getIdModule(),
                e.getName(),
                new StudyProgramMapper().toDTO(e.getStudyProgram()));
    }

}
