/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.RegistrationRequestDTO;
import fon.sims_backend.entity.impl.RegistrationRequest;
import fon.sims_backend.service.RegistrationRequestService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/registration-request")
public class RegistrationRequestController {

    private final RegistrationRequestService registrationRequestService;

    public RegistrationRequestController(RegistrationRequestService registrationRequestService) {
        this.registrationRequestService = registrationRequestService;
    }
    
    @GetMapping("/search")
    @Operation(summary = "Pretražuje zahteve za registraciju po imenu i prezimenu.")
    public ResponseEntity<List<RegistrationRequestDTO>> search(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return new ResponseEntity<>(
                registrationRequestService.findByRegistrationRequest(firstName, lastName), HttpStatus.OK);
    }
    
    @GetMapping
    @Operation(summary = "Vraća sve zahteve za registraciju.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = RegistrationRequestDTO.class), mediaType = "application/json")})
    public ResponseEntity<List<RegistrationRequestDTO>> getAll() {
        return new ResponseEntity<>(registrationRequestService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> countAll() {
        return ResponseEntity.ok(registrationRequestService.countAll());
    }

    @PostMapping
    @Operation(summary = "Unosi nov zahtev za registraciju.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = RegistrationRequest.class), mediaType = "application/json")})
    public ResponseEntity<RegistrationRequestDTO> add(@Valid @RequestBody @NotNull RegistrationRequestDTO registrationRequestDTO) {
        try {
            RegistrationRequestDTO saved = registrationRequestService.create(registrationRequestDTO);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RegistrationRequestController je naišao na grešku!");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        registrationRequestService.deleteById(id);
        return new ResponseEntity<>("Sistem je uspešno obrisao zahtev za registraciju!", HttpStatus.OK);
    }
}
