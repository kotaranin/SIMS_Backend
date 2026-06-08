/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.repository.impl;

import fon.sims_backend.repository.MyRepository;
import fon.sims_backend.entity.impl.Module;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author kotar
 */
public class ModuleRepo implements MyRepository<Module, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Module> findAll() {
        return entityManager.createQuery("SELECT m FROM Module m", Module.class).getResultList();
    }

    @Override
    public Module findByID(Long id) throws Exception {
        Module module = entityManager.find(Module.class, id);
        if (module == null) {
            throw new Exception("Modul nije pronađen!");
        }
        return module;
    }

    @Override
    public void save(Module entity) {
        if (entity.getIdModule() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void deleteByID(Long id) {
        Module module = entityManager.find(Module.class, id);
        if (module != null) {
            entityManager.remove(module);
        }
    }
}
