package com.foro.juniors.foro.Models.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foro.juniors.foro.Models.Dao.ITemaDao;
import com.foro.juniors.foro.Models.Entity.Tema;

@Service
public class TemaServiceImp implements ITemaService{

    @Autowired
    private ITemaDao temaDao;


    @Override
    @Transactional(readOnly = true)
    public List<Tema> findAll() {
        return temaDao.findAll();
    }


    @Override
    @Transactional
    public void save(Tema tema) {
        temaDao.save(tema);
        
    }


    @Override
    @Transactional(readOnly = true)
    public Tema findOne(Long Id) {
        return temaDao.findOne(Id);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        temaDao.delete(id);
    }
    
}
