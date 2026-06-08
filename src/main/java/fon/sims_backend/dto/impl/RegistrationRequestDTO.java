/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author kotar
 */
public class RegistrationRequestDTO implements DTO {

    private Long idRegistrationRequest;
    @NotNull(message = "Ime je obavezno polje!")
    @NotEmpty(message = "Ime je obavezno polje!")
    private String firstName;
    @NotNull(message = "Prezime je obavezno polje!")
    @NotEmpty(message = "Prezime je obavezno polje!")
    private String lastName;
    @NotNull(message = "E-mail je obavezno polje!")
    @NotEmpty(message = "E-mail je obavezno polje!")
    @Email(message = "E-mail nije ispravnog formata!")
    private String email;
    @NotNull(message = "Lozinka je obavezno polje!")
    @NotEmpty(message = "Lozinka je obavezno polje!")
    private String passwordSalt;
    private String hashedPassword;
    @NotNull(message = "Sigurnosno pitanje je obavezno polje!")
    @NotEmpty(message = "Sigurnosno pitanje je obavezno polje!")
    private String question;
    @NotNull(message = "Odgovor na sigurnosno pitanje je obavezno polje!")
    @NotEmpty(message = "Odgovor na sigurnosno pitanje je obavezno polje!")
    private String answerSalt;
    private String hashedAnswer;
    @NotNull(message = "Privilegija je obavezno polje!")
    private Boolean admin;
    @NotNull(message = "Nivo studija je obavezno polje!")
    @Valid
    private StudyLevelDTO studyLevel;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(Long idRegistrationRequest, String firstName, String lastName, String email, String passwordSalt, String hashedPassword, String question, String answerSalt, String hashedAnswer, Boolean admin, StudyLevelDTO studyLevel) {
        this.idRegistrationRequest = idRegistrationRequest;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordSalt = passwordSalt;
        this.hashedPassword = hashedPassword;
        this.question = question;
        this.answerSalt = answerSalt;
        this.hashedAnswer = hashedAnswer;
        this.admin = admin;
        this.studyLevel = studyLevel;
    }

    public Long getIdRegistrationRequest() {
        return idRegistrationRequest;
    }

    public void setIdRegistrationRequest(Long idRegistrationRequest) {
        this.idRegistrationRequest = idRegistrationRequest;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerSalt() {
        return answerSalt;
    }

    public void setAnswerSalt(String answerSalt) {
        this.answerSalt = answerSalt;
    }

    public String getHashedAnswer() {
        return hashedAnswer;
    }

    public void setHashedAnswer(String hashedAnswer) {
        this.hashedAnswer = hashedAnswer;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public StudyLevelDTO getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevelDTO studyLevel) {
        this.studyLevel = studyLevel;
    }

}
