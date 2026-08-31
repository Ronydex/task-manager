package com.ronydex.taskmanager.dto;

import com.ronydex.taskmanager.model.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRegistroDTO{
    
    @NotBlank(message="El nombre del usuario no debe estar vacío")
    @Size(max = 50, message= "El nombre no debe ser mas de 50 caracteres")
    private String nombre;

    @NotBlank(message="El correo  del usuario no debe estar vacio")
    @Email(message = "Debe proporcionar un formato de correo valido")    
    private String email;

    @NotBlank(message="Todo usuario tiene una contraseña")
    @Size(min = 6, message= "La contraseña no debe ser menor a 6 caracteres")
    private String password;

    @NotNull(message="El usuario debe tener un rol")
    private Roles rolAsignado;

    public UsuarioRegistroDTO(){
    }

    public UsuarioRegistroDTO(String nombre, String email, String password, Roles rolAsignado){
    	this.nombre = nombre;
	this.email = email;
	this.password = password;
	this.rolAsignado = rolAsignado;
    }

    //Getters y Setters

    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getEmail(){ return email; }
    public void setEmail(String email){this.email = email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public Roles getRolAsignado(){return rolAsignado;}
    public void setRolAsignado(Roles rolAsignado){this.rolAsignado = rolAsignado;}
}
