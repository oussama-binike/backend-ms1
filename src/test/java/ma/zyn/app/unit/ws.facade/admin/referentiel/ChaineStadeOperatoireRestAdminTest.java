package ma.zyn.app.unit.ws.facade.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;
import ma.zyn.app.service.impl.admin.referentiel.ChaineStadeOperatoireAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.referentiel.ChaineStadeOperatoireRestAdmin;
import ma.zyn.app.ws.converter.referentiel.ChaineStadeOperatoireConverter;
import ma.zyn.app.ws.dto.referentiel.ChaineStadeOperatoireDto;
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
public class ChaineStadeOperatoireRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ChaineStadeOperatoireAdminServiceImpl service;
    @Mock
    private ChaineStadeOperatoireConverter converter;

    @InjectMocks
    private ChaineStadeOperatoireRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllChaineStadeOperatoireTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ChaineStadeOperatoireDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ChaineStadeOperatoireDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveChaineStadeOperatoireTest() throws Exception {
        // Mock data
        ChaineStadeOperatoireDto requestDto = new ChaineStadeOperatoireDto();
        ChaineStadeOperatoire entity = new ChaineStadeOperatoire();
        ChaineStadeOperatoire saved = new ChaineStadeOperatoire();
        ChaineStadeOperatoireDto savedDto = new ChaineStadeOperatoireDto();

        // Mock the converter to return the chaineStadeOperatoire object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved chaineStadeOperatoire DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ChaineStadeOperatoireDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ChaineStadeOperatoireDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved chaineStadeOperatoire DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
