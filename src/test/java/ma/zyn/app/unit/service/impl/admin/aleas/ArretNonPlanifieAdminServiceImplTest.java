package ma.zyn.app.unit.service.impl.admin.aleas;

import ma.zyn.app.bean.core.aleas.ArretNonPlanifie;
import ma.zyn.app.dao.facade.core.aleas.ArretNonPlanifieDao;
import ma.zyn.app.service.impl.admin.aleas.ArretNonPlanifieAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise ;
import ma.zyn.app.bean.core.aleas.CauseArret ;
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
class ArretNonPlanifieAdminServiceImplTest {

    @Mock
    private ArretNonPlanifieDao repository;
    private AutoCloseable autoCloseable;
    private ArretNonPlanifieAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ArretNonPlanifieAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllArretNonPlanifie() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveArretNonPlanifie() {
        // Given
        ArretNonPlanifie toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteArretNonPlanifie() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetArretNonPlanifieById() {
        // Given
        Long idToRetrieve = 1L; // Example ArretNonPlanifie ID to retrieve
        ArretNonPlanifie expected = new ArretNonPlanifie(); // You need to replace ArretNonPlanifie with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ArretNonPlanifie result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ArretNonPlanifie constructSample(int i) {
		ArretNonPlanifie given = new ArretNonPlanifie();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setCommentaire("commentaire-"+i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setCauseArret(new CauseArret(1L));
        given.setDureeEstimee(BigDecimal.TEN);
        given.setDateArret(LocalDateTime.now());
        given.setDateDebut(LocalDateTime.now());
        given.setDateFin(LocalDateTime.now());
        given.setActionEntreprise(new ActionEntreprise(1L));
        return given;
    }

}
