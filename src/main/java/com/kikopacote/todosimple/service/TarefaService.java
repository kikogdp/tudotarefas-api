package com.kikopacote.todosimple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kikopacote.todosimple.models.Tarefa;
import com.kikopacote.todosimple.models.Usuario;
import com.kikopacote.todosimple.repositories.TarefaRepository;
import com.kikopacote.todosimple.repositories.UsuarioRepository;

public class TarefaService {

     

   @Autowired
   private TarefaRepository tarefaRepository;

   private UsuarioService usuarioService;

 
    public Tarefa buscarPorId(Long id){
        Optional<Tarefa> tarefa = this.tarefaRepository.findById(id);
        return tarefa.orElseThrow(()-> new RuntimeException("Usuário não encontrado! id" + id +",Tipo: " + Usuario.class.getName()
        )); 

        }

    @Transactional  
    public Tarefa criarTarefa(Tarefa tarefa){
        Usuario usuario = usuarioService.buscarPorId(tarefa.getUsuario().getId());
        tarefa.setId(null);
        tarefa.setUsuario(usuario);
        tarefa = this.tarefaRepository.save(tarefa);
        return tarefa;

    }    
    @Transactional
    public Tarefa atualizar(Tarefa tarefa){
        Tarefa novaTarefa = buscarPorId(tarefa.getId());
        novaTarefa.setDescricao(tarefa.getDescricao());
        return this.tarefaRepository.save(novaTarefa);
    }

    public void remover(Long id){
        buscarPorId(id);

        try{
         this.tarefaRepository.deleteById(id);

        }catch(Exception e){
        throw new RuntimeException("Não é possível excluir pois há entidades relacionadas");

        }

    }
    
}
