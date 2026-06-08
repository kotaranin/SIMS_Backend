/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fon.sims_backend.mapper;

/**
 *
 * @author kotar
 * @param <T>
 * @param <E>
 */
public interface DTOEntityMapper<T, E> {

    E toEntity(T t);

    T toDTO(E e);
}
