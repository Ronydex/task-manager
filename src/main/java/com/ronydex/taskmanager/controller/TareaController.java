package com.ronydex.taskmanager.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.ronydex.taskmanager.service.TareaService;
import com.ronydex.taskmanager.dto.TareaRegistroDTO;
import com.ronydex.taskmanager.dto.TareaResponseDTO;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

	private final TareaService tareaServ;

	public TareaController(TareaService tareaServ){
	this.tareaServ = tareaServ;
	}

	@PostMapping
	public ResponseEntity<TareaResponseDTO> crearTarea (@Valid @RequestBody TareaRegistroDTO tareaRegDTO){
		TareaResponseDTO tareaResp = tareaServ.crearTarea(tareaRegDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(tareaResp);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TareaResponseDTO> devolverTarea(@PathVariable Long id){
		TareaResponseDTO tareaResp = tareaServ.obtenerPorId(id);
		return ResponseEntity.ok(tareaResp);
	}

	@GetMapping()
	public ResponseEntity<List<TareaResponseDTO>> listarTodasTareas(){
		List<TareaResponseDTO> tareaResp = tareaServ.listarTodasTareas();
		return ResponseEntity.ok(tareaResp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TareaResponseDTO> actualizarTarea(@PathVariable Long id,
		       						@RequestBody TareaRegistroDTO tareaRegDTO){
		TareaResponseDTO tareaResp = tareaServ.actualizarTarea(id,tareaRegDTO);
		return ResponseEntity.ok(tareaResp);
	}

	@DeleteMapping("/{id}")
	public  ResponseEntity<Void> borrarTarea(@PathVariable Long id){
		tareaServ.borrarTarea(id);
		return ResponseEntity.noContent().build();
	}
}
