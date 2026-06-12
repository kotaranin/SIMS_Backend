/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.CityDTO;
import fon.sims_backend.dto.impl.CountryDTO;
import fon.sims_backend.entity.impl.City;
import fon.sims_backend.entity.impl.Country;
import fon.sims_backend.mapper.impl.CountryMapper;
import fon.sims_backend.repository.impl.CityRepo;
import fon.sims_backend.repository.impl.CountryRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class CountryService {

    private final CountryMapper countryMapper;
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;

    @Autowired
    public CountryService(CountryMapper countryMapper, CountryRepo countryRepo, CityRepo cityRepo) {
        this.countryMapper = countryMapper;
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
    }

    public List<CountryDTO> findAll() {
        return countryRepo.findAll().stream().map(countryMapper::toDTO).toList();
    }

    public CountryDTO findByID(Long id) throws Exception {
        return countryMapper.toDTO(countryRepo.findByID(id));
    }

    public List<CountryDTO> findByCountry(String name) {
        return countryRepo.findByCountry(name).stream().map(countryMapper::toDTO).toList();
    }

    public CountryDTO create(CountryDTO countryDTO) {
        Country country = countryMapper.toEntity(countryDTO);
        countryRepo.save(country);
        return countryMapper.toDTO(country);
    }

    public CountryDTO update(CountryDTO countryDTO) throws Exception {
        Country existing = countryRepo.findByID(countryDTO.getIdCountry());
        existing.setName(countryDTO.getName());
        if (countryDTO.getCities() != null) {
            for (CityDTO cityDTO : countryDTO.getCities()) {
                if (cityDTO.getIdCity() != null) {
                    City existingCity = cityRepo.findByID(cityDTO.getIdCity());
                    existingCity.setName(cityDTO.getName());
                    cityRepo.save(existingCity);
                } else {
                    City newCity = new City(null, cityDTO.getName(), existing);
                    cityRepo.save(newCity);
                }
            }
        }
        countryRepo.save(existing);
        return countryMapper.toDTO(countryRepo.findByID(existing.getIdCountry()));
    }
}
