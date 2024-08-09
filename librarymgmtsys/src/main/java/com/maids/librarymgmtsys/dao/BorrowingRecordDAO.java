package com.maids.librarymgmtsys.dao;

import com.maids.librarymgmtsys.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-6:50 AM
 */

@Repository
public interface BorrowingRecordDAO extends JpaRepository<BorrowingRecord, Long> {

    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = :bookId AND br.returnDate IS NULL")
    Optional<BorrowingRecord> findByBookIdAndReturnDateIsNull(Integer bookId);

    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = :bookId AND br.patron.id = :patronId AND br.returnDate IS NULL")
    Optional<BorrowingRecord> findByBookIdAndPatronIdAndReturnDateIsNull(Integer bookId, Integer patronId);
}
