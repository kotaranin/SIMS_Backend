/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.ReportDTO;
import fon.sims_backend.entity.impl.Report;
import fon.sims_backend.mapper.impl.ReportMapper;
import fon.sims_backend.repository.impl.ReportRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class ReportService {

    private final ReportMapper reportMapper;
    private final ReportRepo reportRepo;

    @Autowired
    public ReportService(ReportMapper reportMapper, ReportRepo reportRepo) {
        this.reportMapper = reportMapper;
        this.reportRepo = reportRepo;
    }

    public List<ReportDTO> findAll() {
        return reportRepo.findAll().stream().map(reportMapper::toDTO).toList();
    }

    public ReportDTO findByID(Long id) throws Exception {
        return reportMapper.toDTO(reportRepo.findByID(id));
    }

    public ReportDTO create(ReportDTO reportDTO) {
        Report report = reportMapper.toEntity(reportDTO);
        reportRepo.save(report);
        return reportMapper.toDTO(report);
    }

    public ReportDTO update(ReportDTO reportDTO) {
        Report report = reportMapper.toEntity(reportDTO);
        reportRepo.save(report);
        return reportMapper.toDTO(report);
    }

}
