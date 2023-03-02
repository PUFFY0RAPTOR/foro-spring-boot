package com.foro.juniors.foro.Models.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Dao.IUsuarioDao;
import com.foro.juniors.foro.Models.Entity.Usuario;

@Service
public class UsuarioServiceImp implements IUsuarioService{

    @Autowired
    private IUsuarioDao usuarioDao;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }


    @Override
    @Transactional
    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
        
    }


    @Override
    @Transactional(readOnly = true)
    public Usuario findOne(Long Id) {
        return usuarioDao.findOne(Id);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        usuarioDao.delete(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllLogin(String usuario, String password) {
        return usuarioDao.findAllLogin(usuario, password);
    }

    @Override
    public List<Usuario> findUsuarioLogin(String usuario, String password) {
        return usuarioDao.findUsuarioLogin(usuario, password);
    }

}
