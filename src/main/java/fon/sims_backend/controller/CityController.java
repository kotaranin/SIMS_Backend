/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.CityDTO;
import fon.sims_backend.entity.impl.City;
import fon.sims_backend.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kotar
 */
@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    @Operation(summary = "Vraća sve gradove.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = City.class), mediaType = "application/json")})
    public ResponseEntity<List<CityDTO>> getAll() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Vraća gradove u traženoj državi")
    public ResponseEntity<List<CityDTO>> search(
            @RequestParam(required = false) Long idCountry) {
        return new ResponseEntity<>(
                cityService.findByCountry(idCountry), HttpStatus.OK);
    }
}
