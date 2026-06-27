package com.taskmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarea")

public class Tarea{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;

    @Column(nullable= false, length = 100)
    private String tituloTarea;

    @Column(nullable= false, length = 300)
    private String descripcionTarea;

    @Enumerated(EnumType.STRING)
    private estadosTarea estadoActTar;

    @ManyToOne
    @JoinColumn(name = "usuarioCrea")
    private usuario creadoPorId;

    @ManyToOne
    @JoinColumn(name = "usuarioAsig")
    private usuario asignadoAId;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_solucion", updatable = true)
    private LocalDateTime fechaSolucion;

    public Tarea(String tituloTarea, String descripcionTarea, estadosTarea estadoActTar, usuario creadoPorId, usuario asignadoAId, LocalDateTime fechaCreacion, LocalDateTime fechaSolucion){
        this.tituloTarea = tituloTarea;
        this.descripcionTarea = descripcionTarea;
        this.estadoActTar = estadoActTar;
        this.creadoPorId = creadoPorId;
        this.asignadoAId = asignadoAId;
        this.fechaCreacion = fechaCreacion;
        this.fechaSolucion = fechaSolucion;
    }

    public Tarea(){
        }

    public Long getIdTarea(){ return idTarea; }
    public void setIdTarea(Long idTarea){ this.idTarea = idTarea; }

    public String getTituloTarea(){ return tituloTarea; }
    public void setTituloTarea(String tituloTarea) { this.tituloTarea = tituloTarea; }

    public String getDescripcionTarea() { return descripcionTarea; }
    public void setDescripcionTarea(String descripcionTarea) { this.descripcionTarea = descripcionTarea; }

    public estadosTarea getEstadoActTar() { return estadoActTar; }
    public void setEstadoActTar(estadosTarea estadoActTar) {this.estadoActTar = estadoActTar; }

    public usuario getCreadoPorId() { return creadoPorId; }
    public void setCreadoPorId(usuario creadoPorId) { this.creadoPorId = creadoPorId; }

    public usuario getAsignadoAId() { return asignadoAId; }
    public void setAsignadoAId(usuario asignadoAId) { this.asignadoAId = asignadoAId;}    

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaSolucion() {return fechaSolucion; }
    public void setFechaSolucion(LocalDateTime fechaSolucion) { this.fechaSolucion = fechaSolucion; }

    }

