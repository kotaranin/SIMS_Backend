/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.CityDTO;
import fon.sims_backend.entity.impl.City;
import fon.sims_backend.mapper.DTOEntityMapper;

/**
 *
 * @author kotar
 */
public class CityMapper implements DTOEntityMapper<CityDTO, City> {

    @Override
    public City toEntity(CityDTO t) {
        return new City(
                t.getIdCity(),
                t.getName(),
                new CountryMapper().toEntity(t.getCountry()));
    }

    @Override
    public CityDTO toDTO(City e) {
        return new CityDTO(
                e.getIdCity(),
                e.getName(),
                new CountryMapper().toDTO(e.getCountry()));
    }

}
