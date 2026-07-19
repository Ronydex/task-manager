package com.ronydex.taskmanager.repository;

import com.ronydex.taskmanager.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{
    @Query("SELECT g FROM Tarea g WHERE g.asignadoA.email = :email")
    List<Tarea> buscarTareaPorEmailUsuario(@Param("email") String email);
}
