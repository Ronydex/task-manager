package com.ronydex.taskmanager.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.ronydex.taskmanager.service.TareaService;
import com.ronydex.taskmanager.dto.TareaRegistroDTO;
import com.ronydex.taskmanager.dto.TareaResponseDTO;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")
@Tag(name="Tareas", description = "Endpoints para la gestión y asignación de tareas")
public class TareaController {

	private final TareaService tareaServ;

	public TareaController(TareaService tareaServ){
	this.tareaServ = tareaServ;
	}

	@Operation(summary= "Crear una tarea", description = "Permite crear una tarea de acuerdo a las caracteristicas que hay en su Model")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Tarea creada de manera exitosa"),
		@ApiResponse(responseCode = "400", description = "Fallo al crear la tarea solicitada")
	})
	@PostMapping
	public ResponseEntity<TareaResponseDTO> crearTarea (@Valid @RequestBody TareaRegistroDTO tareaRegDTO){
		TareaResponseDTO tareaResp = tareaServ.crearTarea(tareaRegDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(tareaResp);
	}
	
	@Operation(summary = "Trae una tarea por Id", description = "Retorna una tarea especifica por medio de su id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Tarea encontrada exitosamente"),
		@ApiResponse(responseCode = "404", description = "No se encontró la tarea solicitada")	
	
	})
	@GetMapping("/{id}")
	public ResponseEntity<TareaResponseDTO> devolverTarea(@PathVariable Long id){
		TareaResponseDTO tareaResp = tareaServ.obtenerPorId(id);
		return ResponseEntity.ok(tareaResp);
	}
	@Operation(summary = "Traer todas las tareas en Lista", description = "Retorna todas las tareas a través de una Lista con su DTO")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Listado de tareas encontrado de manera exitosa"),
		@ApiResponse(responseCode = "400", description = "Error al listar las tareas solicitadas")
	})
	@GetMapping()
	public ResponseEntity<List<TareaResponseDTO>> listarTodasTareas(){
		List<TareaResponseDTO> tareaResp = tareaServ.listarTodasTareas();
		return ResponseEntity.ok(tareaResp);
	}

	@Operation(summary = "Actualizar algun dato de una tarea", description = "Permite actualizar cualquier dato de una tarea elegida por el Id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Dato de la Tarea Actualizado de manera correcta"),
		@ApiResponse(responseCode = "400", description = "Error al intentar actualizar un Dato de la Tarea")
	})	
	@PutMapping("/{id}")
	public ResponseEntity<TareaResponseDTO> actualizarTarea(@PathVariable Long id,
		       						@Valid @RequestBody TareaRegistroDTO tareaRegDTO){
		TareaResponseDTO tareaResp = tareaServ.actualizarTarea(id,tareaRegDTO);
		return ResponseEntity.ok(tareaResp);
	}

	@Operation(summary = "Borrar la Tarea seleccionada por Id", description = "Permite borrar una tarea por medio de su Id")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Tarea borrada de manera exitosa"),
		@ApiResponse(responseCode = "400", description = "Error al borrar la tarea seleccionada")
	})
	@DeleteMapping("/{id}")
	public  ResponseEntity<Void> borrarTarea(@PathVariable Long id){
		tareaServ.borrarTarea(id);
		return ResponseEntity.noContent().build();
	}
}
