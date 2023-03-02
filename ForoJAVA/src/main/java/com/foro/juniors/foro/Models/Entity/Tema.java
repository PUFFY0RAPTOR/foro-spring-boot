package com.foro.juniors.foro.Models.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "temas")
public class Tema implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    
    @NotEmpty
    private String NombreTema;

    @NotEmpty
    private String DescTema;

    @OneToMany(mappedBy = "tema")
    private List<Publicacion> publicacion;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombreTema() {
        return NombreTema;
    }

    public void setNombreTema(String nombreTema) {
        NombreTema = nombreTema;
    }

    public String getDescTema() {
        return DescTema;
    }

    public void setDescTema(String descTema) {
        DescTema = descTema;
    }

}
