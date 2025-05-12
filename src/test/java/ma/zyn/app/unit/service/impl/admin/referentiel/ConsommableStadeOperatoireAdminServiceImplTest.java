package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ConsommableStadeOperatoire;
import ma.zyn.app.dao.facade.core.referentiel.ConsommableStadeOperatoireDao;
import ma.zyn.app.service.impl.admin.referentiel.ConsommableStadeOperatoireAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Consommable ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
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
class ConsommableStadeOperatoireAdminServiceImplTest {

    @Mock
    private ConsommableStadeOperatoireDao repository;
    private AutoCloseable autoCloseable;
    private ConsommableStadeOperatoireAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ConsommableStadeOperatoireAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllConsommableStadeOperatoire() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveConsommableStadeOperatoire() {
        // Given
        ConsommableStadeOperatoire toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteConsommableStadeOperatoire() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetConsommableStadeOperatoireById() {
        // Given
        Long idToRetrieve = 1L; // Example ConsommableStadeOperatoire ID to retrieve
        ConsommableStadeOperatoire expected = new ConsommableStadeOperatoire(); // You need to replace ConsommableStadeOperatoire with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ConsommableStadeOperatoire result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ConsommableStadeOperatoire constructSample(int i) {
		ConsommableStadeOperatoire given = new ConsommableStadeOperatoire();
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setConsommable(new Consommable(1L));
        return given;
    }

}
