package com.ronydex.taskmanager.service;

import com.ronydex.taskmanager.model.Usuario;
import com.ronydex.taskmanager.model.Roles;
import com.ronydex.taskmanager.repository.UsuarioRepository;
import com.ronydex.taskmanager.dto.UsuarioRegistroDTO;
import com.ronydex.taskmanager.dto.UsuarioResponseDTO;
import com.ronydex.taskmanager.exception.RecursoNoEncontradoException;
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
		.orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con el ID"));
	UsuarioResponseDTO respuesta = new UsuarioResponseDTO();
	respuesta.setNombre(usuario.getNombre());
	respuesta.setEmail(usuario.getEmail());
  	respuesta.setRolAsignado(usuario.getRolAsignado());
	respuesta.setFechaRegistro(usuario.getFechaRegistro());
	respuesta.setIdUsuario(usuario.getIdUsuario());
	return respuesta;

    }

    public UsuarioResponseDTO borrarUsuario(Long id){
    	Usuario usuario = usuarioRepo.findById(id)
		.orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con el ID"));
	UsuarioResponseDTO respuestaBorrado = new UsuarioResponseDTO();
	respuestaBorrado.setIdUsuario(usuario.getIdUsuario());
	respuestaBorrado.setNombre(usuario.getNombre());
	respuestaBorrado.setEmail(usuario.getEmail());
	respuestaBorrado.setRolAsignado(usuario.getRolAsignado());
	respuestaBorrado.setFechaRegistro(usuario.getFechaRegistro());
	usuarioRepo.delete(usuario);
	return respuestaBorrado;
    }

    public UsuarioResponseDTO actualizarEstUsuario(Long id, UsuarioRegistroDTO userRegDTO,Roles rolSolicitado){
    	Usuario usuario = usuarioRepo.findById(id)
		.orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con el ID"));
	usuario.setNombre(userRegDTO.getNombre());
	usuario.setEmail(userRegDTO.getEmail());
	usuario.setPassword(userRegDTO.getPassword());

	if(rolSolicitado == Roles.ROL_PM || rolSolicitado == Roles.ADMIN){
    		usuario.setRolAsignado(userRegDTO.getRolAsignado());
	}
	Usuario usuarioGuardado = usuarioRepo.save(usuario);
	UsuarioResponseDTO respuesta = new UsuarioResponseDTO();
	respuesta.setIdUsuario(usuario.getIdUsuario());
	respuesta.setNombre(usuario.getNombre());
	respuesta.setEmail(usuario.getEmail());
	respuesta.setRolAsignado(usuario.getRolAsignado());
	respuesta.setFechaRegistro(usuario.getFechaRegistro());
	return respuesta;
    }
}
