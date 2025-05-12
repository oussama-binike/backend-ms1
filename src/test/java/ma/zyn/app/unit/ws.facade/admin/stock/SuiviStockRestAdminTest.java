package ma.zyn.app.unit.ws.facade.admin.stock;

import ma.zyn.app.bean.core.stock.SuiviStock;
import ma.zyn.app.service.impl.admin.stock.SuiviStockAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.stock.SuiviStockRestAdmin;
import ma.zyn.app.ws.converter.stock.SuiviStockConverter;
import ma.zyn.app.ws.dto.stock.SuiviStockDto;
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
public class SuiviStockRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private SuiviStockAdminServiceImpl service;
    @Mock
    private SuiviStockConverter converter;

    @InjectMocks
    private SuiviStockRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllSuiviStockTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<SuiviStockDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<SuiviStockDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveSuiviStockTest() throws Exception {
        // Mock data
        SuiviStockDto requestDto = new SuiviStockDto();
        SuiviStock entity = new SuiviStock();
        SuiviStock saved = new SuiviStock();
        SuiviStockDto savedDto = new SuiviStockDto();

        // Mock the converter to return the suiviStock object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved suiviStock DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<SuiviStockDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        SuiviStockDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved suiviStock DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getVolumeReel(), responseBody.getVolumeReel());
        assertEquals(savedDto.getVolumeEstime(), responseBody.getVolumeEstime());
        assertEquals(savedDto.getDateFlux(), responseBody.getDateFlux());
        assertEquals(savedDto.getRepereSourceDebut(), responseBody.getRepereSourceDebut());
        assertEquals(savedDto.getRepereSourceFin(), responseBody.getRepereSourceFin());
        assertEquals(savedDto.getRepereDestinationDebut(), responseBody.getRepereDestinationDebut());
        assertEquals(savedDto.getRepereDestinationFin(), responseBody.getRepereDestinationFin());
        assertEquals(savedDto.getPositionRouePelle(), responseBody.getPositionRouePelle());
        assertEquals(savedDto.getPositionStacker(), responseBody.getPositionStacker());
    }

}
