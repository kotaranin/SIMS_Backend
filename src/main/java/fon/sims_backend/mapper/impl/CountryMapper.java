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
import java.util.List;

/**
 *
 * @author kotar
 */
public class CountryMapper implements DTOEntityMapper<CountryDTO, Country> {

    @Override
    public Country toEntity(CountryDTO t) {
        CityMapper cityMapper = new CityMapper();
        return new Country(
                t.getIdCountry(),
                t.getName(),
                t.getCities().stream().map(cityDTO -> (City) cityMapper.toEntity(cityDTO)).toList());
    }

    @Override
    public CountryDTO toDTO(Country e) {
        CityMapper cityMapper = new CityMapper();
        return new CountryDTO(
                e.getIdCountry(),
                e.getName(),
                e.getCities().stream().map(city -> (CityDTO) cityMapper.toDTO(city)).toList());
    }

}
