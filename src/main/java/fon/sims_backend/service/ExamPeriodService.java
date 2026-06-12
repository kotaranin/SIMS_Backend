/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.ExamPeriodDTO;
import fon.sims_backend.entity.impl.ExamPeriod;
import fon.sims_backend.mapper.impl.ExamPeriodMapper;
import fon.sims_backend.repository.impl.ExamPeriodRepo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class ExamPeriodService {

    private final ExamPeriodMapper examPeriodMapper;
    private final ExamPeriodRepo examPeriodRepo;

    @Autowired
    public ExamPeriodService(ExamPeriodMapper examPeriodMapper, ExamPeriodRepo examPeriodRepo) {
        this.examPeriodMapper = examPeriodMapper;
        this.examPeriodRepo = examPeriodRepo;
    }

    public List<ExamPeriodDTO> findAll() {
        return examPeriodRepo.findAll().stream().map(examPeriodMapper::toDTO).toList();
    }

    public ExamPeriodDTO findByID(Long id) throws Exception {
        return examPeriodMapper.toDTO(examPeriodRepo.findByID(id));
    }

    public List<ExamPeriodDTO> findByExamPeriod(String name, LocalDate startDate, LocalDate endDate) {
        return examPeriodRepo.findByExamPeriod(name, startDate, endDate).stream().map(examPeriodMapper::toDTO).toList();
    }

    public ExamPeriodDTO create(ExamPeriodDTO teacherDTO) {
        ExamPeriod examPeriod = examPeriodMapper.toEntity(teacherDTO);
        examPeriodRepo.save(examPeriod);
        return examPeriodMapper.toDTO(examPeriod);
    }

    public ExamPeriodDTO update(ExamPeriodDTO teacherDTO) {
        ExamPeriod examPeriod = examPeriodMapper.toEntity(teacherDTO);
        examPeriodRepo.save(examPeriod);
        return examPeriodMapper.toDTO(examPeriod);
    }

}
