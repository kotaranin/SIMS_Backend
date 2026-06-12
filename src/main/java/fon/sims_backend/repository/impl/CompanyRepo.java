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
    @Transactional
    public List<Company> findAll() {
        return entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
    }

    @Override
    @Transactional
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

    @Transactional
    public List<Company> findByCompany(String name, String address, Long idCity) {
        String query = "SELECT c FROM Company c INNER JOIN FETCH c.city WHERE 1=1 ";
        if (name != null) {
            query += "AND LOWER(c.name) LIKE LOWER(:name) ";
        }
        if (address != null) {
            query += "AND LOWER(c.address) LIKE LOWER(:address) ";
        }
        if (idCity != null) {
            query += "AND c.city.idCity = :idCity ";
        }
        var q = entityManager.createQuery(query);
        if (name != null) {
            q.setParameter("name", "%" + name + "%");
        }
        if (address != null) {
            q.setParameter("address", "%" + address + "%");
        }
        if (idCity != null) {
            q.setParameter("idCity", idCity);
        }
        return q.getResultList();
    }
}
