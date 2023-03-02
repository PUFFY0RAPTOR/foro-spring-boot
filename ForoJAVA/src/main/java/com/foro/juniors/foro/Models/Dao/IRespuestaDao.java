package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import com.foro.juniors.foro.Models.Entity.Respuesta;

public interface IRespuestaDao {
    public List<Respuesta> findAll();

    public void save(Respuesta respuesta);

    public Respuesta findOne(Long Id);

    public void delete(Long id);

}
