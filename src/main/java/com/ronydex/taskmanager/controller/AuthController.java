package com.ronydex.taskmanager.controller;

import com.ronydex.taskmanager.dto.UsuarioRegistroDTO;
import com.ronydex.taskmanager.dto.UsuarioResponseDTO;
import com.ronydex.taskmanager.service.UsuarioService;
import com.ronydex.taskmanager.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import com.ronydex.taskmanager.security.JwtUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;


@RestController
@RequestMapping("api/auth/")
public class AuthController{
	
	//Inyeccion de dependencias
	@Autowired
	private UsuarioService usuarioServ;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/register")
	public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@RequestBody Usuario usuario){
		UsuarioResponseDTO respuesta = usuarioServ.registrarUsuario(dto);
		return ResponseEntity.ok(respuesta);
	}

	@PostMapping("/login")
	public ResponseEntity<UsuarioResponseDTO> ingresarUsuario(RequestBody Usuario usuario){
	
	}

}
