package com.foro.juniors.foro.Models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Dao.IPublicacionDao;
import com.foro.juniors.foro.Models.Entity.Publicacion;

@Service
public class PublicacionServiceImp implements IPublicacionService{

    @Autowired
    private IPublicacionDao publicacionDao;


    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> findAll() {
        return publicacionDao.findAll();
    }


    @Override
    @Transactional
    public void save(Publicacion publicacion) {
        publicacionDao.save(publicacion);
        
    }


    @Override
    @Transactional(readOnly = true)
    public Publicacion findOne(Long Id) {
        return publicacionDao.findOne(Id);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        publicacionDao.delete(id);
    }
    
}
