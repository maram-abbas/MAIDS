package com.maids.librarymgmtsys.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name = "BORROWING_RECORD")
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "BOOK_ID", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "PATRON_ID", insertable = false, updatable = false)
    private Patron patron;

    private LocalDateTime borrowingDate;

    private LocalDateTime returnDate;
}
