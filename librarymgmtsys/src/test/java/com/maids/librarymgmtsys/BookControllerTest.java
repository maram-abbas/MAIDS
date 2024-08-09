package com.maids.librarymgmtsys.controller;

import com.maids.librarymgmtsys.dto.BookDTO;
import com.maids.librarymgmtsys.model.Book;
import com.maids.librarymgmtsys.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private ModelMapper modelMapper;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        book = new Book(1, "Book Title", "Author", Year.of(2020), "1234567890123", null);
        bookDTO = new BookDTO(1, "Book Title", "Author", Year.of(2020), "1234567890123");
    }

    @Test
    public void testGetBooks() {
        when(bookService.getAll()).thenReturn(Arrays.asList(book));
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(bookDTO);

        ResponseEntity<List<BookDTO>> response = bookController.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(bookService, times(1)).getAll();
    }

    @Test
    public void testGetBookById_Success() {
        when(bookService.getById(1)).thenReturn(book);
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.getBookById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDTO, response.getBody());
    }

    @Test
    public void testGetBookById_NotFound() {
        when(bookService.getById(1)).thenReturn(null);

        ResponseEntity<BookDTO> response = bookController.getBookById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testSaveBook() {
        when(bookService.save(any(Book.class))).thenReturn(book);
        when(modelMapper.map(any(BookDTO.class), eq(Book.class))).thenReturn(book);
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.saveBook(bookDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookDTO, response.getBody());
        verify(bookService, times(1)).save(any(Book.class));
    }

    @Test
    public void testUpdateBook() {
        when(bookService.update(eq(1), any(Book.class))).thenReturn(book);
        when(modelMapper.map(any(BookDTO.class), eq(Book.class))).thenReturn(book);
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.updateBook(1, bookDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDTO, response.getBody());
        verify(bookService, times(1)).update(eq(1), any(Book.class));
    }

    @Test
    public void testDeleteBook_Success() {
        doNothing().when(bookService).delete(1);

        ResponseEntity<Void> response = bookController.deleteBook(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).delete(1);
    }

    @Test
    public void testDeleteBook_NotFound() {
        doThrow(new RuntimeException("Not Found")).when(bookService).delete(1);

        ResponseEntity<Void> response = bookController.deleteBook(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).delete(1);
    }
}
