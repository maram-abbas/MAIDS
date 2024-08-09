package com.maids.librarymgmtsys.service.serviceImpl;

import com.maids.librarymgmtsys.dao.PatronDAO;
import com.maids.librarymgmtsys.model.Patron;
import com.maids.librarymgmtsys.service.PatronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronDAO patronDAO;

    @Override
    @Cacheable(value = "patrons")
    public List<Patron> getAll() {
        return patronDAO.findAll();
    }

    @Override
    @Cacheable(value = "patrons", key = "#id")
    public Patron getById(Integer id) {
        try {
            return patronDAO.getReferenceById(id);
        } catch (Exception e) {
            log.error("Cannot find reference.", e);
            return null;
        }
    }

    @Override
    @CachePut(value = "patrons", key = "#patron.id")
    public Patron save(Patron patron) {
        return patronDAO.save(patron);
    }

    @Override
    @CachePut(value = "patrons", key = "#id")
    public Patron update(Integer id, Patron patron) {
        try {
            Patron oldPatron = patronDAO.getReferenceById(id);
            patron.setId(oldPatron.getId());
            return patronDAO.save(patron);
        } catch (Exception e) {
            log.error("Cannot find reference.", e);
            return null;
        }
    }

    @Override
    @CacheEvict(value = "patrons", key = "#id")
    public void delete(Integer id) {
        try {
            Patron patron = patronDAO.getReferenceById(id);
            patronDAO.delete(patron);
        } catch (Exception e) {
            log.error("Cannot find reference.", e);
        }
    }
}
