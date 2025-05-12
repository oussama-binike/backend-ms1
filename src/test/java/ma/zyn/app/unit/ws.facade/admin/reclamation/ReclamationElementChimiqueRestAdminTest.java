package ma.zyn.app.unit.ws.facade.admin.reclamation;

import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;
import ma.zyn.app.service.impl.admin.reclamation.ReclamationElementChimiqueAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.reclamation.ReclamationElementChimiqueRestAdmin;
import ma.zyn.app.ws.converter.reclamation.ReclamationElementChimiqueConverter;
import ma.zyn.app.ws.dto.reclamation.ReclamationElementChimiqueDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReclamationElementChimiqueRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ReclamationElementChimiqueAdminServiceImpl service;
    @Mock
    private ReclamationElementChimiqueConverter converter;

    @InjectMocks
    private ReclamationElementChimiqueRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllReclamationElementChimiqueTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ReclamationElementChimiqueDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ReclamationElementChimiqueDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveReclamationElementChimiqueTest() throws Exception {
        // Mock data
        ReclamationElementChimiqueDto requestDto = new ReclamationElementChimiqueDto();
        ReclamationElementChimique entity = new ReclamationElementChimique();
        ReclamationElementChimique saved = new ReclamationElementChimique();
        ReclamationElementChimiqueDto savedDto = new ReclamationElementChimiqueDto();

        // Mock the converter to return the reclamationElementChimique object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved reclamationElementChimique DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ReclamationElementChimiqueDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ReclamationElementChimiqueDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved reclamationElementChimique DTO
    }

}
