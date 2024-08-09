package com.maids.librarymgmtsys.service;

import com.maids.librarymgmtsys.model.Book;

import java.util.List;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-6:53 AM
 */
public interface BookService {

    List<Book> getAll();
    Book getById(Integer id);
    Book save(Book book);
    Book update(Integer id, Book book);
    void delete(Integer id);
}
