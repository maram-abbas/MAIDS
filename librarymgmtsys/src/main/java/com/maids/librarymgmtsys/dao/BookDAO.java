package com.maids.librarymgmtsys.dao;

import com.maids.librarymgmtsys.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-6:50 AM
 */

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {
}
