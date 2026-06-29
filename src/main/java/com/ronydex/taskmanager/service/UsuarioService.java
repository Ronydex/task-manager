package com.ronydex.taskmanager.service;

import com.ronydex.taskmanager.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo){
        this.usuarioRepo = usuarioRepo;
    }

    public void registrarUsuario(Usuario usuario){
    
    }
}
