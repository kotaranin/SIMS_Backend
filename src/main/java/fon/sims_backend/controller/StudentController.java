/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.StudentDTO;
import fon.sims_backend.entity.impl.Student;
import fon.sims_backend.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve studente.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Student.class), mediaType = "application/json")})
    public ResponseEntity<List<StudentDTO>> getAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Pretražuje studente po broju indeksa, imenu, prezimenu, datumu rođenja, godini studija, gradu, studijskom programu i/ili modulu.")
    public ResponseEntity<List<StudentDTO>> search(
            @RequestParam(required = false) String indexNumber,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) LocalDate dateOfBirth,
            @RequestParam(required = false) Integer yearOfStudy,
            @RequestParam(required = false) Long idCity,
            @RequestParam(required = false) Long idStudyProgram,
            @RequestParam(required = false) Long idModule
    ) {
        return new ResponseEntity<>(
                studentService.findByStudent(indexNumber, firstName, lastName, dateOfBirth, yearOfStudy, idCity, idStudyProgram, idModule),
                 HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getByID(
            @NotNull(message = "ID ne sme biti null ili prazan!")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(studentService.findByID(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StudentController je naišao na grešku! " + ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Unosi novog studenta.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = Student.class), mediaType = "application/json")})
    public ResponseEntity<StudentDTO> add(@Valid @RequestBody @NotNull StudentDTO studentDTO) {
        try {
            StudentDTO saved = studentService.create(studentDTO);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StudentController je naišao na grešku! " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira studenta.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Student.class), mediaType = "application/json")})
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        try {
            studentDTO.setIdStudent(id);
            StudentDTO updated = studentService.update(studentDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StudentController je naišao na grešku! " + ex.getMessage());
        }
    }

}
