package com.prueba.springboot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.springboot.springboot.model.entity.RolOpciones;

public interface OpcionRepository extends JpaRepository<RolOpciones, Integer> {

    RolOpciones findByNombreOpcion(String nombreOpcion);

}