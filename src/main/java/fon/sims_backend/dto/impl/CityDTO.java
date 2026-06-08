/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author kotar
 */
public class CityDTO implements DTO {

    private Long idCity;
    private String name;
    @NotNull(message = "Država je obavezno polje!")
    @Valid
    private CountryDTO country;

    public CityDTO() {
    }

    public CityDTO(Long idCity, String name, CountryDTO country) {
        this.idCity = idCity;
        this.name = name;
        this.country = country;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

}
