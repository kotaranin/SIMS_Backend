/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.InternshipDTO;
import fon.sims_backend.entity.impl.Internship;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class InternshipMapper implements DTOEntityMapper<InternshipDTO, Internship> {

    private final TeacherMapper teacherMapper;
    private final ExamPeriodMapper examPeriodMapper;
    private final ReportMapper reportMapper;
    private final StudentOfficerMapper studentOfficerMapper;
    private final CompanyMapper companyMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public InternshipMapper(TeacherMapper teacherMapper, ExamPeriodMapper examPeriodMapper,
            ReportMapper reportMapper, StudentOfficerMapper studentOfficerMapper,
            CompanyMapper companyMapper, StudentMapper studentMapper) {
        this.teacherMapper = teacherMapper;
        this.examPeriodMapper = examPeriodMapper;
        this.reportMapper = reportMapper;
        this.studentOfficerMapper = studentOfficerMapper;
        this.companyMapper = companyMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public Internship toEntity(InternshipDTO t) {
        return new Internship(t.getIdInternship(), t.getStartDate(), t.getEndDate(),
                t.getDefenseDate(), t.getGrade(),
                teacherMapper.toEntity(t.getTeacher()),
                examPeriodMapper.toEntity(t.getExamPeriod()),
                reportMapper.toEntity(t.getReport()),
                studentOfficerMapper.toEntity(t.getStudentOfficer()),
                companyMapper.toEntity(t.getCompany()),
                studentMapper.toEntity(t.getStudent()));
    }

    @Override
    public InternshipDTO toDTO(Internship e) {
        return new InternshipDTO(e.getIdInternship(), e.getStartDate(), e.getEndDate(),
                e.getDefenseDate(), e.getGrade(),
                teacherMapper.toDTO(e.getTeacher()),
                examPeriodMapper.toDTO(e.getExamPeriod()),
                reportMapper.toDTO(e.getReport()),
                studentOfficerMapper.toDTO(e.getStudentOfficer()),
                companyMapper.toDTO(e.getCompany()),
                studentMapper.toDTO(e.getStudent()));
    }

}
