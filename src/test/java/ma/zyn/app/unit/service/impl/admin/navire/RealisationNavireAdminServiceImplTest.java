package ma.zyn.app.unit.service.impl.admin.navire;

import ma.zyn.app.bean.core.navire.RealisationNavire;
import ma.zyn.app.dao.facade.core.navire.RealisationNavireDao;
import ma.zyn.app.service.impl.admin.navire.RealisationNavireAdminServiceImpl;

import ma.zyn.app.bean.core.navire.DestinationNavire ;
import ma.zyn.app.bean.core.navire.RealisationNavireQualite ;
import ma.zyn.app.bean.core.navire.RealisationNavire ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.bean.core.navire.RealisationNavireProduit ;
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
class RealisationNavireAdminServiceImplTest {

    @Mock
    private RealisationNavireDao repository;
    private AutoCloseable autoCloseable;
    private RealisationNavireAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RealisationNavireAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRealisationNavire() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRealisationNavire() {
        // Given
        RealisationNavire toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRealisationNavire() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRealisationNavireById() {
        // Given
        Long idToRetrieve = 1L; // Example RealisationNavire ID to retrieve
        RealisationNavire expected = new RealisationNavire(); // You need to replace RealisationNavire with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RealisationNavire result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RealisationNavire constructSample(int i) {
		RealisationNavire given = new RealisationNavire();
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setNumeroNavire("numeroNavire-"+i);
        given.setNumeroExpedition("numeroExpedition-"+i);
        given.setJour(LocalDateTime.now());
        given.setDestinationNavire(new DestinationNavire(1L));
        given.setTauxCompletude(BigDecimal.TEN);
        given.setTauxRemplissage(BigDecimal.TEN);
        given.setDateChargement(LocalDateTime.now());
        given.setDateFinChargement(LocalDateTime.now());
        List<RealisationNavireProduit> realisationNavireProduits = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                RealisationNavireProduit element = new RealisationNavireProduit();
                                                element.setId((long)id);
                                                element.setProduit(new Produit(Long.valueOf(1)));
                                                element.setTsm(new BigDecimal(2*10));
                                                element.setVolume(new BigDecimal(3*10));
                                                element.setRealisationNavire(new RealisationNavire(Long.valueOf(4)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setRealisationNavireProduits(realisationNavireProduits);
        List<RealisationNavireQualite> realisationNavireQualites = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                RealisationNavireQualite element = new RealisationNavireQualite();
                                                element.setId((long)id);
                                                element.setProduitMarchand(new ProduitMarchand(Long.valueOf(1)));
                                                element.setTsm(new BigDecimal(2*10));
                                                element.setVolume(new BigDecimal(3*10));
                                                element.setRealisationNavire(new RealisationNavire(Long.valueOf(4)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setRealisationNavireQualites(realisationNavireQualites);
        return given;
    }

}
