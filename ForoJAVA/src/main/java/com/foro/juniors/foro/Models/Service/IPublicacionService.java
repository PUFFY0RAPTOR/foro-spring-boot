package com.foro.juniors.foro.Models.Service;

import java.util.List;

import com.foro.juniors.foro.Models.Entity.Publicacion;

public interface IPublicacionService {
    public List<Publicacion> findAll();

    public void save(Publicacion publicacion);

    public Publicacion findOne(Long Id);

    public void delete(Long id);
}
