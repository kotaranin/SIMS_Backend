/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.ModuleDTO;
import fon.sims_backend.mapper.impl.ModuleMapper;
import fon.sims_backend.repository.impl.ModuleRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class ModuleService {

    private final ModuleMapper moduleMapper;
    private final ModuleRepo moduleRepo;

    @Autowired
    public ModuleService(ModuleMapper moduleMapper, ModuleRepo moduleRepo) {
        this.moduleMapper = moduleMapper;
        this.moduleRepo = moduleRepo;
    }

    public List<ModuleDTO> findByStudyProgram(Long idStudyProgram) {
        return moduleRepo.findByStudyProgram(idStudyProgram).stream().map(moduleMapper::toDTO).toList();
    }

}
