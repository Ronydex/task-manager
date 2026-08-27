package com.ronydex.taskmanager.model;	

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;

public enum Roles implements GrantedAuthority{
    ROL_PM("PROJECT_MANAGER"),
    ROL_DEV("DEVELOPER"),
    ADMIN("ADMINISTRATOR"),
    CLIENTE("CLIENT");

    private final String value;

    Roles(String value) {
    	this.value = value;
    }
    
    //Devolver el nombre limpio(ejemplo: "ADMIN")
    public String getValue(){
    	return value;
    }
    
    //Devolver el formato estándar de Spring Security (ejemplo:"ROLE_ADMIN")
    public String getAuthority(){
    	return "ROLE_" + value;
    }


    public static Roles fromString(String nombreRol) {
    	return Arrays.stream(Roles.values())
		.filter(role -> role.value.equalsIgnoreCase(nombreRol) || role.getAuthority().equalsIgnoreCase(nombreRol))
		.findFirst()
		.orElseThrow(() -> new IllegalArgumentException("Rol no reconocido en el sistema " + nombreRol));
    }
}
