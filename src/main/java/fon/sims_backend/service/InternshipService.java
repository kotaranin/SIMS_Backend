/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.InternshipDTO;
import fon.sims_backend.entity.impl.Internship;
import fon.sims_backend.entity.impl.Report;
import fon.sims_backend.enums.Grade;
import fon.sims_backend.mapper.impl.InternshipMapper;
import fon.sims_backend.repository.impl.InternshipRepo;
import fon.sims_backend.repository.impl.ReportRepo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class InternshipService {

    private final InternshipMapper internshipMapper;
    private final InternshipRepo internshipRepo;
    private final ReportRepo reportRepo;

    @Autowired
    public InternshipService(InternshipMapper internshipMapper, InternshipRepo internshipRepo, ReportRepo reportRepo) {
        this.internshipMapper = internshipMapper;
        this.internshipRepo = internshipRepo;
        this.reportRepo = reportRepo;
    }

    public List<InternshipDTO> findAll() {
        return internshipRepo.findAll().stream().map(internshipMapper::toDTO).toList();
    }

    public InternshipDTO findByID(Long id) throws Exception {
        return internshipMapper.toDTO(internshipRepo.findByID(id));
    }

    public List<InternshipDTO> findByInternship(LocalDate startDate, LocalDate endDate, LocalDate defenseDate, Grade grade, Long idTeacher, Long idExamPeriod, Long idStudentOfficer, Long idCompany, Long idStudent) {
        return internshipRepo.findByInternship(startDate, endDate, defenseDate, grade, idTeacher, idExamPeriod, idStudentOfficer, idCompany, idStudent).stream().map(internshipMapper::toDTO).toList();
    }

    public InternshipDTO create(InternshipDTO internshipDTO) throws Exception {
        Report report = new Report(null, internshipDTO.getReport().getFileName(), internshipDTO.getReport().getFileContent());
        reportRepo.save(report);
        internshipDTO.getReport().setIdReport(report.getIdReport());
        Internship internship = internshipMapper.toEntity(internshipDTO);
        internshipRepo.save(internship);
        return internshipMapper.toDTO(internshipRepo.findByID(internship.getIdInternship()));
    }

    public InternshipDTO update(InternshipDTO internshipDTO) throws Exception {
        Report report = new Report(
                internshipDTO.getReport().getIdReport(),
                internshipDTO.getReport().getFileName(),
                internshipDTO.getReport().getFileContent());
        reportRepo.save(report);

        Internship internship = internshipMapper.toEntity(internshipDTO);
        internshipRepo.save(internship);
        return internshipMapper.toDTO(internshipRepo.findByID(internship.getIdInternship()));
    }

    public void delete(Long id) {
        internshipRepo.deleteByID(id);
    }
    
    public Long countAll() {
        return internshipRepo.countAll();
    }
}
