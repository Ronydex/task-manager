package com.ronydex.taskmanager.dto;

import com.ronydex.taskmanager.model.EstadosTarea;
import com.ronydex.taskmanager.model.Usuario;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

public class TareaRegistroDTO{
	
	@NotBlank(message="El titulo no puede estar en blanco")
	private String tituloTarea;

	@NotBlank(message="Necesitamos detalles de la tarea asignada")
	private String descripcionTarea;
	
	@NotNull(message="La tarea debe tener un estatus")
	private EstadosTarea estadoActTar;

	@NotNull(message="La tarea debe ser creada por un Usuario")
	private Long creadoPorId;

	@NotNull(message="La tarea debe ser asignada a un Usuario")
	private Long  asignadoAId;

	//GETTTERS Y SETTERS

	public String getTituloTarea(){return tituloTarea;}
	public void setTituloTarea(String tituloTarea){this.tituloTarea = tituloTarea;}
	
	public String getDescripcionTarea(){return descripcionTarea; }
	public void setDescripcionTarea(String descripcionTarea){this.descripcionTarea = descripcionTarea;}

	public EstadosTarea getEstadoActTar(){return estadoActTar;}
	public void setEstadoActTar(EstadosTarea estadoActTar){this.estadoActTar = estadoActTar;}

	public Long  getCreadoPorId(){return creadoPorId;}
	public void setCreadoPorId(Long creadoPorId){this.creadoPorId = creadoPorId;}

	public Long getAsignadoAId(){return asignadoAId;}
	public void setAsignadoAId(Long asignadoAId){this.asignadoAId = asignadoAId;}
} 
