package com.maids.librarymgmtsys.controller;

import com.maids.librarymgmtsys.dto.BorrowingRecordDTO;
import com.maids.librarymgmtsys.dto.PatronDTO;
import com.maids.librarymgmtsys.model.Patron;
import com.maids.librarymgmtsys.service.BorrowingRecordService;
import com.maids.librarymgmtsys.service.PatronService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> borrowBook(@PathVariable Integer bookId, @PathVariable Integer patronId, @RequestBody BorrowingRecordDTO borrowingRecordDTO) {
        try {
            return ResponseEntity.ok(modelMapper.map(borrowingRecordService.borrowBook(borrowingRecordDTO.getBorrowingDate(), bookId, patronId), BorrowingRecordDTO.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordDTO> returnBook(@PathVariable Integer bookId, @PathVariable Integer patronId, @RequestBody BorrowingRecordDTO borrowingRecordDTO) {
        try {
            return ResponseEntity.ok(modelMapper.map(borrowingRecordService.borrowBook(borrowingRecordDTO.getReturnDate(), bookId, patronId), BorrowingRecordDTO.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
