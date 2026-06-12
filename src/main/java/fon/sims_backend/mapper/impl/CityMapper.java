/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.CityDTO;
import fon.sims_backend.dto.impl.CountryDTO;
import fon.sims_backend.entity.impl.City;
import fon.sims_backend.entity.impl.Country;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class CityMapper implements DTOEntityMapper<CityDTO, City> {

    private final CountryMapper countryMapper;

    @Autowired
    public CityMapper(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    @Override
    public City toEntity(CityDTO t) {
        Country country = (t.getCountry() != null)
                ? countryMapper.toEntity(t.getCountry())
                : null;
        return new City(t.getIdCity(), t.getName(), country);
    }

    @Override
    public CityDTO toDTO(City e) {
        CountryDTO countryDTO = (e.getCountry() != null)
                ? countryMapper.toDTO(e.getCountry())
                : null;
        return new CityDTO(e.getIdCity(), e.getName(), countryDTO);
    }

}
