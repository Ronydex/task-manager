package com.ronydex.taskmanager.security;

import com.ronydex.taskmanager.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;

public class UsuarioPrincipal implements UserDetails{

	private final Usuario usuario;

	public  UsuarioPrincipal(Usuario usuario){
		this.usuario = usuario;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(){
	return List.of(usuario.getRolAsignado());
	}
	public String getUsername(){
	return usuario.getEmail();
	}
	public String getPassword(){
	return usuario.getPassword();
	}
	
	@Override
	public boolean isAccountNonExpired(){
		return true;
	}
	@Override
	public boolean isAccountNonLocked(){
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired(){
		return true;
	}
	@Override
	public boolean isEnabled(){
		return true;
	}
}
