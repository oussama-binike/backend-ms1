package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import ma.zyn.app.dao.facade.core.referentiel.StatusScenarioFluxDao;
import ma.zyn.app.service.impl.admin.referentiel.StatusScenarioFluxAdminServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class StatusScenarioFluxAdminServiceImplTest {

    @Mock
    private StatusScenarioFluxDao repository;
    private AutoCloseable autoCloseable;
    private StatusScenarioFluxAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StatusScenarioFluxAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllStatusScenarioFlux() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveStatusScenarioFlux() {
        // Given
        StatusScenarioFlux toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteStatusScenarioFlux() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetStatusScenarioFluxById() {
        // Given
        Long idToRetrieve = 1L; // Example StatusScenarioFlux ID to retrieve
        StatusScenarioFlux expected = new StatusScenarioFlux(); // You need to replace StatusScenarioFlux with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        StatusScenarioFlux result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private StatusScenarioFlux constructSample(int i) {
		StatusScenarioFlux given = new StatusScenarioFlux();
        given.setLibelle("libelle-"+i);
        given.setCode("code-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
