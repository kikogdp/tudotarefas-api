package com.kikopacote.todosimple.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kikopacote.todosimple.models.Tarefa;
import com.kikopacote.todosimple.models.Usuario;
import com.kikopacote.todosimple.repositories.TarefaRepository;

@Service
public class TarefaService {

     

   @Autowired
   private TarefaRepository tarefaRepository;

   @Autowired
   private UsuarioService usuarioService;

 
    public Tarefa buscarPorId(Long id){
        Optional<Tarefa> tarefa = this.tarefaRepository.findById(id);
        return tarefa.orElseThrow(()-> new RuntimeException("Usuário não encontrado! id" + id +",Tipo: " + Usuario.class.getName()
        )); 

        }

    public List<Tarefa> findByUsuarioId(Long id){
        List<Tarefa> tarefas = this.tarefaRepository.findByUsuario_id(id);
        return tarefas;

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
