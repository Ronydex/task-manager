package com.ronydex.taskmanager.dto;

import com.ronydex.taskmanager.model.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponseDTO{
   
    private Long idUsuario;	

    private String nombre;
   
    private String email;

    private Roles rolAsignado;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaRegistro;

    public UsuarioResponseDTO(){
    }

    public UsuarioResponseDTO(Long idUsuario, String nombre, String email, Roles rolAsignado, LocalDateTime fechaRegistro){
    	this.idUsuario = idUsuario;
	this.nombre = nombre;
	this.email = email;
	this.rolAsignado = rolAsignado;
	this.fechaRegistro = fechaRegistro;
    }
    //Getters y Setters

    public Long getIdUsuario(){return idUsuario; }
    public void setIdUsuario(Long idUsuario){this.idUsuario = idUsuario;}

    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getEmail(){ return email; }
    public void setEmail(String email){this.email = email;}

    public Roles getRolAsignado(){return rolAsignado;}
    public void setRolAsignado(Roles rolAsignado){this.rolAsignado = rolAsignado;}

    public LocalDateTime getFechaRegistro(){return fechaRegistro;}
    public void setFechaRegistro(LocalDateTime fechaRegistro){this.fechaRegistro = fechaRegistro;}
}
