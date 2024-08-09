package com.maids.librarymgmtsys.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.maids.librarymgmtsys.model.Book;
import com.maids.librarymgmtsys.model.Patron;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-5:53 AM
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BORROWING_RECORD")
public class BorrowingRecordDTO {

    private Long id;

    private BookDTO book;

    private PatronDTO patron;

    private LocalDateTime borrowingDate;

    private LocalDateTime returnDate;
}
