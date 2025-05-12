package ma.zyn.app.unit.ws.facade.admin.navire;

import ma.zyn.app.bean.core.navire.RealisationNavire;
import ma.zyn.app.service.impl.admin.navire.RealisationNavireAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.navire.RealisationNavireRestAdmin;
import ma.zyn.app.ws.converter.navire.RealisationNavireConverter;
import ma.zyn.app.ws.dto.navire.RealisationNavireDto;
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
public class RealisationNavireRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RealisationNavireAdminServiceImpl service;
    @Mock
    private RealisationNavireConverter converter;

    @InjectMocks
    private RealisationNavireRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRealisationNavireTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RealisationNavireDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RealisationNavireDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRealisationNavireTest() throws Exception {
        // Mock data
        RealisationNavireDto requestDto = new RealisationNavireDto();
        RealisationNavire entity = new RealisationNavire();
        RealisationNavire saved = new RealisationNavire();
        RealisationNavireDto savedDto = new RealisationNavireDto();

        // Mock the converter to return the realisationNavire object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved realisationNavire DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RealisationNavireDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RealisationNavireDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved realisationNavire DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getNumeroNavire(), responseBody.getNumeroNavire());
        assertEquals(savedDto.getNumeroExpedition(), responseBody.getNumeroExpedition());
        assertEquals(savedDto.getJour(), responseBody.getJour());
        assertEquals(savedDto.getTauxCompletude(), responseBody.getTauxCompletude());
        assertEquals(savedDto.getTauxRemplissage(), responseBody.getTauxRemplissage());
        assertEquals(savedDto.getDateChargement(), responseBody.getDateChargement());
        assertEquals(savedDto.getDateFinChargement(), responseBody.getDateFinChargement());
    }

}
