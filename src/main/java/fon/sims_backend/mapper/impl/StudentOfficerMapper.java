/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.StudentOfficerDTO;
import fon.sims_backend.entity.impl.StudentOfficer;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class StudentOfficerMapper implements DTOEntityMapper<StudentOfficerDTO, StudentOfficer> {

    private final StudyLevelMapper studyLevelMapper;

    @Autowired
    public StudentOfficerMapper(StudyLevelMapper studyLevelMapper) {
        this.studyLevelMapper = studyLevelMapper;
    }

    @Override
    public StudentOfficer toEntity(StudentOfficerDTO t) {
        return new StudentOfficer(
                t.getIdStudentOfficer(),
                t.getFirstName(),
                t.getLastName(),
                t.getEmail(),
                t.getPasswordSalt(),
                t.getHashedPassword(),
                t.getQuestion(),
                t.getAnswerSalt(),
                t.getHashedAnswer(),
                t.getAdmin(),
                t.getStudyLevel() != null ? studyLevelMapper.toEntity(t.getStudyLevel()) : null);
    }

    @Override
    public StudentOfficerDTO toDTO(StudentOfficer e) {
        return new StudentOfficerDTO(
                e.getIdStudentOfficer(),
                e.getFirstName(),
                e.getLastName(),
                e.getEmail(),
                e.getPasswordSalt(),
                e.getHashedPassword(),
                e.getQuestion(),
                e.getAnswerSalt(),
                e.getHashedAnswer(),
                e.getAdmin(),
                studyLevelMapper.toDTO(e.getStudyLevel()));
    }

}
