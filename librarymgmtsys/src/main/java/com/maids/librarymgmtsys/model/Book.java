package com.maids.librarymgmtsys.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="TITLE", nullable=false)
    private String title;

    @Column(name="AUTHOR", nullable=false)
    private String author;

    @Column(name = "PUBLICATION_YEAR", nullable = false)
    private Year publicationYear;

    @Column(name = "ISBN", nullable = false, unique = true)
    @Size(min = 10, max = 13)
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BorrowingRecord> borrowingRecords;
}
