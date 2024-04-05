package com.prueba.springboot.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.springboot.springboot.model.entity.Usuario;
import com.prueba.springboot.springboot.repository.UsuarioRepository;
import com.prueba.springboot.springboot.service.exceptions.UsuarioNoEncontradoException;
import com.prueba.springboot.springboot.service.exceptions.ValidacionException;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {

        validarUsuario(usuario);

        usuario.setCorreo(generarCorreo(usuario));

        usuarioRepository.save(usuario);

        return usuario;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
    }
    
    public Usuario actualizarUsuario(Integer idUsuario, Usuario usuarioActualizado) {
        // Validar datos del usuario
        validarUsuario(usuarioActualizado);

        Usuario usuario = obtenerUsuarioPorId(idUsuario);
        usuario.setNombres(usuarioActualizado.getNombres());
        usuario.setApellidos(usuarioActualizado.getApellidos());
        usuario.setIdentificacion(usuarioActualizado.getIdentificacion());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        usuario.setContrasena(usuarioActualizado.getContrasena());
        // Actualizar otros campos según sea necesario

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getNombres() == null || usuario.getNombres().trim().isEmpty()) {
            throw new ValidacionException("El nombre es obligatorio");
        }
        if (usuario.getApellidos() == null || usuario.getApellidos().trim().isEmpty()) {
            throw new ValidacionException("El apellido es obligatorio");
        }
        if (usuario.getIdentificacion() == null || usuario.getIdentificacion().trim().isEmpty()) {
            throw new ValidacionException("La identificación es obligatoria");
        }
    }

    private String generarCorreo(Usuario usuario) {

        // Generación del correo electrónico
        String correo = usuario.getNombres().charAt(0) + usuario.getApellidos().toLowerCase() + usuario.getIdentificacion().substring(0, 3) + "@mail.com";
    
        // Validación del formato del correo electrónico
        if (!correo.matches("[\\w\\.]+@[\\w\\.]+\\.[a-z]{2,6}")) {
            throw new ValidacionException("El correo electrónico generado no es válido");
        }
    
        // Validación de correo electrónico duplicado
        int contador = 1;
        while (usuarioRepository.existsByCorreoElectronico(correo)) {
            correo = usuario.getNombres().charAt(0) + usuario.getApellidos().toLowerCase() + usuario.getIdentificacion().substring(0, 3) + contador + "@mail.com";
            contador++;
        }
        return correo;
    }

    public void validarNombreUsuario(String nombreUsuario) throws ValidacionException {

        // Validación de caracteres no permitidos
        if (nombreUsuario.matches("[\\w\\d]+")) {
            throw new ValidacionException("El nombre de usuario no debe contener signos especiales");
        }

        // Validación de longitud
        if (nombreUsuario.length() < 8 || nombreUsuario.length() > 20) {
            throw new ValidacionException("La longitud del nombre de usuario debe ser entre 8 y 20 caracteres");
        }

        // Validación de número y letra mayúscula
        if (!nombreUsuario.matches("^(?=.*[0-9])(?=.*[A-Z]).{8,20}$")) {
            throw new ValidacionException("El nombre de usuario debe contener al menos un número y una letra mayúscula");
        }

        // Validación de duplicados
        if (usuarioRepository.existsByNombreUsuario(nombreUsuario)) {
            throw new ValidacionException("El nombre de usuario ya está en uso");
        }
    }
    
}