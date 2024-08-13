package com.kikopacote.todosimple.service;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kikopacote.todosimple.models.Tarefa;
import com.kikopacote.todosimple.models.Usuario;
import com.kikopacote.todosimple.repositories.UsuarioRepository;

@Service
public class UsuarioService {

   @Autowired
   private UsuarioRepository usuarioRepository;

  

 
    public Usuario buscarPorId(Long id){
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(()-> new RuntimeException("Usuário não encontrado! id" + id +",Tipo: " + Usuario.class.getName()
        )); 

        }

    @Transactional  
    public Usuario criarUsuario(Usuario usuario){
        usuario = this.usuarioRepository.save(usuario);
         return usuario;

    }    
    @Transactional
    public Usuario atualizar(Usuario usuario){
        Usuario novoUsuario = buscarPorId(usuario.getId());
        novoUsuario.setSenha(usuario.getSenha());
        return this.usuarioRepository.save(novoUsuario);
    }

    public void remover(Long id){
        buscarPorId(id);

        try{
         this.usuarioRepository.deleteById(id);

        }catch(Exception e){
        throw new RuntimeException("Não é possível excluir pois há entidades relacionadas");

        }

    }




    }

    

