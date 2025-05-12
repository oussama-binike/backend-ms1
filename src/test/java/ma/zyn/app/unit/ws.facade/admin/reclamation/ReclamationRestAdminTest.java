package ma.zyn.app.unit.ws.facade.admin.reclamation;

import ma.zyn.app.bean.core.reclamation.Reclamation;
import ma.zyn.app.service.impl.admin.reclamation.ReclamationAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.reclamation.ReclamationRestAdmin;
import ma.zyn.app.ws.converter.reclamation.ReclamationConverter;
import ma.zyn.app.ws.dto.reclamation.ReclamationDto;
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
public class ReclamationRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ReclamationAdminServiceImpl service;
    @Mock
    private ReclamationConverter converter;

    @InjectMocks
    private ReclamationRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllReclamationTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ReclamationDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ReclamationDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveReclamationTest() throws Exception {
        // Mock data
        ReclamationDto requestDto = new ReclamationDto();
        Reclamation entity = new Reclamation();
        Reclamation saved = new Reclamation();
        ReclamationDto savedDto = new ReclamationDto();

        // Mock the converter to return the reclamation object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved reclamation DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ReclamationDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ReclamationDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved reclamation DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getCommentaire(), responseBody.getCommentaire());
        assertEquals(savedDto.getQuantite(), responseBody.getQuantite());
        assertEquals(savedDto.getFonde(), responseBody.getFonde());
        assertEquals(savedDto.getMotifReclamation(), responseBody.getMotifReclamation());
        assertEquals(savedDto.getDateOccurence(), responseBody.getDateOccurence());
        assertEquals(savedDto.getDateReception(), responseBody.getDateReception());
        assertEquals(savedDto.getActionEntreprise(), responseBody.getActionEntreprise());
    }

}
