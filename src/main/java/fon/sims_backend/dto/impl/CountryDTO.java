/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.dto.impl;

import fon.sims_backend.dto.DTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author kotar
 */
public class CountryDTO implements DTO {

    private Long idCountry;
    @NotEmpty(message = "Naziv je obavezno polje!")
    @NotNull(message = "Naziv je obavezno polje!")
    private String name;
    @Valid
    private List<CityDTO> cities;

    public CountryDTO() {
    }

    public CountryDTO(Long idCountry, String name, List<CityDTO> cities) {
        this.idCountry = idCountry;
        this.name = name;
        this.cities = cities;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityDTO> cities) {
        this.cities = cities;
    }

}
