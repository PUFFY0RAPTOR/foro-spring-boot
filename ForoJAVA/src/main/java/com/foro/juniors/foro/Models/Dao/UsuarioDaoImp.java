package com.foro.juniors.foro.Models.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Entity.Usuario;

@Repository("UsuarioDaoJPA")
public class UsuarioDaoImp implements IUsuarioDao{
    
    @PersistenceContext
    private EntityManager em;

    //Listar
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {

        return em.createQuery("from Usuario").getResultList();
    }


    //Guardar
    @Override
    @Transactional
    public void save(Usuario usuario) {
        
        if (usuario.getId() != null && usuario.getId() > 0) {
            em.merge(usuario);
        } else {
            em.persist(usuario);
        }
        
    }

    //Encontrar usuario
    @Override
    @Transactional(readOnly = true)
    public Usuario findOne(Long Id) {

        return em.find(Usuario.class, Id);
    }


    //Eliminar
    @Override
    public void delete(Long id) {
        Usuario usuario = findOne(id);
        em.remove(usuario);
        //em.remove(findOne(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllLogin(String usuario, String password) {

        return em.createQuery("from Usuario WHERE nombre_usuario = '"+usuario+"' AND contrasena = '"+password+"'")
                .getResultList();
    }

    @Override
    public List<Usuario> findUsuarioLogin(String usuario, String password) {
        return em.createNativeQuery("SELECT nombre_usuario, contrasena FROM usuarios WHERE " +
                "nombre_usuario = :usuario AND contrasena = :password").getResultList();
    }

}
