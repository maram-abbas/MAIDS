package com.maids.librarymgmtsys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MaramAbbas
 * @Created Aug 07, 2024-5:53 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatronDTO {

    private Integer id;

    private String name;

    private String contactInformation;
}
