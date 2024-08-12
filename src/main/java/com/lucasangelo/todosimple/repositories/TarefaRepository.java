package com.lucasangelo.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    

    

    
}
