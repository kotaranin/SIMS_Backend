/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.CountryDTO;
import fon.sims_backend.entity.impl.Country;
import fon.sims_backend.service.CountryService;
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
@RequestMapping("/api/country")
public class CountryController {

    //TODO - SLOZEN SLUCAJ
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve države.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Country.class), mediaType = "application/json")})
    public ResponseEntity<List<CountryDTO>> getAll() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Pretražuje države po imenu.")
    public ResponseEntity<List<CountryDTO>> search(
            @RequestParam(required = false) String name) {
        return new ResponseEntity<>(
                countryService.findByCountry(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getByID(
            @NotNull(message = "ID ne sme biti null ili prazan!")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(countryService.findByID(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CountryController je naišao na grešku!");
        }
    }

    @PostMapping
    @Operation(summary = "Unosi novu državu.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = Country.class), mediaType = "application/json")})
    public ResponseEntity<CountryDTO> add(@Valid @RequestBody @NotNull CountryDTO countryDTO) {
        CountryDTO saved = countryService.create(countryDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Ažurira državu.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Country.class), mediaType = "application/json")})
    public ResponseEntity<CountryDTO> update(@PathVariable Long id, @Valid @RequestBody CountryDTO countryDTO) {
        countryDTO.setIdCountry(id);
        CountryDTO updated;
        try {
            updated = countryService.update(countryDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CountryController je naišao na grešku!");
        }
    }
}
