package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;
import ma.zyn.app.dao.facade.core.referentiel.ChaineStadeOperatoireDao;
import ma.zyn.app.service.impl.admin.referentiel.ChaineStadeOperatoireAdminServiceImpl;

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
class ChaineStadeOperatoireAdminServiceImplTest {

    @Mock
    private ChaineStadeOperatoireDao repository;
    private AutoCloseable autoCloseable;
    private ChaineStadeOperatoireAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ChaineStadeOperatoireAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllChaineStadeOperatoire() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveChaineStadeOperatoire() {
        // Given
        ChaineStadeOperatoire toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteChaineStadeOperatoire() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetChaineStadeOperatoireById() {
        // Given
        Long idToRetrieve = 1L; // Example ChaineStadeOperatoire ID to retrieve
        ChaineStadeOperatoire expected = new ChaineStadeOperatoire(); // You need to replace ChaineStadeOperatoire with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ChaineStadeOperatoire result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ChaineStadeOperatoire constructSample(int i) {
		ChaineStadeOperatoire given = new ChaineStadeOperatoire();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        given.setDescription("description-"+i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        return given;
    }

}
