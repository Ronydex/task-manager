package com.ronydex.taskmanager.service;

import com.ronydex.taskmanager.model.Tarea;
import com.ronydex.taskmanager.model.Usuario;
import com.ronydex.taskmanager.repository.UsuarioRepository;
import com.ronydex.taskmanager.repository.TareaRepository;
import com.ronydex.taskmanager.dto.TareaRegistroDTO;
import com.ronydex.taskmanager.dto.TareaResponseDTO;
import com.ronydex.taskmanager.exception.RecursoNoEncontradoException;

import java.util.List;
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
			.orElseThrow(() -> new RecursoNoEncontradoException("El usuario creador  no existe"));
		Usuario asignado = usuarioRepo.findByEmail(tareaRegDTO.getAsignadoA())
			.orElseThrow(() -> new RecursoNoEncontradoException("El usuario asignado no existe"));
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
		respuestaTar.setDescripcionTarea(tareaGuardada.getDescripcionTarea());
		respuestaTar.setCreadoPorUser(tareaGuardada.getCreadoPor().getNombre());
		respuestaTar.setAsignadoAUser(tareaGuardada.getAsignadoA().getNombre());
		respuestaTar.setIdTarea(tareaGuardada.getIdTarea());
		respuestaTar.setEstadoActTar(tareaGuardada.getEstadoActTar());
		respuestaTar.setFechaCreacion(tareaGuardada.getFechaCreacion());
		return respuestaTar;
	}

	public TareaResponseDTO obtenerPorId(Long id){
		Tarea tarea = tareaRepo.findById(id)
			.orElseThrow(() -> new RecursoNoEncontradoException("La tarea no fue encontrada o no existe"));
		TareaResponseDTO respuestaTar = new TareaResponseDTO();
		respuestaTar.setTituloTarea(tarea.getTituloTarea());
		respuestaTar.setDescripcionTarea(tarea.getDescripcionTarea());
		respuestaTar.setCreadoPorUser(tarea.getCreadoPor().getNombre());
		respuestaTar.setAsignadoAUser(tarea.getAsignadoA().getNombre());
		respuestaTar.setIdTarea(tarea.getIdTarea());
		respuestaTar.setEstadoActTar(tarea.getEstadoActTar());
		respuestaTar.setFechaCreacion(tarea.getFechaCreacion());
		return respuestaTar;
	}

	public TareaResponseDTO borrarTarea(Long id){
		Tarea tarea = tareaRepo.findById(id)
			.orElseThrow(() -> new RecursoNoEncontradoException("La tarea no fue encontrada o no existe"));
		TareaResponseDTO respuestaBorrado = new TareaResponseDTO();
		respuestaBorrado.setIdTarea(tarea.getIdTarea());
		respuestaBorrado.setTituloTarea(tarea.getTituloTarea());
		respuestaBorrado.setDescripcionTarea(tarea.getDescripcionTarea());
		respuestaBorrado.setEstadoActTar(tarea.getEstadoActTar());
		respuestaBorrado.setCreadoPorUser(tarea.getCreadoPor().getNombre());
		respuestaBorrado.setAsignadoAUser(tarea.getAsignadoA().getNombre());
		respuestaBorrado.setFechaCreacion(tarea.getFechaCreacion());
		tareaRepo.delete(tarea);
		return respuestaBorrado;
	}

	public TareaResponseDTO actualizarTarea(Long id,TareaRegistroDTO tareaRegDTO){
		Tarea tarea = tareaRepo.findById(id)
			.orElseThrow(() -> new RecursoNoEncontradoException("La tarea no fue encontrada o no existe"));
		tarea.setTituloTarea(tareaRegDTO.getTituloTarea());
		tarea.setDescripcionTarea(tareaRegDTO.getDescripcionTarea());
		tarea.setEstadoActTar(tareaRegDTO.getEstadoActTar());
		Usuario asignado = usuarioRepo.findByEmail(tareaRegDTO.getAsignadoA())
			.orElseThrow(() -> new RecursoNoEncontradoException("El usuario asignado no existe"));
		tarea.setAsignadoA(asignado);
		tareaRepo.save(tarea);
		TareaResponseDTO respuesta = new TareaResponseDTO();
		respuesta.setTituloTarea(tarea.getTituloTarea());
		respuesta.setDescripcionTarea(tarea.getDescripcionTarea());
		respuesta.setCreadoPorUser(tarea.getCreadoPor().getNombre());
		respuesta.setAsignadoAUser(tarea.getAsignadoA().getNombre());
		respuesta.setIdTarea(tarea.getIdTarea());
		respuesta.setEstadoActTar(tarea.getEstadoActTar());
		respuesta.setFechaCreacion(tarea.getFechaCreacion());
		return respuesta;
	}

	public List<TareaResponseDTO>  listarTodasTareas(){
		return  tareaRepo.findAll()
			.stream()
			.map(this::mapearADTO)
			.toList();
	}

	//Metodo Helper del DTO
	private TareaResponseDTO mapearADTO(Tarea tarea){
		TareaResponseDTO dto = new TareaResponseDTO();
		dto.setIdTarea(tarea.getIdTarea());
		dto.setTituloTarea(tarea.getTituloTarea());
		dto.setDescripcionTarea(tarea.getDescripcionTarea());
		dto.setEstadoActTar(tarea.getEstadoActTar());
		dto.setFechaCreacion(tarea.getFechaCreacion());
		dto.setCreadoPorUser(tarea.getCreadoPor().getNombre());
		dto.setAsignadoAUser(tarea.getAsignadoA().getNombre());
		return dto;
	}
	
}
