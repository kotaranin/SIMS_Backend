/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.StudentDTO;
import fon.sims_backend.entity.impl.Student;
import fon.sims_backend.mapper.impl.StudentMapper;
import fon.sims_backend.repository.impl.StudentRepo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentMapper studentMapper, StudentRepo studentRepo) {
        this.studentMapper = studentMapper;
        this.studentRepo = studentRepo;
    }

    public List<StudentDTO> findAll() {
        return studentRepo.findAll().stream().map(studentMapper::toDTO).toList();
    }

    public StudentDTO findByID(Long id) throws Exception {
        return studentMapper.toDTO(studentRepo.findByID(id));
    }

    public List<StudentDTO> findByStudent(String indexNumber, String firstName, String lastName, LocalDate dateOfBirth, Integer yearOfStudy, Long idCity, Long idStudyProgram, Long idModule) {
        return studentRepo.findByStudent(indexNumber, firstName, lastName, dateOfBirth, yearOfStudy, idCity, idStudyProgram, idModule).stream().map(studentMapper::toDTO).toList();
    }

    public StudentDTO create(StudentDTO studentDTO) throws Exception {
        Student student = studentMapper.toEntity(studentDTO);
        studentRepo.save(student);
        return studentMapper.toDTO(studentRepo.findByID(student.getIdStudent()));
    }

    public StudentDTO update(StudentDTO studentDTO) throws Exception {
        Student student = studentMapper.toEntity(studentDTO);
        studentRepo.save(student);
        return studentMapper.toDTO(studentRepo.findByID(student.getIdStudent()));
    }
}
