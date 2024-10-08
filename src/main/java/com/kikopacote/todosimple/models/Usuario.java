package com.kikopacote.todosimple.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


import java.util.Objects;

@Entity
@Table (name ="usuario")
public class Usuario {
    public interface CriacaoDeUsuario{

    }
    public interface AtualizacaoDeSenha{

    }
    public interface AtualizarUsuario {
    
        
    }

    



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "nome_usuario", length = 100, nullable = false, unique = true)
    @NotNull(groups = CriacaoDeUsuario.class)
    @NotEmpty(groups = CriacaoDeUsuario.class)
    @Size(groups =CriacaoDeUsuario.class, min = 2, max = 100)   
    private String nome;

    
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "senha_usuario", length = 60, nullable = false)
    @NotNull(groups ={CriacaoDeUsuario.class, AtualizarUsuario.class} )
    @NotEmpty(groups ={CriacaoDeUsuario.class, AtualizarUsuario.class})
    @Size(groups = {CriacaoDeUsuario.class, AtualizarUsuario.class}, min = 8, max = 60)    
    private String senha;

    @OneToMany(mappedBy ="usuario")
    private List<Tarefa> tarefas = new ArrayList<Tarefa>();

  

   public Usuario(){

   }

   public Usuario( Long id, String nome, String senha){
    this.id = id;    
    this.nome = nome;
    this.senha = senha;


   }

   @Override
   public boolean equals(Object obj){
    if (obj== this) 
        return true;

    if (obj == null) 
        return false;
             
    if (!(obj instanceof Usuario)) 
        return false;

    Usuario other = (Usuario) obj;
    if (this.id == null)
        if (other.id!= null)
        return false;

    else if(!this.id.equals(other.id))
         return false;
    return Objects.equals(this.id,other.id) && Objects.equals(this.nome,other.nome)
     && Objects.equals(this.senha,other.senha);

   

   }
   @Override
   public int hashCode(){
      final int prime = 31;
      int result = 1;
      result = prime * result +((this.id == null)? 0: this.id.hashCode());
      return result;
   }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Tarefa> getTarefas() {
        return this.tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }



    
}
