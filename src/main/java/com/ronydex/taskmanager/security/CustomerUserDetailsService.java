package com.ronydex.taskmanager.security;

import com.ronydex.taskmanager.model.Usuario;
import com.ronydex.taskmanager.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomerUserDetailsService implements UserDetailsService {

	private final UsuarioRepository usuarioRepo;

	public CustomerUserDetailsService(UsuarioRepository usuarioRepo){
		this.usuarioRepo = usuarioRepo;
	}

	public UserDetails loadUserByUsername(String username){
		Usuario usuario = usuarioRepo.findByEmail(username)
			.orElseThrow(() ->  new UsernameNotFoundException("Usuario no encontrado por medio del correo,intentar nuevamente"));
		return new UsuarioPrincipal(usuario);
	}
}
