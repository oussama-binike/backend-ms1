package ma.zyn.app.unit.ws.facade.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;
import ma.zyn.app.service.impl.admin.referentiel.ConsommableStadeOperatoireAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.referentiel.ConsommableStadeOperatoireRestAdmin;
import ma.zyn.app.ws.converter.referentiel.ConsommableStadeOperatoireConverter;
import ma.zyn.app.ws.dto.referentiel.ConsommableStadeOperatoireDto;
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
public class ConsommableStadeOperatoireRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ConsommableStadeOperatoireAdminServiceImpl service;
    @Mock
    private ConsommableStadeOperatoireConverter converter;

    @InjectMocks
    private ConsommableStadeOperatoireRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllConsommableStadeOperatoireTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ConsommableStadeOperatoireDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ConsommableStadeOperatoireDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveConsommableStadeOperatoireTest() throws Exception {
        // Mock data
        ConsommableStadeOperatoireDto requestDto = new ConsommableStadeOperatoireDto();
        ConsommableStadeOperatoire entity = new ConsommableStadeOperatoire();
        ConsommableStadeOperatoire saved = new ConsommableStadeOperatoire();
        ConsommableStadeOperatoireDto savedDto = new ConsommableStadeOperatoireDto();

        // Mock the converter to return the consommableStadeOperatoire object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved consommableStadeOperatoire DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ConsommableStadeOperatoireDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ConsommableStadeOperatoireDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved consommableStadeOperatoire DTO
    }

}
