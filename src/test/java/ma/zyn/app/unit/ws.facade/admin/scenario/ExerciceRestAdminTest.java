package ma.zyn.app.unit.ws.facade.admin.scenario;

import ma.zyn.app.bean.core.scenario.Exercice;
import ma.zyn.app.service.impl.admin.scenario.ExerciceAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.scenario.ExerciceRestAdmin;
import ma.zyn.app.ws.converter.scenario.ExerciceConverter;
import ma.zyn.app.ws.dto.scenario.ExerciceDto;
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
public class ExerciceRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ExerciceAdminServiceImpl service;
    @Mock
    private ExerciceConverter converter;

    @InjectMocks
    private ExerciceRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllExerciceTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ExerciceDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ExerciceDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveExerciceTest() throws Exception {
        // Mock data
        ExerciceDto requestDto = new ExerciceDto();
        Exercice entity = new Exercice();
        Exercice saved = new Exercice();
        ExerciceDto savedDto = new ExerciceDto();

        // Mock the converter to return the exercice object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved exercice DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ExerciceDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ExerciceDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved exercice DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getMois(), responseBody.getMois());
        assertEquals(savedDto.getAnnee(), responseBody.getAnnee());
        assertEquals(savedDto.getDateDebut(), responseBody.getDateDebut());
        assertEquals(savedDto.getDateFin(), responseBody.getDateFin());
        assertEquals(savedDto.getDateRetrospective(), responseBody.getDateRetrospective());
    }

}
