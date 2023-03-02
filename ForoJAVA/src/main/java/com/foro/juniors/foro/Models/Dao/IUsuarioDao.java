package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import com.foro.juniors.foro.Models.Entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUsuarioDao {
    public List<Usuario> findAll();

    public void save(Usuario usuario);

    public Usuario findOne(Long Id);

    public void delete(Long id);

    public List<Usuario> findAllLogin(String usuario, String password);

    public List<Usuario> findUsuarioLogin(String usuario, String password);

}
