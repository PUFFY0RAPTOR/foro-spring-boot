package com.foro.juniors.foro.Models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Dao.IRespuestaDao;
import com.foro.juniors.foro.Models.Entity.Respuesta;

@Service
public class RespuestaServiceImp implements IRespuestaService{

    @Autowired
    private IRespuestaDao respuestaDao;


    @Override
    @Transactional(readOnly = true)
    public List<Respuesta> findAll() {
        return respuestaDao.findAll();
    }


    @Override
    @Transactional
    public void save(Respuesta respuesta) {
        respuestaDao.save(respuesta);
        
    }


    @Override
    @Transactional(readOnly = true)
    public Respuesta findOne(Long Id) {
        return respuestaDao.findOne(Id);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        respuestaDao.delete(id);
    }
    
}
