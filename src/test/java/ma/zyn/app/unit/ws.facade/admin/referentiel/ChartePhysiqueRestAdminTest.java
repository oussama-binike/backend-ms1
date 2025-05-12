package ma.zyn.app.unit.ws.facade.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ChartePhysique;
import ma.zyn.app.service.impl.admin.referentiel.ChartePhysiqueAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.referentiel.ChartePhysiqueRestAdmin;
import ma.zyn.app.ws.converter.referentiel.ChartePhysiqueConverter;
import ma.zyn.app.ws.dto.referentiel.ChartePhysiqueDto;
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
public class ChartePhysiqueRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ChartePhysiqueAdminServiceImpl service;
    @Mock
    private ChartePhysiqueConverter converter;

    @InjectMocks
    private ChartePhysiqueRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllChartePhysiqueTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ChartePhysiqueDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ChartePhysiqueDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveChartePhysiqueTest() throws Exception {
        // Mock data
        ChartePhysiqueDto requestDto = new ChartePhysiqueDto();
        ChartePhysique entity = new ChartePhysique();
        ChartePhysique saved = new ChartePhysique();
        ChartePhysiqueDto savedDto = new ChartePhysiqueDto();

        // Mock the converter to return the chartePhysique object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved chartePhysique DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ChartePhysiqueDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ChartePhysiqueDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved chartePhysique DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getMinimumSize(), responseBody.getMinimumSize());
        assertEquals(savedDto.getMaximumSize(), responseBody.getMaximumSize());
        assertEquals(savedDto.getValeur(), responseBody.getValeur());
    }

}
