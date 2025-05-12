package ma.zyn.app.unit.service.impl.admin.camion;

import ma.zyn.app.bean.core.camion.RealisationCamion;
import ma.zyn.app.dao.facade.core.camion.RealisationCamionDao;
import ma.zyn.app.service.impl.admin.camion.RealisationCamionAdminServiceImpl;

import ma.zyn.app.bean.core.camion.RealisationCamion ;
import ma.zyn.app.bean.core.camion.RealisationCamionProduit ;
import ma.zyn.app.bean.core.camion.ProvennanceCamion ;
import ma.zyn.app.bean.core.camion.DestinationCamion ;
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
class RealisationCamionAdminServiceImplTest {

    @Mock
    private RealisationCamionDao repository;
    private AutoCloseable autoCloseable;
    private RealisationCamionAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RealisationCamionAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRealisationCamion() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRealisationCamion() {
        // Given
        RealisationCamion toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRealisationCamion() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRealisationCamionById() {
        // Given
        Long idToRetrieve = 1L; // Example RealisationCamion ID to retrieve
        RealisationCamion expected = new RealisationCamion(); // You need to replace RealisationCamion with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RealisationCamion result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RealisationCamion constructSample(int i) {
		RealisationCamion given = new RealisationCamion();
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setJour(LocalDateTime.now());
        given.setNombreCamions(BigDecimal.TEN);
        given.setDureeMoyenneTransport(BigDecimal.TEN);
        given.setTotalTms(BigDecimal.TEN);
        given.setProvennanceCamion(new ProvennanceCamion(1L));
        given.setDestinationCamion(new DestinationCamion(1L));
        List<RealisationCamionProduit> realisationCamionProduits = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                RealisationCamionProduit element = new RealisationCamionProduit();
                                                element.setId((long)id);
                                                element.setProduit(new Produit(Long.valueOf(1)));
                                                element.setTsm(new BigDecimal(2*10));
                                                element.setRealisationCamion(new RealisationCamion(Long.valueOf(3)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setRealisationCamionProduits(realisationCamionProduits);
        return given;
    }

}
