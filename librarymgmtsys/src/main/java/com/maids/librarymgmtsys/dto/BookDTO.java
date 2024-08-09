package com.maids.librarymgmtsys.dto;

import com.maids.librarymgmtsys.validator.ValidISBN;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-5:53 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Integer id;

    private String title;

    private String author;

    private Year publicationYear;

    @ValidISBN
    private String isbn;
}
