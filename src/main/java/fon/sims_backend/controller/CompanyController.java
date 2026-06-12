/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.CompanyDTO;
import fon.sims_backend.entity.impl.Company;
import fon.sims_backend.service.CompanyService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author kotar
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve kompanije.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json")})
    public ResponseEntity<List<CompanyDTO>> getAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getByID(
            @NotNull(message = "ID ne sme biti null ili prazan!")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(companyService.findByID(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CompanyController je naišao na grešku!");
        }
    }

    @PostMapping
    @Operation(summary = "Unosi novu kompaniju.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json")})
    public ResponseEntity<CompanyDTO> add(@Valid @RequestBody @NotNull CompanyDTO companyDTO) {
        try {
            CompanyDTO saved = companyService.create(companyDTO);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CompanyController je naišao na grešku!");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira kompaniju.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Company.class), mediaType = "application/json")})
    public ResponseEntity<CompanyDTO> update(@PathVariable Long id, @Valid @RequestBody CompanyDTO companyDTO) {
        try {
            companyDTO.setIdCompany(id);
            CompanyDTO updated = companyService.update(companyDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CompanyController je naišao na grešku!");
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Pretražuje kompanije po nazivu, adresi i gradu.")
    public ResponseEntity<List<CompanyDTO>> search(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String address,
            @RequestParam(required = false) Long idCity) {
        return new ResponseEntity<>(
                companyService.findByCompany(name, address, idCity), HttpStatus.OK);
    }

}
