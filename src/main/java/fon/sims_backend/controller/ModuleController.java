/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.ModuleDTO;
import fon.sims_backend.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kotar
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/module")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/search")
    @Operation(summary = "Vraća module u traženom studijskom programu.")
    public ResponseEntity<List<ModuleDTO>> search(
            @RequestParam(required = false) Long idStudyProgram) {
        return new ResponseEntity<>(
                moduleService.findByStudyProgram(idStudyProgram), HttpStatus.OK);
    }
}
