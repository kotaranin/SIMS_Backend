/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.Country;
import fon.sims_backend.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kotar
 */
@Repository
public class CountryRepo implements MyRepository<Country, Long>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Country> findAll() {
        return entityManager.createQuery("SELECT c FROM Country c", Country.class).getResultList();
    }

    @Override
    public Country findByID(Long id) throws Exception {
        Country country = entityManager.find(Country.class, id);
        if (country == null) {
            throw new Exception("Država nije pronađena!");
        }
        return country;
    }

    @Override
    public void save(Country entity) {
        if (entity.getIdCountry() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        Country country = entityManager.find(Country.class, id);
        if (country != null) {
            entityManager.remove(country);
        }
    }
}
