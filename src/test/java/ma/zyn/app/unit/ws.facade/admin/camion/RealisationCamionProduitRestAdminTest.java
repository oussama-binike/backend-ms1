package ma.zyn.app.unit.ws.facade.admin.camion;

import ma.zyn.app.bean.core.camion.RealisationCamionProduit;
import ma.zyn.app.service.impl.admin.camion.RealisationCamionProduitAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.camion.RealisationCamionProduitRestAdmin;
import ma.zyn.app.ws.converter.camion.RealisationCamionProduitConverter;
import ma.zyn.app.ws.dto.camion.RealisationCamionProduitDto;
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
public class RealisationCamionProduitRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RealisationCamionProduitAdminServiceImpl service;
    @Mock
    private RealisationCamionProduitConverter converter;

    @InjectMocks
    private RealisationCamionProduitRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRealisationCamionProduitTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RealisationCamionProduitDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RealisationCamionProduitDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRealisationCamionProduitTest() throws Exception {
        // Mock data
        RealisationCamionProduitDto requestDto = new RealisationCamionProduitDto();
        RealisationCamionProduit entity = new RealisationCamionProduit();
        RealisationCamionProduit saved = new RealisationCamionProduit();
        RealisationCamionProduitDto savedDto = new RealisationCamionProduitDto();

        // Mock the converter to return the realisationCamionProduit object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved realisationCamionProduit DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RealisationCamionProduitDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RealisationCamionProduitDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved realisationCamionProduit DTO
        assertEquals(savedDto.getTsm(), responseBody.getTsm());
    }

}
