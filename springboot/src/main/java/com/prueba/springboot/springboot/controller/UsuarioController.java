package com.prueba.springboot.springboot.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    
import com.prueba.springboot.springboot.model.entity.Usuario;
import com.prueba.springboot.springboot.service.UsuarioService;
import com.prueba.springboot.springboot.service.exceptions.ValidacionException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) throws ValidacionException {

        // Validación de nombre de usuario
        usuarioService.validarNombreUsuario(usuario.getCorreo());

        // Guardar usuario
        usuarioService.crearUsuario(usuario);

        return ResponseEntity.created(URI.create("/usuarios/" + usuario.getIdUsuario())).body(usuario);
    }

}
