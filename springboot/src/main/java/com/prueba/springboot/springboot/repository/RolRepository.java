package com.prueba.springboot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.springboot.springboot.model.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByNombreRol(String nombreRol);
    
}
  