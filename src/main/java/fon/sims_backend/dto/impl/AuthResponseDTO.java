/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;

/**
 *
 * @author kotar
 */
public class AuthResponseDTO implements DTO {

    private String token;
    private StudentOfficerDTO user;

    public AuthResponseDTO(String token, StudentOfficerDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public StudentOfficerDTO getUser() {
        return user;
    }

    public void setUser(StudentOfficerDTO user) {
        this.user = user;
    }
}
