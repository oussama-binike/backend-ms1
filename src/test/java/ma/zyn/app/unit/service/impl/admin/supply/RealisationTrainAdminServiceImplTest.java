package ma.zyn.app.unit.service.impl.admin.supply;

import ma.zyn.app.bean.core.supply.RealisationTrain;
import ma.zyn.app.dao.facade.core.supply.RealisationTrainDao;
import ma.zyn.app.service.impl.admin.supply.RealisationTrainAdminServiceImpl;

import ma.zyn.app.bean.core.referentiel.Train ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain ;
import ma.zyn.app.bean.core.referentiel.DestinationTrain ;
import ma.zyn.app.bean.core.supply.RealisationTrain ;
import ma.zyn.app.bean.core.supply.RealisationTrainProduit ;
import ma.zyn.app.bean.core.referentiel.TypeWagon ;
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
class RealisationTrainAdminServiceImplTest {

    @Mock
    private RealisationTrainDao repository;
    private AutoCloseable autoCloseable;
    private RealisationTrainAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RealisationTrainAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRealisationTrain() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRealisationTrain() {
        // Given
        RealisationTrain toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRealisationTrain() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRealisationTrainById() {
        // Given
        Long idToRetrieve = 1L; // Example RealisationTrain ID to retrieve
        RealisationTrain expected = new RealisationTrain(); // You need to replace RealisationTrain with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RealisationTrain result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RealisationTrain constructSample(int i) {
		RealisationTrain given = new RealisationTrain();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setJour(LocalDateTime.now());
        given.setProvennanceTrain(new ProvennanceTrain(1L));
        given.setDestinationTrain(new DestinationTrain(1L));
        given.setTrain(new Train(1L));
        given.setTypeWagon(new TypeWagon(1L));
        given.setStadeOperatoire(new StadeOperatoire(1L));
        given.setTauxCompletude(BigDecimal.TEN);
        given.setTauxRemplissage(BigDecimal.TEN);
        given.setTauxChargement(BigDecimal.TEN);
        given.setTempsChargement("tempsChargement-"+i);
        given.setTempsDechargement("tempsDechargement-"+i);
        given.setTempsTransite(BigDecimal.TEN);
        given.setExpedie(false);
        given.setPlanifie(false);
        List<RealisationTrainProduit> realisationTrainProduits = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                RealisationTrainProduit element = new RealisationTrainProduit();
                                                element.setId((long)id);
                                                element.setProduit(new Produit(Long.valueOf(1)));
                                                element.setVolume(new BigDecimal(2*10));
                                                element.setTsm(new BigDecimal(3*10));
                                                element.setRealisationTrain(new RealisationTrain(Long.valueOf(4)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setRealisationTrainProduits(realisationTrainProduits);
        return given;
    }

}
