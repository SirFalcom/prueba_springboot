package com.prueba.springboot.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.springboot.springboot.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Métodos específicos para la entidad Usuario
    Usuario findByCorreo(String correo);
  
    List<Usuario> findByRol_idRol(Integer idRol);
  
  }
  