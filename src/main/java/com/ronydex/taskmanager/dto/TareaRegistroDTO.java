package com.ronydex.taskmanager.dto;

import com.ronydex.taskmanager.model.EstadosTarea;
import jakarta.validation.constraints.*;

public class TareaRegistroDTO{
	
	@NotBlank(message="El titulo no puede estar en blanco")
	private String tituloTarea;

	@NotBlank(message="Necesitamos detalles de la tarea asignada")
	private String descripcionTarea;
	
	@NotNull(message="La tarea debe tener un estatus")
	private EstadosTarea estadoActTar;

	@NotNull(message="La tarea debe ser creada por un Usuario")
	private String creadoPor;

	@NotNull(message="La tarea debe ser asignada a un Usuario")
	private String asignadoA;

	//GETTTERS Y SETTERS

	public String getTituloTarea(){return tituloTarea;}
	public void setTituloTarea(String tituloTarea){this.tituloTarea = tituloTarea;}
	
	public String getDescripcionTarea(){return descripcionTarea; }
	public void setDescripcionTarea(String descripcionTarea){this.descripcionTarea = descripcionTarea;}

	public EstadosTarea getEstadoActTar(){return estadoActTar;}
	public void setEstadoActTar(EstadosTarea estadoActTar){this.estadoActTar = estadoActTar;}

	public String getCreadoPor(){return creadoPor;}
	public void setCreadoPor(String creadoPor){this.creadoPor = creadoPor;}

	public String getAsignadoA(){return asignadoA;}
	public void setAsignadoA(String asignadoA){this.asignadoA = asignadoA;}
} 
