package ma.zyn.app.unit.service.impl.admin.scenario;

import ma.zyn.app.bean.core.scenario.Exercice;
import ma.zyn.app.dao.facade.core.scenario.ExerciceDao;
import ma.zyn.app.service.impl.admin.scenario.ExerciceAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.StatusExercice ;
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
class ExerciceAdminServiceImplTest {

    @Mock
    private ExerciceDao repository;
    private AutoCloseable autoCloseable;
    private ExerciceAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ExerciceAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllExercice() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveExercice() {
        // Given
        Exercice toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteExercice() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetExerciceById() {
        // Given
        Long idToRetrieve = 1L; // Example Exercice ID to retrieve
        Exercice expected = new Exercice(); // You need to replace Exercice with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Exercice result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Exercice constructSample(int i) {
		Exercice given = new Exercice();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setMois(i);
        given.setAnnee(i);
        given.setDateDebut(LocalDateTime.now());
        given.setDateFin(LocalDateTime.now());
        given.setDateRetrospective(LocalDateTime.now());
        given.setStatusExercice(new StatusExercice(1L));
        return given;
    }

}
