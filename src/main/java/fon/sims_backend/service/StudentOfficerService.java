/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.StudentOfficerDTO;
import fon.sims_backend.entity.impl.StudentOfficer;
import fon.sims_backend.mapper.impl.StudentOfficerMapper;
import fon.sims_backend.repository.impl.StudentOfficerRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class StudentOfficerService {

    private final StudentOfficerMapper studentOfficerMapper;
    private final StudentOfficerRepo studentOfficerRepo;

    @Autowired
    public StudentOfficerService(StudentOfficerMapper studentOfficerMapper, StudentOfficerRepo studentOfficerRepo) {
        this.studentOfficerMapper = studentOfficerMapper;
        this.studentOfficerRepo = studentOfficerRepo;
    }

    public List<StudentOfficerDTO> findAll() {
        return studentOfficerRepo.findAll().stream().map(studentOfficerMapper::toDTO).toList();
    }

    public StudentOfficerDTO findByID(Long id) throws Exception {
        return studentOfficerMapper.toDTO(studentOfficerRepo.findByID(id));
    }

    public StudentOfficerDTO create(StudentOfficerDTO studentOfficerDTO) throws Exception {
        StudentOfficer studentOfficer = studentOfficerMapper.toEntity(studentOfficerDTO);
        studentOfficerRepo.save(studentOfficer);
        return studentOfficerMapper.toDTO(studentOfficerRepo.findByID(studentOfficer.getIdStudentOfficer()));
    }

    public StudentOfficerDTO update(StudentOfficerDTO studentOfficerDTO) throws Exception {
        StudentOfficer studentOfficer = studentOfficerMapper.toEntity(studentOfficerDTO);
        studentOfficerRepo.save(studentOfficer);
        return studentOfficerMapper.toDTO(studentOfficerRepo.findByID(studentOfficer.getIdStudentOfficer()));
    }
}
