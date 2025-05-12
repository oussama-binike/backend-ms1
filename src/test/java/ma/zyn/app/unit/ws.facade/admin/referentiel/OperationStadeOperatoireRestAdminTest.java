package ma.zyn.app.unit.ws.facade.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import ma.zyn.app.service.impl.admin.referentiel.OperationStadeOperatoireAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.referentiel.OperationStadeOperatoireRestAdmin;
import ma.zyn.app.ws.converter.referentiel.OperationStadeOperatoireConverter;
import ma.zyn.app.ws.dto.referentiel.OperationStadeOperatoireDto;
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
public class OperationStadeOperatoireRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private OperationStadeOperatoireAdminServiceImpl service;
    @Mock
    private OperationStadeOperatoireConverter converter;

    @InjectMocks
    private OperationStadeOperatoireRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllOperationStadeOperatoireTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<OperationStadeOperatoireDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<OperationStadeOperatoireDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveOperationStadeOperatoireTest() throws Exception {
        // Mock data
        OperationStadeOperatoireDto requestDto = new OperationStadeOperatoireDto();
        OperationStadeOperatoire entity = new OperationStadeOperatoire();
        OperationStadeOperatoire saved = new OperationStadeOperatoire();
        OperationStadeOperatoireDto savedDto = new OperationStadeOperatoireDto();

        // Mock the converter to return the operationStadeOperatoire object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved operationStadeOperatoire DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<OperationStadeOperatoireDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        OperationStadeOperatoireDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved operationStadeOperatoire DTO
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
