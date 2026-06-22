/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.AuthResponseDTO;
import fon.sims_backend.dto.impl.LoginRequestDTO;
import fon.sims_backend.dto.impl.StudentOfficerDTO;
import fon.sims_backend.entity.impl.StudentOfficer;
import fon.sims_backend.mapper.impl.StudentOfficerMapper;
import fon.sims_backend.repository.impl.StudentOfficerRepo;
import fon.sims_backend.security.JwtService;
import fon.sims_backend.util.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class AuthService {

    @Autowired
    private StudentOfficerRepo studentOfficerRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private StudentOfficerMapper studentOfficerMapper;

    public AuthResponseDTO login(LoginRequestDTO request) throws Exception {
        StudentOfficer officer = studentOfficerRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Pogrešan e-mail ili lozinka!"));
        boolean isPasswordCorrect = verifyPassword(request.getPassword(), officer.getHashedPassword(), officer.getPasswordSalt());
        if (!isPasswordCorrect) {
            throw new BadCredentialsException("Pogrešan e-mail ili lozinka!");
        }
        String token = jwtService.generateToken(officer);
        StudentOfficerDTO userDto = studentOfficerMapper.toDTO(officer);
        return new AuthResponseDTO(token, userDto);
    }

    private boolean verifyPassword(String rawPassword, String databaseHash, String salt) {
        if (rawPassword == null || databaseHash == null || salt == null) {
            return false;
        }
        String computedHash = HashUtils.hash(rawPassword, salt);
        return computedHash.equals(databaseHash);
    }
}
