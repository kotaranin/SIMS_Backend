/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.TeacherDTO;
import fon.sims_backend.entity.impl.Teacher;
import fon.sims_backend.mapper.impl.TeacherMapper;
import fon.sims_backend.repository.impl.TeacherRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class TeacherService {

    private final TeacherMapper teacherMapper;
    private final TeacherRepo teacherRepo;

    @Autowired
    public TeacherService(TeacherMapper teacherMapper, TeacherRepo teacherRepo) {
        this.teacherMapper = teacherMapper;
        this.teacherRepo = teacherRepo;
    }

    public List<TeacherDTO> findAll() {
        return teacherRepo.findAll().stream().map(teacherMapper::toDTO).toList();
    }

    public TeacherDTO findByID(Long id) throws Exception {
        return teacherMapper.toDTO(teacherRepo.findByID(id));
    }

    public TeacherDTO create(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        teacherRepo.save(teacher);
        return teacherMapper.toDTO(teacher);
    }

    public TeacherDTO update(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        teacherRepo.save(teacher);
        return teacherMapper.toDTO(teacher);
    }

}
