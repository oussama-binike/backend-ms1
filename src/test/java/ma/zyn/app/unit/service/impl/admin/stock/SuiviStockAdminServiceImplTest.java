package ma.zyn.app.unit.service.impl.admin.stock;

import ma.zyn.app.bean.core.stock.SuiviStock;
import ma.zyn.app.dao.facade.core.stock.SuiviStockDao;
import ma.zyn.app.service.impl.admin.stock.SuiviStockAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Liaison ;
import ma.zyn.app.bean.core.scenario.ScenarioFlux ;
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
class SuiviStockAdminServiceImplTest {

    @Mock
    private SuiviStockDao repository;
    private AutoCloseable autoCloseable;
    private SuiviStockAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SuiviStockAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllSuiviStock() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveSuiviStock() {
        // Given
        SuiviStock toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteSuiviStock() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetSuiviStockById() {
        // Given
        Long idToRetrieve = 1L; // Example SuiviStock ID to retrieve
        SuiviStock expected = new SuiviStock(); // You need to replace SuiviStock with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        SuiviStock result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private SuiviStock constructSample(int i) {
		SuiviStock given = new SuiviStock();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setLiaison(new Liaison(1L));
        given.setVolumeReel(BigDecimal.TEN);
        given.setVolumeEstime(BigDecimal.TEN);
        given.setDateFlux(LocalDateTime.now());
        given.setRepereSourceDebut(i);
        given.setRepereSourceFin(i);
        given.setRepereDestinationDebut(i);
        given.setRepereDestinationFin(i);
        given.setPositionRouePelle(i);
        given.setPositionStacker(i);
        given.setScenarioFlux(new ScenarioFlux(1L));
        return given;
    }

}
