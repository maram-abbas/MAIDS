package com.maids.librarymgmtsys.controller;

import com.maids.librarymgmtsys.dto.PatronDTO;
import com.maids.librarymgmtsys.model.Patron;
import com.maids.librarymgmtsys.service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PatronController patronController;

    private Patron patron;
    private PatronDTO patronDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patron = new Patron();
        patron.setId(1);
        patron.setName("John Doe");
        patron.setContactInformation("01000000000");

        patronDTO = new PatronDTO();
        patronDTO.setId(1);
        patronDTO.setName("John Doe");
        patronDTO.setContactInformation("01000000000");
    }

    @Test
    void testGetPatrons() {
        when(patronService.getAll()).thenReturn(Arrays.asList(patron));
        when(modelMapper.map(any(Patron.class), eq(PatronDTO.class))).thenReturn(patronDTO);

        ResponseEntity<List<PatronDTO>> response = patronController.getPatrons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(patronService, times(1)).getAll();
    }

    @Test
    void testGetPatronById() {
        when(patronService.getById(1)).thenReturn(patron);
        when(modelMapper.map(any(Patron.class), eq(PatronDTO.class))).thenReturn(patronDTO);

        ResponseEntity<PatronDTO> response = patronController.getPatronById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patronDTO, response.getBody());
        verify(patronService, times(1)).getById(1);
    }

    @Test
    void testGetPatronById_NotFound() {
        when(patronService.getById(1)).thenReturn(null);

        ResponseEntity<PatronDTO> response = patronController.getPatronById(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(patronService, times(1)).getById(1);
    }

    @Test
    void testSavePatron() {
        when(patronService.save(any(Patron.class))).thenReturn(patron);
        when(modelMapper.map(any(PatronDTO.class), eq(Patron.class))).thenReturn(patron);
        when(modelMapper.map(any(Patron.class), eq(PatronDTO.class))).thenReturn(patronDTO);

        ResponseEntity<PatronDTO> response = patronController.savePatron(patronDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patronDTO, response.getBody());
        verify(patronService, times(1)).save(any(Patron.class));
    }

    @Test
    void testUpdatePatron() {
        when(patronService.update(anyInt(), any(Patron.class))).thenReturn(patron);
        when(modelMapper.map(any(PatronDTO.class), eq(Patron.class))).thenReturn(patron);
        when(modelMapper.map(any(Patron.class), eq(PatronDTO.class))).thenReturn(patronDTO);

        ResponseEntity<PatronDTO> response = patronController.updatePatron(1, patronDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patronDTO, response.getBody());
        verify(patronService, times(1)).update(eq(1), any(Patron.class));
    }

    @Test
    void testDeletePatron() {
        doNothing().when(patronService).delete(1);

        ResponseEntity<Void> response = patronController.deletePatron(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(patronService, times(1)).delete(1);
    }

    @Test
    void testDeletePatron_NotFound() {
        doThrow(new RuntimeException()).when(patronService).delete(1);

        ResponseEntity<Void> response = patronController.deletePatron(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(patronService, times(1)).delete(1);
    }
}
