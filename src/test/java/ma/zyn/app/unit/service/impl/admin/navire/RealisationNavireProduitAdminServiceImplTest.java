package ma.zyn.app.unit.service.impl.admin.navire;

import ma.zyn.app.bean.core.navire.RealisationNavireProduit;
import ma.zyn.app.dao.facade.core.navire.RealisationNavireProduitDao;
import ma.zyn.app.service.impl.admin.navire.RealisationNavireProduitAdminServiceImpl;

import ma.zyn.app.bean.core.navire.RealisationNavire ;
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
class RealisationNavireProduitAdminServiceImplTest {

    @Mock
    private RealisationNavireProduitDao repository;
    private AutoCloseable autoCloseable;
    private RealisationNavireProduitAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RealisationNavireProduitAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRealisationNavireProduit() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveRealisationNavireProduit() {
        // Given
        RealisationNavireProduit toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteRealisationNavireProduit() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetRealisationNavireProduitById() {
        // Given
        Long idToRetrieve = 1L; // Example RealisationNavireProduit ID to retrieve
        RealisationNavireProduit expected = new RealisationNavireProduit(); // You need to replace RealisationNavireProduit with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        RealisationNavireProduit result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private RealisationNavireProduit constructSample(int i) {
		RealisationNavireProduit given = new RealisationNavireProduit();
        given.setProduit(new Produit(1L));
        given.setTsm(BigDecimal.TEN);
        given.setVolume(BigDecimal.TEN);
        given.setRealisationNavire(new RealisationNavire(1L));
        return given;
    }

}
