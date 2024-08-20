package com.kikopacote.todosimple.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kikopacote.todosimple.models.Tarefa;
import com.kikopacote.todosimple.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
@Validated
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id){
        Tarefa tarefa = this.tarefaService.buscarPorId(id);
        return ResponseEntity.ok(tarefa);

    }
    @PostMapping
    @Validated
    public ResponseEntity<Void> criarTarefa(@Validated @RequestBody Tarefa tarefa){
        this.tarefaService.criarTarefa(tarefa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    

    }
 
    public ResponseEntity<Void> atualizar(@Validated @RequestBody Tarefa tarefa, @PathVariable Long id){
        tarefa.setId(id);
        this.tarefaService.atualizar(tarefa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        this.tarefaService.remover(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Tarefa>> findByUsuarioId(@PathVariable Long usuarioId){
        List<Tarefa> tarefas = this.tarefaService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok().body(tarefas);


        
    }

    






    
}
