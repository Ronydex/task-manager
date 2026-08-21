package com.ronydex.taskmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			//Paso 1.Apagar CSRF porque se usaran tokens JWT(stateless)
			.csrf(csrf -> csrf.disable())
			//Paso 2.Definir que NO guarde sesiones en memoria del servidor
			.sessionManagement(session ->
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					)
			//Paso 3.Reglas de acceso a las URLs
			.authorizeHttpRequests(auth -> auth
				//Endpoints públicos (login, registro)
				.requestMatchers("/api/auth/**").permitAll()
				//Cualquier otra URL exige estar autenticado
				.anyRequest().authenticated()
				)
			//Paso 4.Poner tu filtro JWT antes del filtro por defecto de Spring
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			//Paso 5.Construir y devolver la configuración armada
			return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}
