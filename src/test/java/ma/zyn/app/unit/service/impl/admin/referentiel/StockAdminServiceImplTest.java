package ma.zyn.app.unit.service.impl.admin.referentiel;

import ma.zyn.app.bean.core.referentiel.Stock;
import ma.zyn.app.dao.facade.core.referentiel.StockDao;
import ma.zyn.app.service.impl.admin.referentiel.StockAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.TypeStock ;
import ma.zyn.app.bean.core.referentiel.CategorieStock ;
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
class StockAdminServiceImplTest {

    @Mock
    private StockDao repository;
    private AutoCloseable autoCloseable;
    private StockAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StockAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllStock() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveStock() {
        // Given
        Stock toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteStock() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetStockById() {
        // Given
        Long idToRetrieve = 1L; // Example Stock ID to retrieve
        Stock expected = new Stock(); // You need to replace Stock with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Stock result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Stock constructSample(int i) {
		Stock given = new Stock();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setAutonomie(BigDecimal.TEN);
        given.setCapacite(BigDecimal.TEN);
        given.setNombreRepere(i);
        given.setRepereDebut(i);
        given.setRepereFin(i);
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setTypeStock(new TypeStock(1L));
        given.setCategorieStock(new CategorieStock(1L));
        return given;
    }

}
