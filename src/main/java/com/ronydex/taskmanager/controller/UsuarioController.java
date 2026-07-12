package com.ronydex.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.ronydex.taskmanager.dto.UsuarioResponseDTO;
import com.ronydex.taskmanager.dto.UsuarioRegistroDTO;
import com.ronydex.taskmanager.service.UsuarioService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private final UsuarioService usuarioServ;
	
	public UsuarioController(UsuarioService usuarioServ){
	this.usuarioServ = usuarioServ;
	}

	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody UsuarioRegistroDTO userRegDTO){
		UsuarioResponseDTO usuarioResp = usuarioServ.registrarUsuario(userRegDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResp);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> devolverUsuario(@PathVariable Long id){
		UsuarioResponseDTO usuarioResp = usuarioServ.obtenerPorId(id);
		return ResponseEntity.ok(usuarioResp);
	}
}
