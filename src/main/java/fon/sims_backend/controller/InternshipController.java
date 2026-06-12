/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.InternshipDTO;
import fon.sims_backend.entity.impl.Internship;
import fon.sims_backend.enums.Grade;
import fon.sims_backend.service.InternshipService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/internship")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve stručne prakse.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Internship.class), mediaType = "application/json")})
    public ResponseEntity<List<InternshipDTO>> getAll() {
        return new ResponseEntity<>(internshipService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Pretražuje stručne prakse po datumu početka, datumu kraja, datumu odbrane, oceni, nastavniku, ispitnom roku, službeniku studentske službe, kompaniji i studentu.")
    public ResponseEntity<List<InternshipDTO>> search(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) LocalDate defenseDate,
            @RequestParam(required = false) Grade grade,
            @RequestParam(required = false) Long idTeacher,
            @RequestParam(required = false) Long idExamPeriod,
            @RequestParam(required = false) Long idStudentOfficer,
            @RequestParam(required = false) Long idCompany,
            @RequestParam(required = false) Long idStudent) {
        return new ResponseEntity<>(
                internshipService.findByInternship(startDate, endDate, defenseDate, grade, idTeacher, idExamPeriod, idStudentOfficer, idCompany, idStudent), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipDTO> getByID(
            @NotNull(message = "ID ne sme biti null ili prazan!")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(internshipService.findByID(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "InternshipController je naišao na grešku! " + ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Unosi novu stručnu praksu.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = Internship.class), mediaType = "application/json")})
    public ResponseEntity<InternshipDTO> add(@Valid @RequestBody @NotNull InternshipDTO internshipDTO) {
        try {
            InternshipDTO saved = internshipService.create(internshipDTO);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "InternshipController je naišao na grešku! " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira stručnu praksu.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Internship.class), mediaType = "application/json")})
    public ResponseEntity<InternshipDTO> update(@PathVariable Long id, @Valid @RequestBody InternshipDTO internshipDTO) {
        try {
            internshipDTO.setIdInternship(id);
            InternshipDTO updated = internshipService.update(internshipDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "InternshipController je naišao na grešku! " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Briše stručnu praksu.")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            internshipService.delete(id);
            return new ResponseEntity<>("Sistem je uspešno obrisao stručnu praksu!", HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "InternshipController je naišao na grešku! " + ex.getMessage());
        }
    }

}
