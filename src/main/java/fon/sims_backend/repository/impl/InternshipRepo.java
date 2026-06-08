/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.Internship;
import fon.sims_backend.repository.MyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author kotar
 */
public class InternshipRepo implements MyRepository<Internship, Long>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Internship> findAll() {
        return entityManager.createQuery("SELECT i FROM Internship i", Internship.class).getResultList();
    }

    @Override
    public Internship findByID(Long id) throws Exception {
        Internship internship = entityManager.find(Internship.class, id);
        if (internship == null) {
            throw new Exception("Studentska praksa nije pronađena!");
        }
        return internship;
    }

    @Override
    public void save(Internship entity) {
        if (entity.getIdInternship() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        Internship internship = entityManager.find(Internship.class, id);
        if (internship != null) {
            entityManager.remove(internship);
        }
    }
}
