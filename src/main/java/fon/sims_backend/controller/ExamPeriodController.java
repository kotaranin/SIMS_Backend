/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.ExamPeriodDTO;
import fon.sims_backend.entity.impl.ExamPeriod;
import fon.sims_backend.service.ExamPeriodService;
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
@RequestMapping("/api/exam-period")
public class ExamPeriodController {

    private final ExamPeriodService examPeriodService;

    public ExamPeriodController(ExamPeriodService examPeriodService) {
        this.examPeriodService = examPeriodService;
    }

    @GetMapping("/search")
    @Operation(summary = "Pretražuje ispitne rokove po nazivu, datumu početka i datumu kraja.")
    public ResponseEntity<List<ExamPeriodDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        return new ResponseEntity<>(
                examPeriodService.findByExamPeriod(name, startDate, endDate), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Vraća sve ispitne rokove.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = ExamPeriod.class), mediaType = "application/json")})
    public ResponseEntity<List<ExamPeriodDTO>> getAll() {
        return new ResponseEntity<>(examPeriodService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamPeriodDTO> getByID(
            @NotNull(message = "ID ne sme biti null ili prazan!")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(examPeriodService.findByID(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ExamPeriodController je naišao na grešku!");
        }
    }

    @PostMapping
    @Operation(summary = "Unosi nov ispitni rok.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = ExamPeriod.class), mediaType = "application/json")})
    public ResponseEntity<ExamPeriodDTO> add(@Valid @RequestBody @NotNull ExamPeriodDTO examPeriodDTO) {
        ExamPeriodDTO saved = examPeriodService.create(examPeriodDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira ispitni rok.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = ExamPeriod.class), mediaType = "application/json")})
    public ResponseEntity<ExamPeriodDTO> update(@PathVariable Long id, @Valid @RequestBody ExamPeriodDTO examPeriodDTO) {
        examPeriodDTO.setIdExamPeriod(id);
        ExamPeriodDTO updated = examPeriodService.update(examPeriodDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
