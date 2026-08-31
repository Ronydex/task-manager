package com.ronydex.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public class UsuarioLoginDTO{
    
       	@NotBlank(message= "El correo es obligatorio para iniciar sesión")	
	@Email(message= "Debe proporcionar un formato de correo válido")
	private String email;

	@NotBlank(message ="La contraseña es obligatoria para iniciar sesión")
	private String password;

	public UsuarioLoginDTO(){
	}
	public UsuarioLoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}

	public String getPassword(){return password;}
	public void setPassword(String password){this.password = password;}
}
