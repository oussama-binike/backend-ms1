package ma.zyn.app.unit.ws.facade.admin.navire;

import ma.zyn.app.bean.core.navire.RealisationNavireProduit;
import ma.zyn.app.service.impl.admin.navire.RealisationNavireProduitAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.navire.RealisationNavireProduitRestAdmin;
import ma.zyn.app.ws.converter.navire.RealisationNavireProduitConverter;
import ma.zyn.app.ws.dto.navire.RealisationNavireProduitDto;
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
public class RealisationNavireProduitRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private RealisationNavireProduitAdminServiceImpl service;
    @Mock
    private RealisationNavireProduitConverter converter;

    @InjectMocks
    private RealisationNavireProduitRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllRealisationNavireProduitTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<RealisationNavireProduitDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<RealisationNavireProduitDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveRealisationNavireProduitTest() throws Exception {
        // Mock data
        RealisationNavireProduitDto requestDto = new RealisationNavireProduitDto();
        RealisationNavireProduit entity = new RealisationNavireProduit();
        RealisationNavireProduit saved = new RealisationNavireProduit();
        RealisationNavireProduitDto savedDto = new RealisationNavireProduitDto();

        // Mock the converter to return the realisationNavireProduit object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved realisationNavireProduit DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<RealisationNavireProduitDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        RealisationNavireProduitDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved realisationNavireProduit DTO
        assertEquals(savedDto.getTsm(), responseBody.getTsm());
        assertEquals(savedDto.getVolume(), responseBody.getVolume());
    }

}
