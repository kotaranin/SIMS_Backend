/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.StudyProgramDTO;
import fon.sims_backend.mapper.impl.StudyProgramMapper;
import fon.sims_backend.repository.impl.StudyProgramRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class StudyProgramService {

    private final StudyProgramMapper studyProgramMapper;
    private final StudyProgramRepo studyProgramRepo;

    @Autowired
    public StudyProgramService(StudyProgramMapper studyProgramMapper, StudyProgramRepo studyProgramRepo) {
        this.studyProgramMapper = studyProgramMapper;
        this.studyProgramRepo = studyProgramRepo;
    }
    
    public List<StudyProgramDTO> findAll() {
        return studyProgramRepo.findAll().stream().map(studyProgramMapper::toDTO).toList();
    }

    public List<StudyProgramDTO> findByStudyLevel(Long idStudyLevel) {
        return studyProgramRepo.findByStudyLevel(idStudyLevel).stream().map(studyProgramMapper::toDTO).toList();
    }
}
