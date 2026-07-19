package com.ronydex.taskmanager.service;

import com.ronydex.taskmanager.model.Tarea;
import com.ronydex.taskmanager.model.Usuario;
import com.ronydex.taskmanager.repository.UsuarioRepository;
import com.ronydex.taskmanager.repository.TareaRepository;
import com.ronydex.taskmanager.dto.TareaRegistroDTO;
import com.ronydex.taskmanager.dto.TareaResponseDTO;

import org.springframework.stereotype.Service;

@Service
public class TareaService {

	private final TareaRepository tareaRepo;

	private final UsuarioRepository usuarioRepo;

	public TareaService (TareaRepository tareaRepo,UsuarioRepository usuarioRepo){
		this.tareaRepo = tareaRepo;
		this.usuarioRepo = usuarioRepo;
	}


	public TareaResponseDTO crearTarea(TareaRegistroDTO tareaRegDTO){
		Usuario creador = usuarioRepo.findByEmail(tareaRegDTO.getCreadoPor())
			.orElseThrow(() -> new RuntimeException("El usuario creador no existe"));
		Usuario asignado = usuarioRepo.findByEmail(tareaRegDTO.getAsignadoA())
			.orElseThrow(() -> new RuntimeException("El usuario asignado no existe"));
		Tarea tarea = new Tarea();
		tarea.setCreadoPor(creador);
		tarea.setAsignadoA(asignado);
		tarea.setDescripcionTarea(tareaRegDTO.getDescripcionTarea());
		tarea.setEstadoActTar(tareaRegDTO.getEstadoActTar());
		tarea.setTituloTarea(tareaRegDTO.getTituloTarea());
		tarea.setFechaCreacion(java.time.LocalDateTime.now());
		Tarea tareaGuardada = tareaRepo.save(tarea);
		TareaResponseDTO respuestaTar = new TareaResponseDTO();
		respuestaTar.setTituloTarea(tareaGuardada.getTituloTarea());
		respuestaTar.setDecripcionTarea(tareaGuardada.getDescripcionTarea());
		respuestaTar.setCreadoPorUser(tareaGuardada.getCreadoPor().getNombre());
		respuestaTar.setAsignadoAUser(tareaGuardada.getAsignadoA().getNombre());
		return respuestaTar;
	}
}
