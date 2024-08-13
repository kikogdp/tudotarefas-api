package com.lucasangelo.todosimple.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.Usuario;
import com.lucasangelo.todosimple.repositories.TarefaRepository;
import com.lucasangelo.todosimple.repositories.UsuarioRepository;

@Service
public class UsuarioService  {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public Usuario buscarPeloId(Long id){
       Optional<Usuario> usuario =this.usuarioRepository.findById(id);
        return usuario.orElseThrow(()-> new RuntimeException(
            "Usuário não encontrado! Id: " + id +", Tipo:"
        +Usuario.class.getName()));

    }

    @Transactional
    public Usuario CriarUsuario(Usuario usuario){
        usuario.setId(null);
        usuario = this.usuarioRepository.save(usuario);
        this.tarefaRepository.saveAll((usuario.getTarefas()));
        return usuario;

    }

    @Transactional
    public Usuario atualizar(Usuario usuario){
        Usuario usuarioAtualizar = buscarPeloId(usuario.getId());
        usuarioAtualizar.setSenha(usuario.getSenha());
        return this.usuarioRepository.save(usuario);



    }


    public void delete( Long id){
        buscarPeloId(id);
      try{
        this.usuarioRepository.deleteById(id);
      }catch(Exception e){
        throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas");

      }  
    }
 



    
}
