/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author kotar
 */
public class ReportDTO implements DTO {

    private Long idReport;
    @NotNull(message = "Naziv je obavezno polje!")
    @NotEmpty(message = "Naziv je obavezno polje!")
    private String fileName;
    @NotNull(message = "Datoteka je obavezno polje!")
    private byte[] fileContent;

    public ReportDTO() {
    }

    public ReportDTO(Long idReport, String fileName, byte[] fileContent) {
        this.idReport = idReport;
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public Long getIdReport() {
        return idReport;
    }

    public void setIdReport(Long idReport) {
        this.idReport = idReport;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
