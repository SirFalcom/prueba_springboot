package com.prueba.springboot.springboot.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idUsuario;

  private String nombres;

  private String apellidos;

  private String identificacion;

  @Column(unique = true)
  private String correo;

  private String contrasena;

  @ManyToOne
  @JoinColumn(name = "rol_idRol")
  
  private Rol rol;

}

