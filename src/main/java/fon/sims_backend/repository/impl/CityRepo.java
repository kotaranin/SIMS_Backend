/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.City;
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
public class CityRepo implements MyRepository<City, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> findAll() {
        return entityManager.createQuery("SELECT c FROM City c", City.class).getResultList();
    }

    @Override
    public City findByID(Long id) throws Exception {
        City city = entityManager.find(City.class, id);
        if (city == null) {
            throw new Exception("Grad nije pronađen!");
        }
        return city;
    }

    @Override
    public void save(City entity) {
        if (entity.getIdCity() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        City city = entityManager.find(City.class, id);
        if (city != null) {
            entityManager.remove(city);
        }
    }
}
