package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Entity.Respuesta;

@Repository("RespuestaDaoJPA")
public class RespuestaDaoImp implements IRespuestaDao{
    
    @PersistenceContext
    private EntityManager em;

    //Listar
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Respuesta> findAll() {
        return em.createQuery("from Respuesta").getResultList();
    }


    //Guardar
    @Override
    @Transactional
    public void save(Respuesta respuesta) {
        
        if (respuesta.getId() != null && respuesta.getId() > 0) {
            em.merge(respuesta);
        } else {
            em.persist(respuesta);
        }
        
    }

    //Encontrar Respuesta
    @Override
    @Transactional(readOnly = true)
    public Respuesta findOne(Long Id) {
        return em.find(Respuesta.class, Id);
    }


    //Eliminar
    @Override
    public void delete(Long id) {
        Respuesta respuesta = findOne(id);
        em.remove(respuesta);
        //em.remove(findOne(id));
    }


}
