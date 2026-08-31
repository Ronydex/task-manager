package com.ronydex.taskmanager.dto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponseDTO{
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime horaDelError;

	private String estadoHttp;

	private String mensajeDeError;

	private String direccionURLFalla;

	private Map<String, String>  mapeaErroresValidacion = new HashMap<>();

	public ErrorResponseDTO() {
	}

	public ErrorResponseDTO(LocalDateTime horaDelError, String estadoHttp, String mensajeDelError, String direccionURLFalla, Map<String,String>  mapeaErroresValidacion ){
	this.horaDelError = horaDelError;
	this.estadoHttp = estadoHttp;
	this.mensajeDeError = mensajeDeError;
	this.direccionURLFalla = direccionURLFalla;
	this.mapeaErroresValidacion = mapeaErroresValidacion;
	}

	public ErrorResponseDTO(LocalDateTime horaDelError, String estadoHttp, String mensajeDeError, String direccionURLFalla){
	this.horaDelError = horaDelError;
	this.estadoHttp = estadoHttp;
	this.mensajeDeError = mensajeDeError;
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

	public Map<String, String>  getMapeaErroresValidacion(){return mapeaErroresValidacion;}
	public void setMapeaErroresValidacion(Map<String, String>  mapeaErroresValidacion){this.mapeaErroresValidacion = mapeaErroresValidacion;}
}
