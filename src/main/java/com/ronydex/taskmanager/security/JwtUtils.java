package com.ronydex.taskmanager.security;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import javax.crypto.SecretKey
import java.nio.charset.StandardCharsets
@Component

public class JwtUtils{
	@Value("${jwt.secret}") //Va al archivo de application.properties por la variable con ese nombre y copia el valor dentro de la variable jwtSecret.
	private String jwtSecret;
				
	@Value("{$jwt.expiration}") //Va al archivo de application.properties por la variable con ese nombre y copia dentro de la variable jwtExpirationMs.
	private int jwtExpirationMs;

	//Metodo para la creacion del token a traves de un "generador".
	public String generateToken(UsuarioPrincipal usuarioPrincipal){
		//Creacion de una variable de la fecha actual para el token
		Date fechaAhora = new Date();
		//Calculo de la fecha de expiracion,desde el dia de su creacion hasta el de su expiracion a traves de milisegundos(jwtExpirationMs)
		Date fechaExpiracion = new Date(fechaAhora.getTime() + jwtExpirationMs);
		//Apertura la "fabrica" para la creacion del token
		return Jwts.builder()
			//Escribe el nombre de usuario o correo dentro del token
			.setSubject(usuarioPrincipal.getUsername())
			//Imprime la fecha de emisión
			.setIssuedAt(fechaAhora)
			//Imprime la fecha de caducidad
			.setExpiration(fechaExpiracion)
			//Firma el token usando una clave secreta para que nadie pueda falsificarlo
			.signWith(key)
			//Empaqueta toda la información y la convierte en una larga cadena de texto
			.compact();
	}
	//Metodo inverso de generateToken,se encarga de abrir el token para verificar la identidad
	public String getUsernameFromToken(String token){
		//Inicio de la herramienta para leer tokens
		return Jwts.parser()
		//Entrega de la clave secreta para que se pueda verificar su identidad y autenticidad
		    .verifyWith(getSigningKey())
	        //Ensambla el lector con la configuración correspondiente
		    .build()
		//Abre la cadena de texto del token(en dado caso de falsificación,arrojaria un error)
		    .parseSignedClaims(token)
		//Saca la información interna del token    
		    .getPayload()
		//De dicho contenido,extrae especificamente el nombre de usuario y lo devuelve
		    .getSubject();
	}
	//Método para verificar si el token pasa,o no
	public boolean validateToken(String token){
		//Intenta abrir y leer el token usando la misma lógica de getUsernameFromToken
		try{
		Jwts.parser()
		    .verifyWith(getSigningKey())
		    .build()
		    .parseSignedClaims(token);
		    return true;
		}
		//Si el token expiró,alguien lo borró o alteró ,arroja la excepción "e",que es un false
		catch(JwtException | IllegalArgumentException e){
		    return false;
		}
	}

}
