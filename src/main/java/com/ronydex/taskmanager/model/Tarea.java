package com.ronydex.taskmanager.model;

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
    private EstadosTarea estadoActTar;

    @ManyToOne
    @JoinColumn(name = "usuarioCrea")
    private Usuario creadoPorId;

    @ManyToOne
    @JoinColumn(name = "usuarioAsig")
    private Usuario asignadoAId;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_solucion", updatable = true)
    private LocalDateTime fechaSolucion;

    public Tarea(String tituloTarea, String descripcionTarea, EstadosTarea estadoActTar, Usuario creadoPorId, Usuario asignadoAId, LocalDateTime fechaCreacion, LocalDateTime fechaSolucion){
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

    public EstadosTarea getEstadoActTar() { return estadoActTar; }
    public void setEstadoActTar(EstadosTarea estadoActTar) {this.estadoActTar = estadoActTar; }

    public Usuario getCreadoPorId() { return creadoPorId; }
    public void setCreadoPorId(Usuario creadoPorId) { this.creadoPorId = creadoPorId; }

    public Usuario getAsignadoAId() { return asignadoAId; }
    public void setAsignadoAId(Usuario asignadoAId) { this.asignadoAId = asignadoAId;}    

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaSolucion() {return fechaSolucion; }
    public void setFechaSolucion(LocalDateTime fechaSolucion) { this.fechaSolucion = fechaSolucion; }

    }

