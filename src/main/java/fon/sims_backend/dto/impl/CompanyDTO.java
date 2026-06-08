/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author kotar
 */
public class CompanyDTO implements DTO {

    private Long idCompany;
    @NotNull(message = "Naziv je obavezno polje!")
    @NotEmpty(message = "Naziv je obavezno polje!")
    private String name;
    @NotNull(message = "Adresa je obavezno polje!")
    @NotEmpty(message = "Adresa je obavezno polje!")
    private String address;
    @NotNull(message = "Grad je obavezno polje!")
    @Valid
    private CityDTO city;

    public CompanyDTO() {
    }

    public CompanyDTO(Long idCompany, String name, String address, CityDTO city) {
        this.idCompany = idCompany;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

}
