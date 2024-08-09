package com.maids.librarymgmtsys.controller;

import com.maids.librarymgmtsys.dto.BookDTO;
import com.maids.librarymgmtsys.model.Book;
import com.maids.librarymgmtsys.service.BookService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/books")
@Validated
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<BookDTO> books = bookService.getAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(modelMapper.map(book, BookDTO.class));
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO book) {
        Book savedBook = bookService.save(modelMapper.map(book, Book.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedBook, BookDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @Valid @RequestBody BookDTO book) {
        Book updatedBook = bookService.update(id, modelMapper.map(book, Book.class));
        return ResponseEntity.ok(modelMapper.map(updatedBook, BookDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        try {
            bookService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
