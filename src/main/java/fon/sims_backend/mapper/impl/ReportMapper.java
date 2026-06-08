/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.ReportDTO;
import fon.sims_backend.entity.impl.Report;
import fon.sims_backend.mapper.DTOEntityMapper;

/**
 *
 * @author kotar
 */
public class ReportMapper implements DTOEntityMapper<ReportDTO, Report> {

    @Override
    public Report toEntity(ReportDTO t) {
        return new Report(
                t.getIdReport(),
                t.getFileName(),
                t.getFileContent());
    }

    @Override
    public ReportDTO toDTO(Report e) {
        return new ReportDTO(
                e.getIdReport(),
                e.getFileName(),
                e.getFileContent());
    }

}
