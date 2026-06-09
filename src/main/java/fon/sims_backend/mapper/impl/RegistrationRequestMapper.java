/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.RegistrationRequestDTO;
import fon.sims_backend.entity.impl.RegistrationRequest;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class RegistrationRequestMapper implements DTOEntityMapper<RegistrationRequestDTO, RegistrationRequest> {

    @Override
    public RegistrationRequest toEntity(RegistrationRequestDTO t) {
        return new RegistrationRequest(
                t.getIdRegistrationRequest(),
                t.getFirstName(),
                t.getLastName(),
                t.getEmail(),
                t.getPasswordSalt(),
                t.getHashedPassword(),
                t.getQuestion(),
                t.getAnswerSalt(),
                t.getHashedAnswer(),
                t.getAdmin(),
                new StudyLevelMapper().toEntity(t.getStudyLevel()));
    }

    @Override
    public RegistrationRequestDTO toDTO(RegistrationRequest e) {
        return new RegistrationRequestDTO(
                e.getIdRegistrationRequest(),
                e.getFirstName(),
                e.getLastName(),
                e.getEmail(),
                e.getPasswordSalt(),
                e.getHashedPassword(),
                e.getQuestion(),
                e.getAnswerSalt(),
                e.getHashedAnswer(),
                e.getAdmin(),
                new StudyLevelMapper().toDTO(e.getStudyLevel()));
    }

}
