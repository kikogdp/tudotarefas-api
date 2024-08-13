package com.kikopacote.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kikopacote.todosimple.models.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    
}
