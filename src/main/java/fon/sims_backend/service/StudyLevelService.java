/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.ModuleDTO;
import fon.sims_backend.dto.impl.StudyLevelDTO;
import fon.sims_backend.dto.impl.StudyProgramDTO;
import fon.sims_backend.entity.impl.StudyLevel;
import fon.sims_backend.entity.impl.StudyProgram;
import fon.sims_backend.entity.impl.Module;
import fon.sims_backend.mapper.impl.StudyLevelMapper;
import fon.sims_backend.repository.impl.ModuleRepo;
import fon.sims_backend.repository.impl.StudyLevelRepo;
import fon.sims_backend.repository.impl.StudyProgramRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class StudyLevelService {

    private final StudyLevelMapper studyLevelMapper;
    private final StudyLevelRepo studyLevelRepo;
    private final StudyProgramRepo studyProgramRepo;
    private final ModuleRepo moduleRepo;

    @Autowired
    public StudyLevelService(StudyLevelMapper studyLevelMapper, StudyLevelRepo studyLevelRepo,
            StudyProgramRepo studyProgramRepo, ModuleRepo moduleRepo) {
        this.studyLevelMapper = studyLevelMapper;
        this.studyLevelRepo = studyLevelRepo;
        this.studyProgramRepo = studyProgramRepo;
        this.moduleRepo = moduleRepo;
    }

    public List<StudyLevelDTO> findAll() {
        return studyLevelRepo.findAll().stream().map(studyLevelMapper::toDTO).toList();
    }

    public StudyLevelDTO findByID(Long id) throws Exception {
        return studyLevelMapper.toDTO(studyLevelRepo.findByID(id));
    }

    public List<StudyLevelDTO> findByStudyLevel(String name) {
        return studyLevelRepo.findByStudyLevel(name).stream().map(studyLevelMapper::toDTO).toList();
    }

    public StudyLevelDTO create(StudyLevelDTO studyLevelDTO) {
        StudyLevel studyLevel = studyLevelMapper.toEntity(studyLevelDTO);
        studyLevelRepo.save(studyLevel);
        return studyLevelMapper.toDTO(studyLevel);
    }

    public StudyLevelDTO update(StudyLevelDTO studyLevelDTO) throws Exception {
        StudyLevel existing = studyLevelRepo.findByID(studyLevelDTO.getIdStudyLevel());
        existing.setName(studyLevelDTO.getName());
        if (studyLevelDTO.getStudyPrograms() != null) {
            for (StudyProgramDTO spDTO : studyLevelDTO.getStudyPrograms()) {
                if (spDTO.getIdStudyProgram() != null) {
                    StudyProgram existingSP = studyProgramRepo.findByID(spDTO.getIdStudyProgram());
                    existingSP.setName(spDTO.getName());
                    if (spDTO.getModules() != null) {
                        for (ModuleDTO mDTO : spDTO.getModules()) {
                            if (mDTO.getIdModule() != null) {
                                Module existingModule = moduleRepo.findByID(mDTO.getIdModule());
                                existingModule.setName(mDTO.getName());
                                moduleRepo.save(existingModule);
                            } else {
                                Module newModule = new Module(null, mDTO.getName(), existingSP);
                                moduleRepo.save(newModule);
                            }
                        }
                    }
                    studyProgramRepo.save(existingSP);
                } else {
                    StudyProgram newSP = new StudyProgram(null, spDTO.getName(), existing, null);
                    studyProgramRepo.save(newSP);
                    if (spDTO.getModules() != null) {
                        for (ModuleDTO mDTO : spDTO.getModules()) {
                            Module newModule = new Module(null, mDTO.getName(), newSP);
                            moduleRepo.save(newModule);
                        }
                    }
                }
            }
        }
        studyLevelRepo.save(existing);
        return studyLevelMapper.toDTO(studyLevelRepo.findByID(existing.getIdStudyLevel()));
    }
}
