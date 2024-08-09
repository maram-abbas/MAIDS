package com.maids.librarymgmtsys;

import com.maids.librarymgmtsys.controller.BorrowingRecordController;
import com.maids.librarymgmtsys.dto.BorrowingRecordDTO;
import com.maids.librarymgmtsys.model.BorrowingRecord;
import com.maids.librarymgmtsys.service.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    private BorrowingRecord borrowingRecord;
    private BorrowingRecordDTO borrowingRecordDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);
        borrowingRecord.setBorrowingDate(LocalDateTime.now());

        borrowingRecordDTO = new BorrowingRecordDTO();
        borrowingRecordDTO.setId(1L);
        borrowingRecordDTO.setBorrowingDate(LocalDateTime.now());
    }

    @Test
    void testBorrowBook_Success() throws Exception {
        when(borrowingRecordService.borrowBook(any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(borrowingRecord);
        when(modelMapper.map(any(BorrowingRecord.class), eq(BorrowingRecordDTO.class))).thenReturn(borrowingRecordDTO);

        ResponseEntity<BorrowingRecordDTO> response = borrowingRecordController.borrowBook(1, 1, borrowingRecordDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(borrowingRecordDTO, response.getBody());
        verify(borrowingRecordService, times(1)).borrowBook(any(LocalDateTime.class), eq(1), eq(1));
    }

    @Test
    void testBorrowBook_BadRequest() throws Exception {
        when(borrowingRecordService.borrowBook(any(LocalDateTime.class), anyInt(), anyInt())).thenThrow(new RuntimeException());

        ResponseEntity<BorrowingRecordDTO> response = borrowingRecordController.borrowBook(1, 1, borrowingRecordDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(borrowingRecordService, times(1)).borrowBook(any(LocalDateTime.class), eq(1), eq(1));
    }

    @Test
    void testReturnBook_Success() throws Exception {
        when(borrowingRecordService.returnBook(any(LocalDateTime.class), anyInt(), anyInt())).thenReturn(borrowingRecord);
        when(modelMapper.map(any(BorrowingRecord.class), eq(BorrowingRecordDTO.class))).thenReturn(borrowingRecordDTO);

        ResponseEntity<BorrowingRecordDTO> response = borrowingRecordController.returnBook(1, 1, borrowingRecordDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(borrowingRecordDTO, response.getBody());
        verify(borrowingRecordService, times(1)).returnBook(any(LocalDateTime.class), eq(1), eq(1));
    }

    @Test
    void testReturnBook_BadRequest() throws Exception {
        when(borrowingRecordService.returnBook(any(LocalDateTime.class), anyInt(), anyInt())).thenThrow(new RuntimeException());

        ResponseEntity<BorrowingRecordDTO> response = borrowingRecordController.returnBook(1, 1, borrowingRecordDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(borrowingRecordService, times(1)).returnBook(any(LocalDateTime.class), eq(1), eq(1));
    }
}
