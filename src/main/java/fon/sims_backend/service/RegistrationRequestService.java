/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.RegistrationRequestDTO;
import fon.sims_backend.entity.impl.RegistrationRequest;
import fon.sims_backend.mapper.impl.RegistrationRequestMapper;
import fon.sims_backend.repository.impl.RegistrationRequestRepo;
import fon.sims_backend.util.HashUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class RegistrationRequestService {

    private final RegistrationRequestMapper registrationRequestMapper;
    private final RegistrationRequestRepo registrationRequestRepo;

    @Autowired
    public RegistrationRequestService(RegistrationRequestMapper registrationRequestMapper, RegistrationRequestRepo registrationRequestRepo) {
        this.registrationRequestMapper = registrationRequestMapper;
        this.registrationRequestRepo = registrationRequestRepo;
    }

    public List<RegistrationRequestDTO> findAll() {
        return registrationRequestRepo.findAll().stream().map(registrationRequestMapper::toDTO).toList();
    }

    public RegistrationRequestDTO findByID(Long id) throws Exception {
        return registrationRequestMapper.toDTO(registrationRequestRepo.findByID(id));
    }

    public List<RegistrationRequestDTO> findByRegistrationRequest(String firstName, String lastName) {
        return registrationRequestRepo.findByRegistrationRequest(firstName, lastName).stream().map(registrationRequestMapper::toDTO).toList();
    }

    public RegistrationRequestDTO create(RegistrationRequestDTO registrationRequestDTO) throws Exception {
        RegistrationRequest request = registrationRequestMapper.toEntity(registrationRequestDTO);
    String rawPassword = request.getPasswordSalt(); 
    String rawAnswer = request.getAnswerSalt();
    String passwordSalt = HashUtils.generateSalt();
    String answerSalt = HashUtils.generateSalt();
    String hashedPassword = HashUtils.hash(rawPassword, passwordSalt);
    String hashedAnswer = HashUtils.hash(rawAnswer, answerSalt);
    request.setPasswordSalt(passwordSalt);
    request.setHashedPassword(hashedPassword);
    request.setAnswerSalt(answerSalt);
    request.setHashedAnswer(hashedAnswer);
    registrationRequestRepo.save(request);
    return registrationRequestMapper.toDTO(registrationRequestRepo.findByID(request.getIdRegistrationRequest()));
    }

    public RegistrationRequestDTO update(RegistrationRequestDTO registrationRequestDTO) throws Exception {
        RegistrationRequest registrationRequest = registrationRequestMapper.toEntity(registrationRequestDTO);
        registrationRequestRepo.save(registrationRequest);
        return registrationRequestMapper.toDTO(registrationRequestRepo.findByID(registrationRequest.getIdRegistrationRequest()));
    }

    public void deleteById(Long id) {
        registrationRequestRepo.deleteByID(id);
    }
    
    public Long countAll() {
        return registrationRequestRepo.countAll();
    }

}
