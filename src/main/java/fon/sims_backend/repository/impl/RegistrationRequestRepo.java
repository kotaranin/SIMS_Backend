/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.entity.impl.RegistrationRequest;
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
public class RegistrationRequestRepo implements MyRepository<RegistrationRequest, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RegistrationRequest> findAll() {
        return entityManager.createQuery("SELECT r FROM RegistrationRequest r", RegistrationRequest.class).getResultList();
    }

    @Override
    public RegistrationRequest findByID(Long id) throws Exception {
        RegistrationRequest registrationRequest = entityManager.find(RegistrationRequest.class, id);
        if (registrationRequest == null) {
            throw new Exception("Zahtev za registraciju nije pronađen!");
        }
        return registrationRequest;
    }

    @Override
    public void save(RegistrationRequest entity) {
        if (entity.getIdRegistrationRequest() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        RegistrationRequest registrationRequest = entityManager.find(RegistrationRequest.class, id);
        if (registrationRequest != null) {
            entityManager.remove(registrationRequest);
        }
    }
}
