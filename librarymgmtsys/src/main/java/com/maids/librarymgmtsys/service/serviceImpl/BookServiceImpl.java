package com.maids.librarymgmtsys.service.serviceImpl;

import com.maids.librarymgmtsys.dao.BookDAO;
import com.maids.librarymgmtsys.model.Book;
import com.maids.librarymgmtsys.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    @Cacheable(value = "books")
    public List<Book> getAll() {
        return bookDAO.findAll();
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public Book getById(Integer id) {
        try {
            return bookDAO.getReferenceById(id);
        } catch (Exception e) {
            log.error("Cannot find reference.", e);
            return null;
        }
    }

    @Override
    @CachePut(value = "books", key = "#book.id")
    public Book save(Book book) {
        return bookDAO.save(book);
    }

    @Override
    @CachePut(value = "books", key = "#id")
    public Book update(Integer id, Book book) {
        try {
            Book oldBook = bookDAO.getReferenceById(id);
            book.setId(oldBook.getId());
            return bookDAO.save(book);
        } catch (Exception e) {
            log.error("Cannot find reference.", e);
            return null;
        }
    }

    @Override
    @CacheEvict(value = "books", key = "#id")
    public void delete(Integer id) {
        try {
            Book book = bookDAO.getReferenceById(id);
            bookDAO.delete(book);
        } catch (Exception e) {
            log.error("Cannot find reference.", e);
        }
    }
}
