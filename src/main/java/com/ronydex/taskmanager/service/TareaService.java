package com.ronydex.taskmanager.service;

import com.ronydex.taskmanager.model.Tarea;
import com.ronydex.taskmanager.model.Usuario;
import com.ronydex.taskmanager.repository.UsuarioRepository;
import com.ronydex.taskmanager.repository.TareaRepository;
import com.ronydex.taskmanager.dto.UsuarioRegistroDTO;
import com.ronydex.taskmanager.dto.UsuarioResponseDTO;
import com.ronydex.taskmanager.dto.TareaRegistroDTO;
import com.ronydex.taskmanager.dto.TareaResponseDTO;

import org.springframework.stereotype.Service;

@Service
public class TareaService {

	private final TareaRepository tareaRepo;

	public TareaService (TareaRepository tareaRepo){
		this.tareaRepo = tareaRepo;
	}


	public TareaResponseDTO crearTarea(TareaRegistroDTO tareaRegDTO, UsuarioRegistroDTO userRegDTO){
		Tarea tarea = new Tarea();
		tarea.setTituloTarea(tareaRegDTO.getTituloTarea());
		tarea.setCreadoPorId(userRegDTO.getCreadoPorId());
		Tarea tareaGuardada = tareaRepo.save(tarea);
		TareaResponseDTO respuestaTar = new TareaResponseDTO();
		respuestaTar.setTituloTarea(tareaGuardada.getTituloTarea());
		respuestaTar.setCreadoPorId(tareaGuardada.getCreadoPorId());
		return respuestaTar;
	}
}
