/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.mapper.impl;

import fon.sims_backend.dto.impl.CompanyDTO;
import fon.sims_backend.entity.impl.Company;
import fon.sims_backend.mapper.DTOEntityMapper;

/**
 *
 * @author kotar
 */
public class CompanyMapper implements DTOEntityMapper<CompanyDTO, Company> {

    @Override
    public Company toEntity(CompanyDTO t) {
        return new Company(
                t.getIdCompany(),
                t.getName(),
                t.getAddress(),
                new CityMapper().toEntity(t.getCity()));
    }

    @Override
    public CompanyDTO toDTO(Company e) {
        return new CompanyDTO(
                e.getIdCompany(),
                e.getName(),
                e.getAddress(),
                new CityMapper().toDTO(e.getCity()));
    }

}
