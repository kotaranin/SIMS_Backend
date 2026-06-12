/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.controller;

import fon.sims_backend.dto.impl.StudyProgramDTO;
import fon.sims_backend.entity.impl.StudyProgram;
import fon.sims_backend.service.StudyProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/study-program")
public class StudyProgramController {

    private final StudyProgramService studyProgramService;

    public StudyProgramController(StudyProgramService studyProgramService) {
        this.studyProgramService = studyProgramService;
    }
    
    @GetMapping
    @Operation(summary = "Vraća sve studijske programe.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = StudyProgram.class), mediaType = "application/json")})
    public ResponseEntity<List<StudyProgramDTO>> getAll() {
        return new ResponseEntity<>(studyProgramService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "Pretražuje studijske programe prema traženom nivou studija.")
    public ResponseEntity<List<StudyProgramDTO>> search(
            @RequestParam(required = false) Long idStudyLevel) {
        return new ResponseEntity<>(
                studyProgramService.findByStudyLevel(idStudyLevel), HttpStatus.OK);
    }

}
