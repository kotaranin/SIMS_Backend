/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.ExamPeriodDTO;
import fon.sims_backend.entity.impl.ExamPeriod;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class ExamPeriodMapper implements DTOEntityMapper<ExamPeriodDTO, ExamPeriod> {

    @Override
    public ExamPeriod toEntity(ExamPeriodDTO t) {
        return new ExamPeriod(
                t.getIdExamPeriod(),
                t.getName(),
                t.getStartDate(),
                t.getEndDate());
    }

    @Override
    public ExamPeriodDTO toDTO(ExamPeriod e) {
        return new ExamPeriodDTO(
                e.getIdExamPeriod(),
                e.getName(),
                e.getStartDate(),
                e.getEndDate());
    }

}
