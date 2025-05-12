package ma.zyn.app.unit.ws.facade.admin.camion;

import ma.zyn.app.bean.core.camion.DestinationCamion;
import ma.zyn.app.service.impl.admin.camion.DestinationCamionAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.camion.DestinationCamionRestAdmin;
import ma.zyn.app.ws.converter.camion.DestinationCamionConverter;
import ma.zyn.app.ws.dto.camion.DestinationCamionDto;
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
public class DestinationCamionRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DestinationCamionAdminServiceImpl service;
    @Mock
    private DestinationCamionConverter converter;

    @InjectMocks
    private DestinationCamionRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDestinationCamionTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DestinationCamionDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DestinationCamionDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDestinationCamionTest() throws Exception {
        // Mock data
        DestinationCamionDto requestDto = new DestinationCamionDto();
        DestinationCamion entity = new DestinationCamion();
        DestinationCamion saved = new DestinationCamion();
        DestinationCamionDto savedDto = new DestinationCamionDto();

        // Mock the converter to return the destinationCamion object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved destinationCamion DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DestinationCamionDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DestinationCamionDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved destinationCamion DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
