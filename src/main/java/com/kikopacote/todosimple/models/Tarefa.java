package com.kikopacote.todosimple.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Tarefa.TABLE_NAME)
@Setter
@Getter
public class Tarefa {
    public static final String TABLE_NAME= "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false, updatable = false)
     private Usuario usuario;

    @Column(name = "descricao", length = 225, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String descricao;


    public Tarefa() {
    }

    
    public Tarefa(Long id, Usuario usuario, String descricao) {
        this.id = id;
        this.usuario = usuario;
        this.descricao = descricao;
    }




   @Override
   public boolean equals(Object obj){
    if (obj== this) 
        return true;

    if (obj == null) 
        return false;
             
    if (!(obj instanceof Tarefa)) 
        return false;

    Tarefa other = (Tarefa) obj;
    if (this.id == null)
        if (other.id!= null)
        return false;

    else if(!this.id.equals(other.id))
         return false;
    return Objects.equals(this.id,other.id) && Objects.equals(this.usuario,other.usuario)
     && Objects.equals(this.descricao,other.descricao);

   

   }
   @Override
   public int hashCode(){
      final int prime = 31;
      int result = 1;
      result = prime * result +((this.id == null)? 0: this.id.hashCode());
      return result;
   }



}
