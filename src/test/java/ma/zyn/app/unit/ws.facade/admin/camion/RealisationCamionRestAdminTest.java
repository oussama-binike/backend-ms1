package ma.zyn.app.unit.ws.facade.admin.camion;

import ma.zyn.app.bean.core.camion.RealisationCamion;
import ma.zyn.app.service.impl.admin.camion.RealisationCamionAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.camion.RealisationCamionRestAdmin;
import ma.zyn.app.ws.converter.camion.RealisationCamionConverter;
import ma.zyn.app.ws.dto.camion.RealisationCamionDto;
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
public class RealisationCamionRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RealisationCamionAdminServiceImpl service;
    @Mock
    private RealisationCamionConverter converter;

    @InjectMocks
    private RealisationCamionRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRealisationCamionTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RealisationCamionDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RealisationCamionDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRealisationCamionTest() throws Exception {
        // Mock data
        RealisationCamionDto requestDto = new RealisationCamionDto();
        RealisationCamion entity = new RealisationCamion();
        RealisationCamion saved = new RealisationCamion();
        RealisationCamionDto savedDto = new RealisationCamionDto();

        // Mock the converter to return the realisationCamion object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved realisationCamion DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RealisationCamionDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RealisationCamionDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved realisationCamion DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getJour(), responseBody.getJour());
        assertEquals(savedDto.getNombreCamions(), responseBody.getNombreCamions());
        assertEquals(savedDto.getDureeMoyenneTransport(), responseBody.getDureeMoyenneTransport());
        assertEquals(savedDto.getTotalTms(), responseBody.getTotalTms());
    }

}
