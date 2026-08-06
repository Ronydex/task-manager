package com.ronydex.taskmanager.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import com.ronydex.taskmanager.dto.UsuarioResponseDTO;
import com.ronydex.taskmanager.dto.UsuarioRegistroDTO;
import com.ronydex.taskmanager.service.UsuarioService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Endpoints para la gestión de los usuarios")
public class UsuarioController {

	private final UsuarioService usuarioServ;
	
	public UsuarioController(UsuarioService usuarioServ){
	this.usuarioServ = usuarioServ;
	}
	
	@Operation(summary = "Crear un usuario", description = "Permite crear un usuario de acuerdo a las caracteristicas del Model")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Usuario creado de manera exitosa"),
		@ApiResponse(responseCode = "400", description = "Fallo al crear el usuario")
	})
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody UsuarioRegistroDTO userRegDTO){
		UsuarioResponseDTO usuarioResp = usuarioServ.registrarUsuario(userRegDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResp);
		
	}

	@Operation(summary = "Devolver un usuario por Id", description = "Permite devolver el usuario solicitado por medio de la Id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Usuario devuelto de manera exitosa por el Id"),
		@ApiResponse(responseCode = "400", description = "Error al encontrar al usuario por medio del Id")
	})	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> devolverUsuario(@PathVariable Long id){
		UsuarioResponseDTO usuarioResp = usuarioServ.obtenerPorId(id);
		return ResponseEntity.ok(usuarioResp);
	}
}
