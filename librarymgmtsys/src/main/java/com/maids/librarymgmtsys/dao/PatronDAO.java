package com.maids.librarymgmtsys.dao;

import com.maids.librarymgmtsys.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-6:50 AM
 */

@Repository
public interface PatronDAO extends JpaRepository<Patron, Integer> {
}
