/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.TeacherDTO;
import fon.sims_backend.entity.impl.Teacher;
import fon.sims_backend.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
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
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve nastavnike.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Teacher.class), mediaType = "application/json")})
    public ResponseEntity<List<TeacherDTO>> getAll() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getByID(
            @NotNull(message = "ID ne sme biti null ili prazan!")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(teacherService.findByID(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TeacherController je naišao na grešku!");
        }
    }

    @PostMapping
    @Operation(summary = "Unosi novog nastavnika.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = Teacher.class), mediaType = "application/json")})
    public ResponseEntity<TeacherDTO> add(@Valid @RequestBody @NotNull TeacherDTO teacherDTO) {
        TeacherDTO saved = teacherService.create(teacherDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira nastavnika.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Teacher.class), mediaType = "application/json")})
    public ResponseEntity<TeacherDTO> update(@PathVariable Long id, @Valid @RequestBody TeacherDTO teacherDTO) {
        teacherDTO.setIdTeacher(id);
        TeacherDTO updated = teacherService.update(teacherDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Pretražuje nastavnike po imenu i prezimenu.")
    public ResponseEntity<List<TeacherDTO>> search(
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName) {
        return new ResponseEntity<>(
                teacherService.findByTeacher(firstName, lastName), HttpStatus.OK);
    }

}
