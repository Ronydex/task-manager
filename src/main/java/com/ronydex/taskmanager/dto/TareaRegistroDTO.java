package com.ronydex.taskmanager.dto;

import com.ronydex.taskmanager.model.EstadosTarea;
import jakarta.validation.constraints.*;

public class TareaRegistroDTO{
	
	@NotBlank(message="El titulo no puede estar en blanco")
	@Size(max = 100, message = "El titulo no debe tener mas de 100 caracteres")
	private String tituloTarea;

	@NotBlank(message="Necesitamos detalles de la tarea asignada")
	private String descripcionTarea;
	
	@NotNull(message="La tarea debe tener un estatus")
	private EstadosTarea estadoActTar;

	@NotNull(message="La tarea debe ser creada por un Usuario")
	private Long creadoPor;

	@NotNull(message="La tarea debe ser asignada a un Usuario")
	private Long asignadoA;

	public TareaRegistroDTO(){
	}

	public TareaRegistroDTO (String tituloTarea, String descripcionTarea, EstadosTarea estadoActTar, Long creadoPor, Long asignadoA ){
		this.tituloTarea = tituloTarea;
		this.descripcionTarea = descripcionTarea;
		this.estadoActTar = estadoActTar;
		this.creadoPor = creadoPor;
		this.asignadoA = asignadoA;
	}

	//GETTTERS Y SETTERS

	public String getTituloTarea(){return tituloTarea;}
	public void setTituloTarea(String tituloTarea){this.tituloTarea = tituloTarea;}
	
	public String getDescripcionTarea(){return descripcionTarea; }
	public void setDescripcionTarea(String descripcionTarea){this.descripcionTarea = descripcionTarea;}

	public EstadosTarea getEstadoActTar(){return estadoActTar;}
	public void setEstadoActTar(EstadosTarea estadoActTar){this.estadoActTar = estadoActTar;}

	public Long getCreadoPor(){return creadoPor;}
	public void setCreadoPor(Long creadoPor){this.creadoPor = creadoPor;}

	public Long getAsignadoA(){return asignadoA;}
	public void setAsignadoA(Long asignadoA){this.asignadoA = asignadoA;}
} 
