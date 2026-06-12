/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.ReportDTO;
import fon.sims_backend.entity.impl.Report;
import fon.sims_backend.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author kotar
 */
@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve dnevnike prakse.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Report.class), mediaType = "application/json")})
    public ResponseEntity<List<ReportDTO>> getAll() {
        return new ResponseEntity<>(reportService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getByID(
            @NotNull(message = "ID ne sme biti null ili prazan!")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(reportService.findByID(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ReportController je naišao na grešku!");
        }
    }

    @PostMapping
    @Operation(summary = "Unosi nov dnevnik prakse.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = Report.class), mediaType = "application/json")})
    public ResponseEntity<ReportDTO> add(@Valid @RequestBody @NotNull ReportDTO reportDTO) {
        ReportDTO saved = reportService.create(reportDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira dnevnik prakse.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Report.class), mediaType = "application/json")})
    public ResponseEntity<ReportDTO> update(@PathVariable Long id, @Valid @RequestBody ReportDTO reportDTO) {
        reportDTO.setIdReport(id);
        ReportDTO updated = reportService.update(reportDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
