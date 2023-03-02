package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Entity.Publicacion;

@Repository("PublicacionDaoJPA")
public class PublicacionDaoImp implements IPublicacionDao{
    
    @PersistenceContext
    private EntityManager em;

    //Listar
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> findAll() {
        return em.createQuery("from Publicacion").getResultList();
    }


    //Guardar
    @Override
    @Transactional
    public void save(Publicacion publicacion) {
        
        if (publicacion.getId() != null && publicacion.getId() > 0) {
            em.merge(publicacion);
        } else {
            em.persist(publicacion);
        }
        
    }

    //Encontrar usuario
    @Override
    @Transactional(readOnly = true)
    public Publicacion findOne(Long Id) {
        return em.find(Publicacion.class, Id);
    }


    //Eliminar
    @Override
    public void delete(Long id) {
        Publicacion publicacion = findOne(id);
        em.remove(publicacion);
        //em.remove(findOne(id));
    }


}
