/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.RegistrationRequest;
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
public class RegistrationRequestRepo implements MyRepository<RegistrationRequest, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<RegistrationRequest> findAll() {
        return entityManager.createQuery("SELECT r FROM RegistrationRequest r", RegistrationRequest.class).getResultList();
    }

    @Override
    @Transactional
    public RegistrationRequest findByID(Long id) throws Exception {
        RegistrationRequest registrationRequest = entityManager.find(RegistrationRequest.class, id);
        if (registrationRequest == null) {
            throw new Exception("Zahtev za registraciju nije pronađen!");
        }
        return registrationRequest;
    }

    @Transactional
    public List<RegistrationRequest> findByRegistrationRequest(String firstName, String lastName) {
        String query = "SELECT r FROM RegistrationRequest r WHERE 1=1 ";
        if (firstName != null) {
            query += "AND LOWER(r.firstName) LIKE LOWER(:firstName) ";
        }
        if (lastName != null) {
            query += "AND LOWER(r.lastName) LIKE LOWER(:lastName) ";
        }
        var q = entityManager.createQuery(query);
        if (firstName != null) {
            q.setParameter("firstName", "%" + firstName + "%");
        }
        if (lastName != null) {
            q.setParameter("lastName", "%" + lastName + "%");
        }
        return q.getResultList();
    }

    @Override
    @Transactional
    public void save(RegistrationRequest entity) {
        if (entity.getIdRegistrationRequest() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Transactional
    public void deleteByID(Long id) {
        RegistrationRequest registrationRequest = entityManager.find(RegistrationRequest.class, id);
        if (registrationRequest != null) {
            entityManager.remove(registrationRequest);
        }
    }
}
