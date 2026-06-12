/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.service;

import fon.sims_backend.dto.impl.CompanyDTO;
import fon.sims_backend.entity.impl.Company;
import fon.sims_backend.mapper.impl.CompanyMapper;
import fon.sims_backend.repository.impl.CompanyRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kotar
 */
@Service
public class CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepo companyRepo;

    @Autowired
    public CompanyService(CompanyMapper companyMapper, CompanyRepo companyRepo) {
        this.companyMapper = companyMapper;
        this.companyRepo = companyRepo;
    }

    public List<CompanyDTO> findAll() {
        return companyRepo.findAll().stream().map(companyMapper::toDTO).toList();
    }

    public CompanyDTO findByID(Long id) throws Exception {
        return companyMapper.toDTO(companyRepo.findByID(id));
    }
    
    public List<CompanyDTO> findByCompany(String name, String address, Long idCity) {
        return companyRepo.findByCompany(name, address, idCity).stream().map(companyMapper::toDTO).toList();
    }

    public CompanyDTO create(CompanyDTO companyDTO) throws Exception {
        Company company = companyMapper.toEntity(companyDTO);
        companyRepo.save(company);
        return companyMapper.toDTO(companyRepo.findByID(company.getIdCompany()));
    }

    public CompanyDTO update(CompanyDTO companyDTO) throws Exception {
        Company company = companyMapper.toEntity(companyDTO);
        companyRepo.save(company);
        return companyMapper.toDTO(companyRepo.findByID(company.getIdCompany()));
    }

}
