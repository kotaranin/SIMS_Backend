/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.StudentDTO;
import fon.sims_backend.entity.impl.Student;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class StudentMapper implements DTOEntityMapper<StudentDTO, Student> {

    @Override
    public Student toEntity(StudentDTO t) {
        return new Student(
                t.getIdStudent(),
                t.getIndexNumber(),
                t.getFirstName(),
                t.getLastName(),
                t.getDateOfBirth(),
                t.getYearOfStudy(),
                new CityMapper().toEntity(t.getCity()),
                new StudyProgramMapper().toEntity(t.getStudyProgram()),
                new ModuleMapper().toEntity(t.getModule()));
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
                new CityMapper().toDTO(e.getCity()),
                new StudyProgramMapper().toDTO(e.getStudyProgram()),
                new ModuleMapper().toDTO(e.getModule()));
    }

}
