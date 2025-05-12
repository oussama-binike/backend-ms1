package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.dao.facade.core.referentiel.LiaisonDao;
import ma.zyn.app.service.impl.admin.referentiel.LiaisonAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Stock ;
import ma.zyn.app.bean.core.referentiel.Engin ;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.Produit ;
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
class LiaisonAdminServiceImplTest {

    @Mock
    private LiaisonDao repository;
    private AutoCloseable autoCloseable;
    private LiaisonAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new LiaisonAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllLiaison() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveLiaison() {
        // Given
        Liaison toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteLiaison() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetLiaisonById() {
        // Given
        Long idToRetrieve = 1L; // Example Liaison ID to retrieve
        Liaison expected = new Liaison(); // You need to replace Liaison with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Liaison result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Liaison constructSample(int i) {
		Liaison given = new Liaison();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setStockSource(new Stock(1L));
        given.setStockDestination(new Stock(1L));
        given.setEngin(new Engin(1L));
        given.setOperationStadeOperatoire(new OperationStadeOperatoire(1L));
        given.setProdduit(new Produit(1L));
        return given;
    }

}
