package ma.zyn.app.unit.ws.facade.admin.planmaintenance;

import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import ma.zyn.app.service.impl.admin.planmaintenance.TauxRendementStadeOperatoireAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.planmaintenance.TauxRendementStadeOperatoireRestAdmin;
import ma.zyn.app.ws.converter.planmaintenance.TauxRendementStadeOperatoireConverter;
import ma.zyn.app.ws.dto.planmaintenance.TauxRendementStadeOperatoireDto;
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
public class TauxRendementStadeOperatoireRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private TauxRendementStadeOperatoireAdminServiceImpl service;
    @Mock
    private TauxRendementStadeOperatoireConverter converter;

    @InjectMocks
    private TauxRendementStadeOperatoireRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllTauxRendementStadeOperatoireTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<TauxRendementStadeOperatoireDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<TauxRendementStadeOperatoireDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveTauxRendementStadeOperatoireTest() throws Exception {
        // Mock data
        TauxRendementStadeOperatoireDto requestDto = new TauxRendementStadeOperatoireDto();
        TauxRendementStadeOperatoire entity = new TauxRendementStadeOperatoire();
        TauxRendementStadeOperatoire saved = new TauxRendementStadeOperatoire();
        TauxRendementStadeOperatoireDto savedDto = new TauxRendementStadeOperatoireDto();

        // Mock the converter to return the tauxRendementStadeOperatoire object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved tauxRendementStadeOperatoire DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<TauxRendementStadeOperatoireDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        TauxRendementStadeOperatoireDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved tauxRendementStadeOperatoire DTO
        assertEquals(savedDto.getJour(), responseBody.getJour());
        assertEquals(savedDto.getTauxRendementGlobal(), responseBody.getTauxRendementGlobal());
    }

}
