/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.entity.impl;

import fon.sims_backend.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author kotar
 */
@Entity
@Table(name = "registration_request")
public class RegistrationRequest implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistrationRequest;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordSalt;
    private String hashedPassword;
    private String question;
    private String answerSalt;
    private String hashedAnswer;
    private Boolean admin;
    @ManyToOne
    @JoinColumn(name = "id_study_level")
    private StudyLevel studyLevel;

    public RegistrationRequest() {
    }

    public RegistrationRequest(Long idRegistrationRequest, String firstName, String lastName, String email, String passwordSalt, String hashedPassword, String question, String answerSalt, String hashedAnswer, Boolean admin, StudyLevel studyLevel) {
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

    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
    }

}
