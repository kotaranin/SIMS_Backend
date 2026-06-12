/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.CityDTO;
import fon.sims_backend.mapper.impl.CityMapper;
import fon.sims_backend.repository.impl.CityRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class CityService {

    private final CityMapper cityMapper;
    private final CityRepo cityRepo;

    @Autowired
    public CityService(CityMapper cityMapper, CityRepo cityRepo) {
        this.cityMapper = cityMapper;
        this.cityRepo = cityRepo;
    }

    public List<CityDTO> findAll() {
        return cityRepo.findAll().stream().map(cityMapper::toDTO).toList();
    }
    
    public List<CityDTO> findByCountry(Long idCountry) {
        return cityRepo.findByCountry(idCountry).stream().map(cityMapper::toDTO).toList();
    }
}
