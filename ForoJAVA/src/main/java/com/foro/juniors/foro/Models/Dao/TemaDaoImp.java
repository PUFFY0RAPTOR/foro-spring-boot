package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Entity.Tema;

@Repository("TemaDaoJPA")
public class TemaDaoImp implements ITemaDao{

    @PersistenceContext
    private EntityManager em;

    //Listar
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Tema> findAll() {
        return em.createQuery("from Tema").getResultList();
    }


    //Guardar
    @Override
    @Transactional
    public void save(Tema tema) {
        
        if (tema.getId() != null && tema.getId() > 0) {
            em.merge(tema);
        } else {
            em.persist(tema);
        }
        
    }

    //Encontrar tema
    @Override
    @Transactional(readOnly = true)
    public Tema findOne(Long Id) {
        return em.find(Tema.class, Id);
    }


    //Eliminar tema
    @Override
    public void delete(Long id) {
        Tema tema = findOne(id);
        em.remove(tema);
    }

}
