/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.TeacherDTO;
import fon.sims_backend.entity.impl.Teacher;
import fon.sims_backend.mapper.DTOEntityMapper;

/**
 *
 * @author kotar
 */
public class TeacherMapper implements DTOEntityMapper<TeacherDTO, Teacher> {

    @Override
    public Teacher toEntity(TeacherDTO t) {
        return new Teacher(
                t.getIdTeacher(),
                t.getFirstName(),
                t.getLastName()
        );
    }

    @Override
    public TeacherDTO toDTO(Teacher e) {
        return new TeacherDTO(
                e.getIdTeacher(),
                e.getFirstName(),
                e.getLastName()
        );
    }

}
