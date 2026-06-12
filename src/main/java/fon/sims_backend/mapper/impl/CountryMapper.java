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
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class CountryMapper implements DTOEntityMapper<CountryDTO, Country> {

    @Override
    public Country toEntity(CountryDTO t) {
        Country country = new Country(t.getIdCountry(), t.getName(), null);
        if (t.getCities() != null) {
            List<City> cities = t.getCities().stream().map(cityDTO -> {
                City city = new City(cityDTO.getIdCity(), cityDTO.getName(), country);
                return city;
            }).toList();
            country.setCities(cities);
        }
        return country;
    }

    @Override
    public CountryDTO toDTO(Country e) {
        List<CityDTO> cityDTOs = (e.getCities() != null)
            ? e.getCities().stream().map(city ->
                new CityDTO(city.getIdCity(), city.getName(), null)
              ).toList()
            : List.of();
        return new CountryDTO(e.getIdCountry(), e.getName(), cityDTOs);
    }

}
