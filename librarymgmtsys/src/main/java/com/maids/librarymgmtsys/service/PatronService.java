package com.maids.librarymgmtsys.service;

import com.maids.librarymgmtsys.model.Patron;

import java.util.List;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-6:53 AM
 */
public interface PatronService {

    List<Patron> getAll();
    Patron getById(Integer id);
    Patron save(Patron patron);
    Patron update(Integer id, Patron patron);
    void delete(Integer id);
}
