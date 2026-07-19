package com.ronydex.taskmanager.service;

import com.ronydex.taskmanager.model.Usuario;
import com.ronydex.taskmanager.repository.UsuarioRepository;
import com.ronydex.taskmanager.dto.UsuarioRegistroDTO;
import com.ronydex.taskmanager.dto.UsuarioResponseDTO;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

    public UsuarioService (UsuarioRepository usuarioRepo){
	this.usuarioRepo = usuarioRepo;
    }
	
    public UsuarioResponseDTO registrarUsuario(UsuarioRegistroDTO userRegDTO){
	Usuario usuario = new Usuario();
	usuario.setNombre(userRegDTO.getNombre());
	usuario.setEmail(userRegDTO.getEmail());
	usuario.setPassword(userRegDTO.getPassword());
	usuario.setRolAsignado(userRegDTO.getRolAsignado());
	usuario.setFechaRegistro(java.time.LocalDateTime.now());
	Usuario usuarioGuardado = usuarioRepo.save(usuario);
	UsuarioResponseDTO respuesta = new UsuarioResponseDTO();
	respuesta.setNombre(usuarioGuardado.getNombre());
	respuesta.setEmail(usuarioGuardado.getEmail());
	respuesta.setRolAsignado(usuarioGuardado.getRolAsignado());
	respuesta.setFechaRegistro(usuarioGuardado.getFechaRegistro());
	respuesta.setIdUsuario(usuarioGuardado.getIdUsuario());
	return respuesta;
    
    }

    public UsuarioResponseDTO obtenerPorId(Long id){
       Usuario usuario = usuarioRepo.findById(id)
		.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	UsuarioResponseDTO respuesta = new UsuarioResponseDTO();
	respuesta.setNombre(usuario.getNombre());
	respuesta.setEmail(usuario.getEmail());
  	respuesta.setRolAsignado(usuario.getRolAsignado());
	respuesta.setFechaRegistro(usuario.getFechaRegistro());
	respuesta.setIdUsuario(usuario.getIdUsuario());
	return respuesta;

    }


}
