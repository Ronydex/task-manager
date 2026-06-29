package com.ronydex.taskmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "usuario")

public class Usuario{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "fecha_registro",updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Roles rolAsignado;


    //Getters y Setters:

    public Long getIdUsuario(){ return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail(){ return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword(){ return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getFechaRegistro(){ return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }



}
