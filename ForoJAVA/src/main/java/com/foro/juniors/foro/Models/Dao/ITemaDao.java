package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import com.foro.juniors.foro.Models.Entity.Tema;

public interface ITemaDao {
    public List<Tema> findAll();

    public void save(Tema tema);

    public Tema findOne(Long Id);

    public void delete(Long id);
}
