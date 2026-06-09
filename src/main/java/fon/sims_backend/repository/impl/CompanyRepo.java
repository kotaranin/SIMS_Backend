/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.Company;
import fon.sims_backend.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kotar
 */
@Repository
public class CompanyRepo implements MyRepository<Company, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Company> findAll() {
        return entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
    }

    @Override
    public Company findByID(Long id) throws Exception {
        Company company = entityManager.find(Company.class, id);
        if (company == null) {
            throw new Exception("Kompanija nije pronađena!");
        }
        return company;
    }

    @Override
    @Transactional
    public void save(Company entity) {
        if (entity.getIdCompany() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        Company company = entityManager.find(Company.class, id);
        if (company != null) {
            entityManager.remove(company);
        }
    }
}
