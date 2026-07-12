package com.ronydex.taskmanager.dto;


import com.ronydex.taskmanager.model.Roles;
import jakarta.validation.constraints.*;

public class UsuarioDTO{
    
    @NotBlank(message="El nombre del usuario no debe estar vacío")
    private String nombre;

    @NotBlank(message="El correo  del usuario no debe estar vacio")
    private String email;

    @NotBlank(message="Todo usuario tiene una contraseña")
    private String password;

    @NotNull(message="El usuario debe tener un rol")
    private Roles rolAsignado;

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
