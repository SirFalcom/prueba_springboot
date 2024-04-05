package com.prueba.springboot.springboot.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Rol {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idRol;

  private String nombreRol;

  private String descripcion;

  @OneToMany(mappedBy = "rol")
  private List<Usuario> usuarios;

  @ManyToMany(mappedBy = "roles")
  private List<RolOpciones> opciones;

}
