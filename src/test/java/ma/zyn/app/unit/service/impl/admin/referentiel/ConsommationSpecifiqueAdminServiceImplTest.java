package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ConsommationSpecifique;
import ma.zyn.app.dao.facade.core.referentiel.ConsommationSpecifiqueDao;
import ma.zyn.app.service.impl.admin.referentiel.ConsommationSpecifiqueAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Unite ;
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
class ConsommationSpecifiqueAdminServiceImplTest {

    @Mock
    private ConsommationSpecifiqueDao repository;
    private AutoCloseable autoCloseable;
    private ConsommationSpecifiqueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ConsommationSpecifiqueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllConsommationSpecifique() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveConsommationSpecifique() {
        // Given
        ConsommationSpecifique toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteConsommationSpecifique() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetConsommationSpecifiqueById() {
        // Given
        Long idToRetrieve = 1L; // Example ConsommationSpecifique ID to retrieve
        ConsommationSpecifique expected = new ConsommationSpecifique(); // You need to replace ConsommationSpecifique with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ConsommationSpecifique result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ConsommationSpecifique constructSample(int i) {
		ConsommationSpecifique given = new ConsommationSpecifique();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setConsommable(new Consommable(1L));
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setValeur(BigDecimal.TEN);
        given.setDateConsommationSpecifique(LocalDateTime.now());
        given.setUnite(new Unite(1L));
        return given;
    }

}
