package ma.zyn.app.unit.service.impl.admin.camion;

import ma.zyn.app.bean.core.camion.RealisationCamionProduit;
import ma.zyn.app.dao.facade.core.camion.RealisationCamionProduitDao;
import ma.zyn.app.service.impl.admin.camion.RealisationCamionProduitAdminServiceImpl;

import ma.zyn.app.bean.core.camion.RealisationCamion ;
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
class RealisationCamionProduitAdminServiceImplTest {

    @Mock
    private RealisationCamionProduitDao repository;
    private AutoCloseable autoCloseable;
    private RealisationCamionProduitAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RealisationCamionProduitAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRealisationCamionProduit() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRealisationCamionProduit() {
        // Given
        RealisationCamionProduit toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRealisationCamionProduit() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRealisationCamionProduitById() {
        // Given
        Long idToRetrieve = 1L; // Example RealisationCamionProduit ID to retrieve
        RealisationCamionProduit expected = new RealisationCamionProduit(); // You need to replace RealisationCamionProduit with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RealisationCamionProduit result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RealisationCamionProduit constructSample(int i) {
		RealisationCamionProduit given = new RealisationCamionProduit();
        given.setProduit(new Produit(1L));
        given.setTsm(BigDecimal.TEN);
        given.setRealisationCamion(new RealisationCamion(1L));
        return given;
    }

}
