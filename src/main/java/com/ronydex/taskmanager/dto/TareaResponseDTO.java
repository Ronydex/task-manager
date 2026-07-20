package com.ronydex.taskmanager.dto;

import com.ronydex.taskmanager.model.EstadosTarea;
import java.time.LocalDateTime;

public class TareaResponseDTO{
	
	private Long idTarea;

	private String tituloTarea;
	
	private String descripcionTarea;

	private EstadosTarea estadoActTar;

	private String creadoPorUser;

	private String asignadoAUser;

	private LocalDateTime fechaCreacion;

	private LocalDateTime fechaSolucion;

	private String tiempoResolucion;


	public Long getIdTarea(){return idTarea;}
	public void setIdTarea(Long idTarea){this.idTarea = idTarea;}

	public String getTituloTarea(){return tituloTarea;}
	public void setTituloTarea(String tituloTarea){this.tituloTarea = tituloTarea;}

	public String getDescripcionTarea(){return descripcionTarea;}
	public void setDescripcionTarea(String descripcionTarea){this.descripcionTarea = descripcionTarea;}

	public EstadosTarea getEstadoActTar(){return estadoActTar;}
	public void setEstadoActTar(EstadosTarea estadoActTar){this.estadoActTar = estadoActTar;}

	public String getCreadoPorUser(){return creadoPorUser;}
	public void setCreadoPorUser(String creadoPorUser){this.creadoPorUser = creadoPorUser;}
	
	public String getAsignadoAUser(){return asignadoAUser;}
	public void setAsignadoAUser(String asignadoAUser){this.asignadoAUser = asignadoAUser;}

	public LocalDateTime getFechaCreacion(){return fechaCreacion;}
	public void setFechaCreacion(LocalDateTime fechaCreacion){this.fechaCreacion = fechaCreacion;}

	public LocalDateTime getFechaSolucion(){return fechaSolucion;}
	public void setFechaSolucion(LocalDateTime fechaSolucion){this.fechaSolucion = fechaSolucion;}
	
	public String getTiempoResolucion(){return tiempoResolucion;}
	public void setTiempoResolucion(String  tiempoResolucion){this.tiempoResolucion = tiempoResolucion;}

	
} 
