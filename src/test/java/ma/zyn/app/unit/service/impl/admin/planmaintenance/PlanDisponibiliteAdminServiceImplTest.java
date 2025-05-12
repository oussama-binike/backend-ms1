package ma.zyn.app.unit.service.impl.admin.planmaintenance;

import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import ma.zyn.app.dao.facade.core.planmaintenance.PlanDisponibiliteDao;
import ma.zyn.app.service.impl.admin.planmaintenance.PlanDisponibiliteAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
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
class PlanDisponibiliteAdminServiceImplTest {

    @Mock
    private PlanDisponibiliteDao repository;
    private AutoCloseable autoCloseable;
    private PlanDisponibiliteAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PlanDisponibiliteAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllPlanDisponibilite() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSavePlanDisponibilite() {
        // Given
        PlanDisponibilite toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeletePlanDisponibilite() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetPlanDisponibiliteById() {
        // Given
        Long idToRetrieve = 1L; // Example PlanDisponibilite ID to retrieve
        PlanDisponibilite expected = new PlanDisponibilite(); // You need to replace PlanDisponibilite with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        PlanDisponibilite result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private PlanDisponibilite constructSample(int i) {
		PlanDisponibilite given = new PlanDisponibilite();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setNombreHeureArret(BigDecimal.TEN);
        given.setDateArretDebut(LocalDateTime.now());
        given.setDateArretFin(LocalDateTime.now());
        given.setScenarioFlux(new ScenarioFlux(1L));
        return given;
    }

}
