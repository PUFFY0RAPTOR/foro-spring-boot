package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import com.foro.juniors.foro.Models.Entity.Comentario;

public interface IComentarioDao {
    public List<Comentario> findAll();

    public void save(Comentario comentario);

    public Comentario findOne(Long Id);

    public void delete(Long id);

}
