package ma.zyn.app.unit.service.impl.admin.reclamation;

import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;
import ma.zyn.app.dao.facade.core.reclamation.ReclamationElementChimiqueDao;
import ma.zyn.app.service.impl.admin.reclamation.ReclamationElementChimiqueAdminServiceImpl;

import ma.zyn.app.bean.core.reclamation.Reclamation ;
import ma.zyn.app.bean.core.referentiel.ElementChimique ;
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
class ReclamationElementChimiqueAdminServiceImplTest {

    @Mock
    private ReclamationElementChimiqueDao repository;
    private AutoCloseable autoCloseable;
    private ReclamationElementChimiqueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ReclamationElementChimiqueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllReclamationElementChimique() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveReclamationElementChimique() {
        // Given
        ReclamationElementChimique toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteReclamationElementChimique() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetReclamationElementChimiqueById() {
        // Given
        Long idToRetrieve = 1L; // Example ReclamationElementChimique ID to retrieve
        ReclamationElementChimique expected = new ReclamationElementChimique(); // You need to replace ReclamationElementChimique with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ReclamationElementChimique result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ReclamationElementChimique constructSample(int i) {
		ReclamationElementChimique given = new ReclamationElementChimique();
        given.setReclamation(new Reclamation(1L));
        given.setElementChimique(new ElementChimique(1L));
        return given;
    }

}
