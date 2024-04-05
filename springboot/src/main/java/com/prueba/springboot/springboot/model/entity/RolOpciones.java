package com.prueba.springboot.springboot.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_opciones")
public class RolOpciones {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idOpcion;

  private String nombreOpcion;

  private String descripcion;

  private List<Rol> roles;

}
