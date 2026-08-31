package com.ronydex.taskmanager.controller;

import com.ronydex.taskmanager.security.UsuarioPrincipal;
import com.ronydex.taskmanager.dto.UsuarioLoginDTO;
import com.ronydex.taskmanager.dto.UsuarioRegistroDTO;
import com.ronydex.taskmanager.dto.UsuarioResponseDTO;
import com.ronydex.taskmanager.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import com.ronydex.taskmanager.security.JwtUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;
import java.util.HashMap;



@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", descripción: "Endpoints para registro e inicio de sesión de usuarios")
public class AuthController{
	
	//Inyeccion de dependencias
	@Autowired
	private UsuarioService usuarioServ;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;


	@Operation(summary = "Registrar un nuevo usuario")
	@ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente")
	@ApiResponse(responseCode = "400", description = "Datos de registro inválidos")
	@PostMapping("/register")
	public ResponseEntity<UsuarioResponseDTO> registrarUsuario( @Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO){
		UsuarioResponseDTO respuesta = usuarioServ.registrarUsuario(usuarioRegistroDTO);
		return ResponseEntity.ok(respuesta);
	}

	@Operation(summary = "Iniciar sesión y obtener token JWT")
	@ApiResponse(responseCode = "200", descripcion = "Autenticación exitosa,retorna el token Bearer")
	@ApiResponse(responseCode = "400", descripcion = "Autenticación rechazada,credenciales incorrectas")
	@PostMapping("/login")
	public ResponseEntity<?> autenticarUsuario(@Valid @RequestBody UsuarioLoginDTO usuarioLoginDTO){
	Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				usuarioLoginDTO.getEmail(),
				usuarioLoginDTO.getPassword()
				)
			);
		UsuarioPrincipal userPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
		String tokenJwt = jwtUtils.generateToken(userPrincipal);

		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("token", tokenJwt);
		
		return ResponseEntity.ok(respuesta);
	}

}
