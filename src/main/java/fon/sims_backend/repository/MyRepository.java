/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.sims_backend.repository;

import java.util.List;

/**
 *
 * @author kotar
 */
public interface MyRepository<E, ID> {

    List<E> findAll();

    E findByID(ID id) throws Exception;

    void save(E entity);

}
