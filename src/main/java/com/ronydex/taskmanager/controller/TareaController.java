package com.ronydex.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.ronydex.taskmanager.dto.TareaRegistroDTO;
import com.ronydex.taskmanager.dto.TareaResponseDTO;
import com.ronydex.taskmanager.dto.UsuarioResponseDTO;
import com.ronydex.taskmanager.service.TareaService;
import com.ronydex.taskmanager.service.UsuarioService;

@RestController
@RequestMapping("/api/tarea")
public class TareaController {

	private final TareaService tareaServ;

	public TareaController(TareaService tareaServ){
	this.tareaServ = tareaServ;
	}

	@PostMapping
		public ResponseEntity<TareaResponseDTO> crearTarea (@Valid @RequestBody TareaRegistroDTO tareaRegDTO){
			UsuarioResponseDTO usuarioResp = 
		}

}
