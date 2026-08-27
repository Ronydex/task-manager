package com.ronydex.taskmanager.service;

import com.ronydex.taskmanager.model.Usuario;
import com.ronydex.taskmanager.model.Roles;
import com.ronydex.taskmanager.repository.UsuarioRepository;
import com.ronydex.taskmanager.dto.UsuarioRegistroDTO;
import com.ronydex.taskmanager.dto.UsuarioResponseDTO;
import com.ronydex.taskmanager.exception.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService (UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder){
	this.usuarioRepo = usuarioRepo;
	this.passwordEncoder = passwordEncoder;
    }
	
    public UsuarioResponseDTO registrarUsuario(UsuarioRegistroDTO userRegDTO){
	Usuario usuario = new Usuario();
	usuario.setNombre(userRegDTO.getNombre());
	usuario.setEmail(userRegDTO.getEmail());
	usuario.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
	usuario.setRolAsignado(userRegDTO.getRolAsignado());
	usuario.setFechaRegistro(java.time.LocalDateTime.now());
	
	Usuario usuarioGuardado = usuarioRepo.save(usuario);
	return convertirAResponseDTO(usuarioGuardado);
    
    }

    public UsuarioResponseDTO obtenerPorId(Long id){
       Usuario usuario = usuarioRepo.findById(id)
		.orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con el ID"));
       return convertirAResponseDTO(usuario);
    }

    public UsuarioResponseDTO borrarUsuario(Long id){
    	Usuario usuario = usuarioRepo.findById(id)
		.orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con el ID"));
	UsuarioResponseDTO respuestaBorrado = convertirAResponseDTO(usuario);
	usuarioRepo.delete(usuario);
	return respuestaBorrado;
    }

    public UsuarioResponseDTO actualizarEstUsuario(Long id, UsuarioRegistroDTO userRegDTO,Roles rolSolicitado){
    	Usuario usuario = usuarioRepo.findById(id)
		.orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con el ID"));
	usuario.setNombre(userRegDTO.getNombre());
	usuario.setEmail(userRegDTO.getEmail());
	
	if(userRegDTO.getPassword() != null && !userRegDTO.getPassword().isBlank()){
	usuario.setPassword(passwordEncoder.encode(userRegDTO.getPassword()));
	}

	if(rolSolicitado == Roles.ROL_PM || rolSolicitado == Roles.ADMIN){
    		usuario.setRolAsignado(userRegDTO.getRolAsignado());
	}
	Usuario usuarioGuardado = usuarioRepo.save(usuario);
	return convertirAResponseDTO(usuarioGuardado);
    }

    private UsuarioResponseDTO convertirAResponseDTO(Usuario usuario){
	UsuarioResponseDTO dto = new UsuarioResponseDTO();
	dto.setIdUsuario(usuario.getIdUsuario());
	dto.setNombre(usuario.getNombre());
	dto.setEmail(usuario.getEmail());
	dto.setRolAsignado(usuario.getRolAsignado());
	dto.setFechaRegistro(usuario.getFechaRegistro());
	return dto;
    	}

}
