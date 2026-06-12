/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.StudentDTO;
import fon.sims_backend.entity.impl.Student;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class StudentMapper implements DTOEntityMapper<StudentDTO, Student> {

    private final CityMapper cityMapper;
    private final StudyProgramMapper studyProgramMapper;
    private final ModuleMapper moduleMapper;
    
    @Autowired
    public StudentMapper(CityMapper cityMapper, StudyProgramMapper studyProgramMapper, ModuleMapper moduleMapper) {
        this.cityMapper = cityMapper;
        this.studyProgramMapper = studyProgramMapper;
        this.moduleMapper = moduleMapper;
    }

    @Override
    public Student toEntity(StudentDTO t) {
        return new Student(
                t.getIdStudent(),
                t.getIndexNumber(),
                t.getFirstName(),
                t.getLastName(),
                t.getDateOfBirth(),
                t.getYearOfStudy(),
                cityMapper.toEntity(t.getCity()),
                studyProgramMapper.toEntity(t.getStudyProgram()),
                t.getModule() != null ? moduleMapper.toEntity(t.getModule()) : null);
    }

    @Override
    public StudentDTO toDTO(Student e) {
        return new StudentDTO(
                e.getIdStudent(),
                e.getIndexNumber(),
                e.getFirstName(),
                e.getLastName(),
                e.getDateOfBirth(),
                e.getYearOfStudy(),
                cityMapper.toDTO(e.getCity()),
                studyProgramMapper.toDTO(e.getStudyProgram()),
                e.getModule() != null ? moduleMapper.toDTO(e.getModule()) : null);
    }

}
