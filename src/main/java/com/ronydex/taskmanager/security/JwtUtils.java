package com.ronydex.taskmanager.security;

@Component

public class JwtUtils{
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("{jwt.expiration}")
	private int jwtExpirationMs;

	public String generateToken(UsuarioPrincipal username){
		Date fechaAhora = new Date();
		Date fechaExpiracion = new Date(fechaAhora.getTime() + jwtExpirationMs);
		Jwts.builder
	}

	public JwtUtil

}
