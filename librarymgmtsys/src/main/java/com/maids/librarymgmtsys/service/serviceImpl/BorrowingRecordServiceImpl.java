package com.maids.librarymgmtsys.service.serviceImpl;

import com.maids.librarymgmtsys.dao.BorrowingRecordDAO;
import com.maids.librarymgmtsys.model.Book;
import com.maids.librarymgmtsys.model.BorrowingRecord;
import com.maids.librarymgmtsys.model.Patron;
import com.maids.librarymgmtsys.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author MaramAbbas
 * @Created Aug 08, 2024-2:33 PM
 */
@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    BorrowingRecordDAO borrowingRecordDAO;

    @Override
    @Transactional
    public BorrowingRecord borrowBook(LocalDateTime borrowingDate, Integer bookId, Integer patronId) throws Exception {
        Optional<BorrowingRecord> borrowingRecordBook = borrowingRecordDAO.findByBookIdAndReturnDateIsNull(bookId);
        if(borrowingRecordBook.isPresent()) {
            throw new Exception("book already borrowed.");
        }

        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .book(Book.builder().id(bookId).build())
                .patron(Patron.builder().id(patronId).build())
                .borrowingDate(borrowingDate)
                .build();
        return borrowingRecordDAO.save(borrowingRecord);
    }

    @Override
    @Transactional
    public BorrowingRecord returnBook(LocalDateTime returnDate, Integer bookId, Integer patronId) throws Exception {
        Optional<BorrowingRecord> borrowingRecord = borrowingRecordDAO.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);
        if(borrowingRecord.isPresent())
        {
            borrowingRecord.get().setReturnDate(returnDate);
            return borrowingRecordDAO.save(borrowingRecord.get());
        }
        else
        {
            throw new Exception("book not borrowed.");
        }
    }
}
