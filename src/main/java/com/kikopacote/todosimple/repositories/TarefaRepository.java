package com.kikopacote.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kikopacote.todosimple.models.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{


    

    
}
