/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.InternshipDTO;
import fon.sims_backend.entity.impl.Internship;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class InternshipMapper implements DTOEntityMapper<InternshipDTO, Internship> {

    @Override
    public Internship toEntity(InternshipDTO t) {
        return new Internship(
                t.getIdInternship(),
                t.getStartDate(),
                t.getEndDate(),
                t.getDefenseDate(),
                t.getGrade(),
                new TeacherMapper().toEntity(t.getTeacher()),
                new ExamPeriodMapper().toEntity(t.getExamPeriod()),
                new ReportMapper().toEntity(t.getReport()),
                new StudentOfficerMapper().toEntity(t.getStudentOfficer()),
                new CompanyMapper().toEntity(t.getCompany()),
                new StudentMapper().toEntity(t.getStudent()));
    }

    @Override
    public InternshipDTO toDTO(Internship e) {
        return new InternshipDTO(
                e.getIdInternship(),
                e.getStartDate(),
                e.getEndDate(),
                e.getDefenseDate(),
                e.getGrade(),
                new TeacherMapper().toDTO(e.getTeacher()),
                new ExamPeriodMapper().toDTO(e.getExamPeriod()),
                new ReportMapper().toDTO(e.getReport()),
                new StudentOfficerMapper().toDTO(e.getStudentOfficer()),
                new CompanyMapper().toDTO(e.getCompany()),
                new StudentMapper().toDTO(e.getStudent()));
    }

}
