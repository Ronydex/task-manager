package com.ronydex.taskmanager.dto;

import com.ronydex.taskmanager.model.Roles;
import java.time.LocalDateTime;

public class UsuarioResponseDTO{
   
    private Long idUsuario;	

    private String nombre;
    
    private String email;

    private Roles rolAsignado;

    private LocalDateTime fechaRegistro;
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
