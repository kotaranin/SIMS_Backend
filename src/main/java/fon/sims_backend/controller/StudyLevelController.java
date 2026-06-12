/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.StudyLevelDTO;
import fon.sims_backend.entity.impl.StudyLevel;
import fon.sims_backend.service.StudyLevelService;
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
@RequestMapping("/api/study-level")
public class StudyLevelController {

    private final StudyLevelService studyLevelService;

    public StudyLevelController(StudyLevelService studyLevelService) {
        this.studyLevelService = studyLevelService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve nivoe studija.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = StudyLevel.class), mediaType = "application/json")})
    public ResponseEntity<List<StudyLevelDTO>> getAll() {
        return new ResponseEntity<>(studyLevelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Pretražuje nivoe studija po imenu.")
    public ResponseEntity<List<StudyLevelDTO>> search(
            @RequestParam(required = false) String name) {
        return new ResponseEntity<>(
                studyLevelService.findByStudyLevel(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyLevelDTO> getByID(
            @NotNull(message = "ID ne sme biti null ili prazan!")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(studyLevelService.findByID(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StudyLevelController je naišao na grešku!");
        }
    }

    @PostMapping
    @Operation(summary = "Unosi nov nivo studija.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = StudyLevel.class), mediaType = "application/json")})
    public ResponseEntity<StudyLevelDTO> add(@Valid @RequestBody @NotNull StudyLevelDTO studyLevelDTO) {
        StudyLevelDTO saved = studyLevelService.create(studyLevelDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira nivo studija.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = StudyLevel.class), mediaType = "application/json")})
    public ResponseEntity<StudyLevelDTO> update(@PathVariable Long id, @Valid @RequestBody StudyLevelDTO studyLevelDTO) {
        try {
            studyLevelDTO.setIdStudyLevel(id);
            StudyLevelDTO updated = studyLevelService.update(studyLevelDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StudyLevelController je naišao na grešku!");
        }
    }

}
