package com.ronydex.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioLoginDTO{
     
	private String email;

	private String password;

	@NotBlank(message= "El correo es obligatorio para iniciar sesión")
	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}

	@NotBlank(message ="La contraseña es obligatoria para iniciar sesión")
	public String getPassword(){return password;}
	public void setPassword(String password){this.password = password;}
}
