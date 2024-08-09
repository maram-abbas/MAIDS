package com.maids.librarymgmtsys.service;

import com.maids.librarymgmtsys.model.Book;
import com.maids.librarymgmtsys.model.BorrowingRecord;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-6:53 AM
 */
public interface BorrowingRecordService {

    BorrowingRecord borrowBook(LocalDateTime borrowingDate, Integer bookId, Integer patronId) throws Exception;
    BorrowingRecord returnBook(LocalDateTime returnDate, Integer bookId, Integer patronId) throws Exception;
}
