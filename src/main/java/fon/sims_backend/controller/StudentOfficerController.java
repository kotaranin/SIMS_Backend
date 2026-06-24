/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.StudentOfficerDTO;
import fon.sims_backend.entity.impl.StudentOfficer;
import fon.sims_backend.service.StudentOfficerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author kotar
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/student-officer")
public class StudentOfficerController {

    private final StudentOfficerService studentOfficerService;

    public StudentOfficerController(StudentOfficerService studentOfficerService) {
        this.studentOfficerService = studentOfficerService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve službenike studentske službe.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = StudentOfficer.class), mediaType = "application/json")})
    public ResponseEntity<List<StudentOfficerDTO>> getAll() {
        return new ResponseEntity<>(studentOfficerService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Unosi novog službenika studentske službe.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = StudentOfficer.class), mediaType = "application/json")})
    public ResponseEntity<StudentOfficerDTO> add(@Valid @RequestBody @NotNull StudentOfficerDTO studentOfficerDTO) {
        try {
            StudentOfficerDTO saved = studentOfficerService.create(studentOfficerDTO);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StudentOfficerController je naišao na grešku!");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira službenika studentske službe.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = StudentOfficer.class), mediaType = "application/json")})
    public ResponseEntity<StudentOfficerDTO> update(@PathVariable Long id, @Valid @RequestBody StudentOfficerDTO studentOfficerDTO) {
        try {
            studentOfficerDTO.setIdStudentOfficer(id);
            StudentOfficerDTO updated = studentOfficerService.update(studentOfficerDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StudentOfficerController je naišao na grešku!");
        }
    }

    @PostMapping("/reset-password/verify-question")
    public ResponseEntity<Boolean> verifySecurityAnswer(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String answer = payload.get("answer");
        boolean isValid = studentOfficerService.verifySecurityAnswer(email, answer);
        return ResponseEntity.ok(isValid);
    }

    @PutMapping("/reset-password/update")
    public ResponseEntity<Void> updatePassword(@RequestBody Map<String, String> payload) throws Exception {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");
        studentOfficerService.updatePassword(email, newPassword);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/get-question")
public ResponseEntity<String> getSecurityQuestion(@RequestParam String email) {
    return ResponseEntity.ok(studentOfficerService.getSecurityQuestion(email));
}

}
