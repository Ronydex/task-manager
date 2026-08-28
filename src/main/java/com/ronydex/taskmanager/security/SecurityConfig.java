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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.Customizer;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(List.of("*"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Cache-Control"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	@Bean
	public  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.cors(Customizer.withDefaults())
			//Paso 1.Apagar CSRF porque se usaran tokens JWT(stateless)
			.csrf(csrf -> csrf.disable())
			//Paso 2.Definir que NO guarde sesiones en memoria del servidor
			.sessionManagement(session ->
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					)
			//Paso 3.Reglas de acceso a las URLs
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
				//Endpoints públicos (login, registro)
				.requestMatchers("/api/auth/**").permitAll()
				
				// --- ACCIONES EXCLUSIVAS DEL ADMINISTRADOR ---
				.requestMatchers(org.springframework.http.HttpMethod.POST, "/api/tareas").hasRole("ADMINISTRATOR")
				.requestMatchers(org-springframework.http.HttpMethod.DELETE, "/api/usuarios/developers/**").hasRole("ADMINISTRATOR")

				// --- ACCIONES EXCLUSIVAS DEL PROJECT MANAGER ---
				.requestMatchers(org.springframework.http.HttpMethod.POST, "/api/tareas").hasAnyRole("ADMINISTRATOR", "PROJECT_MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/tareas/{id}/reasignar").hasAnyRole("ADMINISTRATOR", "PROJECT_MANAGER")
				.requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/tareas/{id}/reabrir").hasAnyRole("ADMINISTRAOTR", "PROJECT_MANAGER")

				// --- ACCIONES EXCLUSIVAS DEL DEVELOPER ---
				.requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/tareas/{id}/cerrar").hasAnyRole("ADMINISTRATOR", "PROJECT_MANAGER", "DEVELOPER")
				// ---ACCIONES EXCLUSIVAS DEL CLIENTE ---
				.requestMatchers(org.springframework.http.HttpMethod.POST, "/api/tareas/{id}/solicitar-cierre").hasRole("CLIENT")

				// ---LECTURA GENERAL DE TAREAS ---
				.requestMatchers(org.springframework.http.HttpMethod.GET, "/api/tareas/**").hasAnyRole("ADMINISTRATOR", "PROJECT_MANAGER", "DEVELOPER", "CLIENT")
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
