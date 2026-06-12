/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.CompanyDTO;
import fon.sims_backend.entity.impl.Company;
import fon.sims_backend.mapper.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kotar
 */
@Component
public class CompanyMapper implements DTOEntityMapper<CompanyDTO, Company> {

    private final CityMapper cityMapper;

    @Autowired
    public CompanyMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @Override
    public Company toEntity(CompanyDTO t) {
        return new Company(
                t.getIdCompany(),
                t.getName(),
                t.getAddress(),
                cityMapper.toEntity(t.getCity()));
    }

    @Override
    public CompanyDTO toDTO(Company e) {
        return new CompanyDTO(
                e.getIdCompany(),
                e.getName(),
                e.getAddress(),
                cityMapper.toDTO(e.getCity()));
    }

}
