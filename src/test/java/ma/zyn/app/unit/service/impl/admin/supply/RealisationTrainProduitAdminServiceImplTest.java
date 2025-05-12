package ma.zyn.app.unit.service.impl.admin.supply;

import ma.zyn.app.bean.core.supply.RealisationTrainProduit;
import ma.zyn.app.dao.facade.core.supply.RealisationTrainProduitDao;
import ma.zyn.app.service.impl.admin.supply.RealisationTrainProduitAdminServiceImpl;

import ma.zyn.app.bean.core.supply.RealisationTrain ;
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
class RealisationTrainProduitAdminServiceImplTest {

    @Mock
    private RealisationTrainProduitDao repository;
    private AutoCloseable autoCloseable;
    private RealisationTrainProduitAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RealisationTrainProduitAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRealisationTrainProduit() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRealisationTrainProduit() {
        // Given
        RealisationTrainProduit toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRealisationTrainProduit() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRealisationTrainProduitById() {
        // Given
        Long idToRetrieve = 1L; // Example RealisationTrainProduit ID to retrieve
        RealisationTrainProduit expected = new RealisationTrainProduit(); // You need to replace RealisationTrainProduit with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RealisationTrainProduit result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RealisationTrainProduit constructSample(int i) {
		RealisationTrainProduit given = new RealisationTrainProduit();
        given.setProduit(new Produit(1L));
        given.setVolume(BigDecimal.TEN);
        given.setTsm(BigDecimal.TEN);
        given.setRealisationTrain(new RealisationTrain(1L));
        return given;
    }

}
