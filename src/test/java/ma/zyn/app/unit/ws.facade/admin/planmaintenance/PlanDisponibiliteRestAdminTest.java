package ma.zyn.app.unit.ws.facade.admin.planmaintenance;

import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import ma.zyn.app.service.impl.admin.planmaintenance.PlanDisponibiliteAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.planmaintenance.PlanDisponibiliteRestAdmin;
import ma.zyn.app.ws.converter.planmaintenance.PlanDisponibiliteConverter;
import ma.zyn.app.ws.dto.planmaintenance.PlanDisponibiliteDto;
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
public class PlanDisponibiliteRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private PlanDisponibiliteAdminServiceImpl service;
    @Mock
    private PlanDisponibiliteConverter converter;

    @InjectMocks
    private PlanDisponibiliteRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllPlanDisponibiliteTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<PlanDisponibiliteDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<PlanDisponibiliteDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSavePlanDisponibiliteTest() throws Exception {
        // Mock data
        PlanDisponibiliteDto requestDto = new PlanDisponibiliteDto();
        PlanDisponibilite entity = new PlanDisponibilite();
        PlanDisponibilite saved = new PlanDisponibilite();
        PlanDisponibiliteDto savedDto = new PlanDisponibiliteDto();

        // Mock the converter to return the planDisponibilite object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved planDisponibilite DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<PlanDisponibiliteDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        PlanDisponibiliteDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved planDisponibilite DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getNombreHeureArret(), responseBody.getNombreHeureArret());
        assertEquals(savedDto.getDateArretDebut(), responseBody.getDateArretDebut());
        assertEquals(savedDto.getDateArretFin(), responseBody.getDateArretFin());
    }

}
