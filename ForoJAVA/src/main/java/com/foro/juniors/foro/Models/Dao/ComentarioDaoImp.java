package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Entity.Comentario;

@Repository("ComentarioDaoJPA")
public class ComentarioDaoImp implements IComentarioDao{
    
    @PersistenceContext
    private EntityManager em;

    //Listar
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findAll() {
        return em.createQuery("from Comentario").getResultList();
    }


    //Guardar
    @Override
    @Transactional
    public void save(Comentario coment) {
        
        if (coment.getId() != null && coment.getId() > 0) {
            em.merge(coment);
        } else {
            em.persist(coment);
        }
        
    }

    //Encontrar
    @Override
    @Transactional(readOnly = true)
    public Comentario findOne(Long Id) {
        return em.find(Comentario.class, Id);
    }


    //Eliminar
    @Override
    public void delete(Long id) {
        Comentario coment = findOne(id);
        em.remove(coment);
        //em.remove(findOne(id));
    }


}
