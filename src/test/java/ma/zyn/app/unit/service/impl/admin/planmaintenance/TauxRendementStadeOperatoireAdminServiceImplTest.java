package ma.zyn.app.unit.service.impl.admin.planmaintenance;

import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import ma.zyn.app.dao.facade.core.planmaintenance.TauxRendementStadeOperatoireDao;
import ma.zyn.app.service.impl.admin.planmaintenance.TauxRendementStadeOperatoireAdminServiceImpl;

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
class TauxRendementStadeOperatoireAdminServiceImplTest {

    @Mock
    private TauxRendementStadeOperatoireDao repository;
    private AutoCloseable autoCloseable;
    private TauxRendementStadeOperatoireAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new TauxRendementStadeOperatoireAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllTauxRendementStadeOperatoire() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveTauxRendementStadeOperatoire() {
        // Given
        TauxRendementStadeOperatoire toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteTauxRendementStadeOperatoire() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetTauxRendementStadeOperatoireById() {
        // Given
        Long idToRetrieve = 1L; // Example TauxRendementStadeOperatoire ID to retrieve
        TauxRendementStadeOperatoire expected = new TauxRendementStadeOperatoire(); // You need to replace TauxRendementStadeOperatoire with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        TauxRendementStadeOperatoire result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private TauxRendementStadeOperatoire constructSample(int i) {
		TauxRendementStadeOperatoire given = new TauxRendementStadeOperatoire();
        given.setJour(LocalDateTime.now());
        given.setTauxRendementGlobal(BigDecimal.TEN);
        given.setScenarioFlux(new ScenarioFlux(1L));
        return given;
    }

}
