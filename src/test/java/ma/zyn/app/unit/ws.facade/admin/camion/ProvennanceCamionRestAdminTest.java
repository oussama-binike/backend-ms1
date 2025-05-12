package ma.zyn.app.unit.ws.facade.admin.camion;

import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import ma.zyn.app.service.impl.admin.camion.ProvennanceCamionAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.camion.ProvennanceCamionRestAdmin;
import ma.zyn.app.ws.converter.camion.ProvennanceCamionConverter;
import ma.zyn.app.ws.dto.camion.ProvennanceCamionDto;
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
public class ProvennanceCamionRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ProvennanceCamionAdminServiceImpl service;
    @Mock
    private ProvennanceCamionConverter converter;

    @InjectMocks
    private ProvennanceCamionRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllProvennanceCamionTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ProvennanceCamionDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ProvennanceCamionDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveProvennanceCamionTest() throws Exception {
        // Mock data
        ProvennanceCamionDto requestDto = new ProvennanceCamionDto();
        ProvennanceCamion entity = new ProvennanceCamion();
        ProvennanceCamion saved = new ProvennanceCamion();
        ProvennanceCamionDto savedDto = new ProvennanceCamionDto();

        // Mock the converter to return the provennanceCamion object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved provennanceCamion DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ProvennanceCamionDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ProvennanceCamionDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved provennanceCamion DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
