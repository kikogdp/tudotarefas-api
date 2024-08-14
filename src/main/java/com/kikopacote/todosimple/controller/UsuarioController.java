package com.kikopacote.todosimple.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kikopacote.todosimple.models.Usuario;
import com.kikopacote.todosimple.models.Usuario.AtualizarUsuario;
import com.kikopacote.todosimple.models.Usuario.CriacaoDeUsuario;
import com.kikopacote.todosimple.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        Usuario usuario = this.usuarioService.buscarPorId(id);
        return ResponseEntity.ok().body(usuario);

    }

    @PostMapping
    @Validated(CriacaoDeUsuario.class)
    public ResponseEntity<Void> criarUsuario(@Valid @RequestBody Usuario usuario){
        
        this.usuarioService.criarUsuario(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/id").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();


    }

    @PutMapping("/{id}")
    @Validated(AtualizarUsuario.class)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Usuario usuario, @PathVariable Long id){
        usuario.setId(id);
        this.usuarioService.atualizar(usuario);
        return ResponseEntity.noContent().build();


    }

    public ResponseEntity<Void> remover(@PathVariable Long id){
        this.usuarioService.remover(id);
        return ResponseEntity.noContent().build();

    }






    
}
