package com.ronydex.taskmanager.dto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

public class ErrorResponseDTO{
	
	private LocalDateTime horaDelError;

	private String estadoHttp;

	private String mensajeDeError;

	private String direccionURLFalla;

	private Map<String, String>  mapaErroresValidacion = new HashMap<>();

	public ErrorResponseDTO(LocalDateTime horaDelError, String estadoHttp, String mensajeDelError, String direccionURLFalla, Map<String,String>  mapaErroresValidacion ){
	this.horaDelError = horaDelError;
	this.estadoHttp = estadoHttp;
	this.mensajeDelError = mensajeDelError;
	this.direccionURLFalla = direccionURLFalla;
	this.mapaErroresValidacion = mapaErroresValidacion;
	}

	public ErrorResponseDTO(LocalDateTime horaDelError, String estadoHttp, String mensajeDelError, String direccionURLFalla){
	this.horaDelError = horaDelError;
	this.estadoHttp = estadoHttp;
	this.mensajeDelError = mensajeDelError;
	this.direccionURLFalla = direccionURLFalla;
	}
	//GETTERS Y SETTERS
	
	public LocalDateTime getHoraDelError(){return horaDelError;}
	public void setHoraDelError(LocalDateTime horaDelError){this.horaDelError = horaDelError;}

	public String getEstadoHttp(){return estadoHttp;}
	public void setEstadoHttp(String estadoHttp){this.estadoHttp = estadoHttp;}

	public String getMensajeDeError(){return mensajeDeError;}
	public void setMensajeDeError(String mensajeDeError){this.mensajeDeError = mensajeDeError;}

	public String getDireccionURLFalla(){return direccionURLFalla;}
	public void setDireccionURLFalla(String direccionURLFalla){this.direccionURLFalla = direccionURLFalla;}

	public Map<String, String>  getMapeaErroresValidacion(){return mapaErroresValidacion;}
	public void setMapeaErroresValidacion(Map<String, String>  mapeaErroresValidacion){this.mapeaErroresValidacion = mapeaErroresValidacion;}
}
